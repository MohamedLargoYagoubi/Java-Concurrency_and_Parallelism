import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class SumatorioSecuencial {
    private long Begin;
    private long End;
    private long Result;
    private long NSums;

    // Constructor para un rango específico
    public SumatorioSecuencial(long end) {
        this.Begin = 1;
        this.End = end;
    }

    // Constructor para un rango definido por begin y end
    public SumatorioSecuencial(long begin, long end) {
        this.Begin = begin;
        this.End = end;
    }

    // Getters y setters
    public long getBegin() {
        return Begin;
    }

    public void setBegin(long begin) {
        Begin = begin;
    }

    public long getEnd() {
        return End;
    }

    public void setEnd(long end) {
        End = end;
    }

    public long getResult() {
        return Result;
    }

    public void setResult(long result) {
        Result = result;
    }

    public long getNSums() {
        return NSums;
    }

    public void setNSums(long NSums) {
        this.NSums = NSums;
    }

    private void Add(long num) {
        Result += num;
        NSums++;
    }

    private long SumatorioFormula() {
        return (long) (End * ((End + 1) / 2.0) - (Begin - 1) * (Begin / 2.0));
    }

    public long Calculate() {
        // Usar un pool de hilos con un tamaño fijo
        final int poolSize = 4; // Número de hilos en el pool
        final int taskSize = 1_000_000; // Tamaño de cada tarea
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        try {
            // Crear una lista de tareas
            List<Callable<Long>> tasks = new ArrayList<>();
            for (long i = Begin; i <= End; i += taskSize) {
                long taskBegin = i;
                long taskEnd = Math.min(i + taskSize - 1, End); // Asegurarse de no superar End
                tasks.add(new SumTask(taskBegin, taskEnd));
            }

            // Ejecutar todas las tareas y recoger los resultados parciales
            List<Future<Long>> results = executor.invokeAll(tasks);

            // Combinar los resultados parciales
            Result = 0;
            NSums = 0;
            for (Future<Long> result : results) {
                long partialSum = result.get();
                Result += partialSum;
                NSums += taskSize; // Sumamos el tamaño de cada tarea al número de sumas
            }

            // Ajustar NSums en caso de que la última tarea sea más pequeña
            NSums = Math.min(NSums, End - Begin + 1);

            // Validar resultados usando la fórmula
            assert Result == SumatorioFormula() : "El resultado no coincide con la fórmula";
            assert NSums == (End - Begin + 1) : "El número de sumas no coincide con el rango";

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error en la ejecución de tareas", e);
        } finally {
            // Apagar el ExecutorService
            executor.shutdown();
        }

        return Result;
    }

    // Tarea Callable para calcular la suma de un rango
    static class SumTask implements Callable<Long> {
        private final long begin;
        private final long end;

        public SumTask(long begin, long end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public Long call() {
            long sum = 0;
            for (long i = begin; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
    }

}