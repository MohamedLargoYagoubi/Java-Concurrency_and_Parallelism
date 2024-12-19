import static org.junit.jupiter.api.Assertions.*;

public class SumatorioSecuencial {

    private long Begin;
    private long End;
    private long Result;
    private long NSums;

    // Clase interna para los hilos trabajadores
    private static class WorkerThread extends Thread {
        private final long begin;
        private final long end;
        private long partialResult;
        private long partialSums;

        public WorkerThread(long begin, long end) {
            this.begin = begin;
            this.end = end;
            this.partialResult = 0;
            this.partialSums = 0;
        }

        @Override
        public void run() {
            for (long i = begin; i <= end; i++) {
                partialResult += i;
                partialSums++;
            }
        }

        public long getPartialResult() {
            return partialResult;
        }

        public long getPartialSums() {
            return partialSums;
        }
    }

    public SumatorioSecuencial(long end) {
        this.Begin = 1;
        this.End = end;
    }

    public SumatorioSecuencial(long begin, long end) {
        this.Begin = begin;
        this.End = end;
    }

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
        return ((long) (End * ((End + 1) / 2.0)) - (long) ((Begin - 1) * (Begin / 2.0)));
    }

    public long Calculate() {
        setResult(0);
        setNSums(0);

        try {
            // Número de hilos
            int numThreads = 4;
            long range = End - Begin + 1;

            // Ajustar el número de hilos si el rango es pequeño
            numThreads = (int) Math.min(numThreads, range);

            // Calcular el tamaño del bloque para cada hilo
            long blockSize = range / numThreads;
            long remainder = range % numThreads;

            // Crear y iniciar los hilos
            WorkerThread[] threads = new WorkerThread[numThreads];
            long currentBegin = Begin;

            for (int i = 0; i < numThreads; i++) {
                long currentEnd = currentBegin + blockSize - 1;
                // Distribuir el remainder entre los primeros hilos
                if (remainder > 0) {
                    currentEnd++;
                    remainder--;
                }

                threads[i] = new WorkerThread(currentBegin, currentEnd);
                threads[i].start();
                currentBegin = currentEnd + 1;
            }

            // Esperar a que todos los hilos terminen y sumar resultados parciales
            for (WorkerThread thread : threads) {
                thread.join();
                Result += thread.getPartialResult();
                NSums += thread.getPartialSums();
            }

            // Verificar el resultado usando la fórmula matemática
            assertEquals(Result, SumatorioFormula());
            assertEquals(NSums, End - Begin + 1);

            return Result;

        } catch (InterruptedException e) {
            // En caso de interrupción, fallback al método secuencial
            Result = 0;
            NSums = 0;
            for (long x = Begin; x <= End; Add(x), x++);
            assertEquals(Result, SumatorioFormula());
            assertEquals(NSums, End - Begin + 1);
            return Result;
        }
    }

    private void assertEquals(long actual, long expected) {
        if (actual != expected) {
            throw new AssertionError("Expected: " + expected + " but was: " + actual);
        }
    }

}
