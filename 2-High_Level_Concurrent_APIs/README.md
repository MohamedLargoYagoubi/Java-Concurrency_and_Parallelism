<p align="center">
  <img width=100% src="https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/blob/main/media/banner.gif" /> 
</p>

<h1 align="center">HIGH LEVEL CONCURRENCY APIs</h1>

<div align="center">
  <b>
    TODO
  </b>
</div>


<h1 align="center">AUTORES</h1>
<p><b>Fecha de creación:</b> Lleida (Spain), 31-10-2024 </p>
<p><b>Última modificación:</b> Lleida (Spain), 31-10-2024 </p>
<p><b>Desarrollado por :</b></p>

<div align="center">
  <table>  
    <thead>  <tr>  <th> <img src="https://avatars.githubusercontent.com/u/50272010?s=400&u=6ed6f2401e6a5b578fa1e59646f5398c2c073eff&v=4" width=115 style="border-radius: 50"> </th> </thead> 
    <tbody> 
      <tr>  <td align="center"> <b> MOHAMED LARGO YAGOUBI</b></td></tr>  
      <tr>  <td align="center"> <a href = "https://github.com/MohamedLargoYagoubi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"/> </a> </td> </tr> 
    </tbody>  
  </table>
</div>





<h1 align="center">TAGS</h1>
<div align="center">
  <p align="center"><b>TECNOLOGÍAS</b></h1>
</div>

<p align="center">
  <img src="https://img.shields.io/badge/JAVA-B1361E?style=for-the-badge&logo=openjdk&logoColor=white"/>
</p>

<div align="center">
  <p align="center"><b>IDIOMAS</b></h1>
</div>
<p align="center">
  <img src="https://img.shields.io/badge/-Español-734F96?style=for-the-badge&logo=microsofttranslator"/>
</p>

<div align="center">
  <p align="center"><b>ESTADO</b></h1>
</div>

<p align="center">
  <img src="https://img.shields.io/badge/🏗️%20𝗘𝗡%20𝗗𝗘𝗦𝗔𝗥𝗥𝗢𝗟𝗟𝗢-black?style=for-the-badge"/>
</p>


<h1 align="center">ÍNDICE</h1>

<div align="center">
  <b>
    TODO.
  </b>
</div>


# 1. FRAMEWORK DE EJECUCIÓN

<hr />

## 1.1 - Executor Framework 

Un `Executor` es un objeto que ejecuta tareas enviadas de tipo `Runnable`. Este proporciona una forma de desacoplar la presentación de tareas de los detalles de cómo se ejecutarán, incluyendo el uso de hilos, la planificación, etc.

-	El método `execute(Runnable task)` toma un objeto `Runnable` y lo ejecuta en algún momento en el futuro.

```java
Executor executor = Executors.newSingleThreadExecutor();
executor.execute(() -> {
    System.out.println("Tarea ejecutada");
});
```

## 1.2 - Interface Callable<V> 

La interfaz `Callable<V>` es similar a `Runnable`, representa una tarea asíncrona que puede devolver un resultado y lanzar una excepción. La interfaz `Callable` es bastante simple y contiene un único método sin argumentos llamado `call()`, que se invoca para ejecutar la tarea asíncrona.
-	Si la tarea se ejecuta de forma asíncrona, el resultado se propaga típicamente de vuelta al creador de la tarea a través de un `Future`.

```java
public interface Callable<V> {
    V call() throws Exception;
}
```


## 1.3 - Interface Future<V>

La interfaz `Future<V>` representa el resultado de una tarea asíncrona. Proporciona métodos para verificar si la tarea está completa, esperar su finalización y recuperar el resultado.
- Para obtener el resultado, se llama a uno de los dos métodos `get()` en el Future. Si se llama al método `get()` antes de que la tarea asíncrona haya terminado, el método bloqueará la ejecución del hilo actual hasta que el resultado esté listo. 
-	Si deseamos añadir una espera máxima en el bloque del método `get(long timeout, TimeUnit unit)`. 
-	Podemos cancelar la tarea asíncrona llamando al método `cancel(boolean mayInterruptIfRunning)`. La ejecución de la tarea asincrónica debe estar implementada para soportar la cancelación, en caso contrario `cancel()` no tendrá efecto. 
-	Podemos verificar si la tarea asíncrona ha terminado (y si hay un resultado disponible) llamando al método `isDone()`. 
-	El método `isCancelled()` permite verificar si la tarea asíncrona ha sido cancelada. 


```java
public interface Future<V> {
    boolean cancel(boolean mayInterruptIfRunning);
    V get() throws InterruptedException, ExecutionException;
    V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
    boolean isCancelled();
    boolean isDone();
}
```

## 1.4 - ExecutorService

`ExecutorService` es una subinterfaz de `Executor` que añade métodos para gestionar la vida útil del servicio, como la terminación controlada y la presentación de tareas que devuelven un valor (complementa al método `execute()` con el método `submit()`).
-	`execute(Runnable command)` ejecuta la tarea proporcionada en algún momento en el futuro.
-	`submit(Runnable task)` o `submit(Callable<T> task)` permite enviar una tarea `Runnable` o  `Callable` para su ejecución y devuelve un Future que representa esa tarea.
-	`invokeAny(Collection<? extends Callable<T>> tasks)` ejecuta la colección de tareas y devuelve el resultado de una tarea que se completó con éxito (sin lanzar una excepción).
-	`invokeAll(Collection<? extends Callable<T>> tasks)` Ejecuta la colección de tareas y devuelve una lista de `Future` que representan el estado y los resultados de todas las tareas.
-	`shutdown()` termina de forma ordenada las tareas previamente enviadas, pero no se aceptan nuevas tareas.
-	`shutdownNow()` detiene todas las tareas que están activamente ejecutándose, deteniendo el procesamiento de tareas en espera y devuelve una lista de las tareas que estaban esperando ejecución.
-	`awaitTermination(long timeout, TimeUnit unit)` bloquea hasta que todas las tareas hayan completado la ejecución después de una solicitud de apagado, o hasta que ocurra el tiempo de espera, o hasta que el hilo actual sea interrumpido, lo que ocurra primero.

```java
public class EjemploExecutorService {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // execute(Runnable)
        executor.execute(() -> System.out.println("Tarea ejecutada con execute"));

        // submit(Runnable)
        Future<?> futureRunnable = executor.submit(() -> System.out.println("Tarea ejecutada con submit (Runnable)"));

        // submit(Callable)
        Future<Integer> futureCallable = executor.submit(() -> {
            System.out.println("Tarea ejecutada con submit (Callable)");
            return 42;
        });

        // invokeAny()
        List<Callable<String>> tasks = Arrays.asList(
            () -> "Tarea 1",
            () -> "Tarea 2",
            () -> "Tarea 3"
        );
        try {
            String result = executor.invokeAny(tasks);
            System.out.println("Resultado de invokeAny: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // invokeAll()
        try {
            List<Future<String>> futures = executor.invokeAll(tasks);
            for (Future<String> future : futures) {
                System.out.println("Resultado de invokeAll: " + future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // shutdown and awaitTermination
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
```

## 1.5 - ScheduledExecutorService

`ScheduledExecutorService` es una subinterfaz `ExecutorService` que puede programar tareas para ejecutarse después de un retraso o para ejecutarse repetidamente con un intervalo de tiempo fijo entre cada ejecución.
- `schedule(Callable<V> task, long delay, TimeUnit unit)` ejecuta el `Callable` dado después del retraso especificado. Devuelve un `ScheduledFuture` que se puede usar para cancelar la tarea antes de que comience a ejecutarse o para obtener el resultado una vez ejecutada.
- `schedule(Runnable task, long delay, TimeUnit unit)` funciona de forma manera similar al método anterior, excepto que un `Runnable` no puede devolver un valor, por lo que el método `ScheduledFuture.get()` devuelve null cuando la tarea ha terminado.
- `scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit)` ejecuta una tarea periódicamente. La tarea se ejecuta por primera vez después del `initialDelay` y luego recurrentemente cada vez que expira el `period`.
- `scheduleWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit)` este método funciona de manera similar al anterior, excepto que el `delay` se interpreta como el tiempo entre el final de una ejecución y el comienzo de la siguiente.
- El ScheduledExecutorService necesita ser apagado usando los métodos `shutdown()` o `shutdownNow()`. Si no, mantendrá la JVM en ejecución, incluso cuando todos los demás hilos se hayan detenido.  

```java
scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
    public void run() {
        System.out.println("Periodic Task Executed!");
    }
}, 0, 10, TimeUnit.SECONDS);
scheduledExecutorService.shutdown();
```

## 1.6 - ThreadPool

Los `ThreadPools` son una técnica para gestionar la ejecución de múltiples tareas en hilos reutilizables, optimizando el uso de recursos y mejorando el rendimiento de las aplicaciones concurrentes. 
- **Single Thread Executor** crea un único hilo para ejecutar las tareas de manera secuencial y evitando problemas de concurrencia. Se crea mediante el método `newSingleThreadExecutor()`.
- **Fixed Thread Pool** crea un número fijo de hilos que se reutilizan para ejecutar las tareas. Si todas las hilos están ocupados, las tareas adicionales se colocan en una cola de tareas sin límite (`LinkedBlockingQueue`) hasta que un hilo esté disponible, es útil cuando se desea limitar el número de hilos concurrentes para evitar el consumo excesivo de recursos y se invoca con el método `newFixedThreadPool(int nThreads)`.
- **Cached Thread Pool** crea nuevos hilos según sea necesario, pero reutiliza los hilos existentes si están disponibles (si no se usan durante 60 segundos se terminan y se eliminan del pool), usa una cola síncrona (`SynchronousQueue`) y puede crecer dinámicamente hasta un número muy grande de hilos (`Integer.MAX_VALUE`). Este tipo de pool es adecuado para ejecutar muchas tareas cortas y de vida corta, se invoca con el método `newCachedThreadPool()`.

Además de los métodos mencionados en el `ExecutorService`, mediante `isShutdown()` podremos saber si el pool ha sido parado y con `isTerminated()` si se ha completado la parada.



# 2. PATRONES CONCURRENCIA

<hr />

## 2.1 - Patrón Fork/Join 

El patrón **Fork/Join** divide un problema grande en subproblemas más pequeños, los resuelve de manera recursiva y luego combina los resultados (enfoque de Divide y Vencerás), es ideal para tareas que pueden ser divididas en partes independientes que se pueden ejecutar en paralelo. Se utiliza un algoritmo de Work-Stealing, donde los hilos que se quedan sin trabajo pueden "robar" tareas de otros hilos que aún están ocupados, esto optimiza la utilización de los recursos y mejora el rendimiento.

## 2.2 - ForkJoinPool  

Un `ForkJoinPool` es el grupo de hilos que gestiona la ejecución de las tareas. Para crear una instancia de `ForkJoinPool` se invoca con su constructor o el método estático `commonPool()`, que devuelve un pool de hilos común.

```java
ForkJoinPool forkJoinPool = new ForkJoinPool(int numThreads);
ForkJoinPool commonPool = ForkJoinPool.commonPool();
```

Disponemos de los siguientes métodos:

- `execute(ForkJoinTask<?> task)` y `execute(Runnable task)` ejecuta la tarea dada de manera asíncrona o en algún momento en el futuro.
-	`invokeAll(Collection<? extends ForkJoinTask<?>> tasks)` y `invoke(ForkJoinTask<?> task)` invoca todas las tareas en la colección o la tarea dada y espera a que se completen, devolviendo su resultado.
-	`submit(ForkJoinTask<?> task)`, `submit(Callable<T> task)` y `submit(Runnable task)`: Envía una tarea `ForkJoinTask` , `Callable` o `Runnable` para su ejecución y devuelve un `Future` que representa la tarea.
-	`submit(Runnable task, T result)` envía una tarea `Runnable` para su ejecución y devuelve un `Future` que representa la tarea, con un resultado predefinido.
-	`awaitQuiescence(long timeout, TimeUnit unit)` y `awaitTermination(long timeout, TimeUnit unit)` se espera o bloquea hasta que todas las tareas hayan completado su ejecución después de una solicitud de apagado, o hasta que ocurra el tiempo de espera, o hasta que el hilo actual sea interrumpido.
-	`getActiveThreadCount()` devuelve una estimación del número de hilos que están actualmente robando o ejecutando tareas.
-	`getParallelism()` devuelve el nivel de paralelismo objetivo de este pool.
-	`getPoolSize()` devuelve el número de hilos de trabajo que han comenzado pero no han terminado aún.
-	`getQueuedSubmissionCount()` devuelve una estimación del número de tareas enviadas a este pool que aún no han comenzado a ejecutarse.
-	`getQueuedTaskCount()` devuelve una estimación del número total de tareas actualmente en las colas de los hilos de trabajo.
-	`getRunningThreadCount()` devuelve una estimación del número de hilos de trabajo que no están bloqueados esperando unirse a tareas u otra sincronización gestionada.
-	`getStealCount()` devuelve una estimación del número total de tareas robadas de la cola de trabajo de un hilo por otro. 

## 2.4 - ForkJoinTask, RecursiveAction y RecursiveTask

Una `ForkJoinTask` es una clase que representa una tarea que se ejecuta dentro de un `ForkJoinPool` y tiene dos subclases principales:
- `RecursiveAction` para tareas que no devuelven un resultado.
- `RecursiveTask<V>` para tareas que devuelven un resultado.

Para `RecursiveAction` y `RecursiveTask` definimos los siguientes métodos, indicando las diferencias entre cada clase:
- `compute()` método abstracto que debe ser implementado para definir la lógica de la tarea. Este método se llama cuando la tarea se ejecuta, en la RecursiveTask se tendrá que devolver algún valor.

```java
protected abstract void compute(); // RecursiveAction (no devuelve nada)
```
```java
protected abstract V compute(); // RecursiveTask 
```

- `exec()` implementa las convenciones de ejecución para `RecursiveActions` o `RecursiveTasks`. Este método llama internamente a `compute()`.

```java
protected final boolean exec() {
    compute(); // RecursiveAction
    return true;
}
```
```java
protected final boolean exec() {
    setRawResult(compute()); // RecursiveTask
    return true;
}
```

- `getRawResult()` siempre devuelve null ya que `RecursiveAction` no produce un resultado. En caso de `RecursiveTask` devuelve el resultado de la tarea.

```java
public final Void getRawResult() { // RecursiveAction (no devuelve nada) 

    return null;
}
```
```java
public final V getRawResult() { // RecursiveTask
    return result;
}
```

- `setRawResult(Void mustBeNull)` requiere un valor de finalización nulo, en `RecursiveAction` este método no hace nada. En el caso de `RecursiveTask` establece el resultado de la tarea.

```java
protected final void setRawResult(V value) { // RecursiveTask (en RecursiveAction no está definido) 
    this.result = value;
}
```

CODE TODO 
```java
public class IncrementTask extends RecursiveAction {
    private final long[] array;
    private final int start, end;
    private static final int THRESHOLD = 1000;

    public IncrementTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                array[i]++;
            }
        } else {
            int mid = (start + end) / 2;
            IncrementTask leftTask = new IncrementTask(array, start, mid);
            IncrementTask rightTask = new IncrementTask(array, mid, end);
            invokeAll(leftTask, rightTask);
        }
    }
}
```

```java
public class SumTask extends RecursiveTask<Long> {
    private final long[] array;
    private final int start, end;
    private static final int THRESHOLD = 1000;

    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);
            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }
}
```

Volviendo a `ForkJoinTask` definiremos los siguientes métodos:
- `fork()` organiza la ejecución asíncrona de la tarea. Es similar a iniciar un nuevo hilo, pero mucho más eficiente.
```java
ForkJoinTask<?> task = new RecursiveTask<Integer>() {
    @Override
    protected Integer compute() {
        return 1 + 1;
    }
};
task.fork();
```
- `join()` espera a que la tarea se complete y devuelve su resultado. Es similar a `Future.get()`, pero más eficiente.
```java
Integer result = task.join();
```
- `invoke()` invoca la tarea y espera a que se complete, devolviendo su resultado. Es una combinación de `fork()` y `join()`.
```java
Integer result = task.invoke();
```
- `isDone()` devuelve true si la tarea se ha completado de alguna manera (incluyendo cancelación).
```java
boolean done = task.isDone();
```
- `isCompletedNormally()` devuelve true si la tarea se completó sin ser cancelada ni encontrar una excepción.
```java
boolean completedNormally = task.isCompletedNormally();
```
- `isCancelled()` devuelve true si la tarea fue cancelada.
```java
boolean cancelled = task.isCancelled();
```
- `isCompletedAbnormally()` devuelve true si la tarea fue cancelada o encontró una excepción.
```java
boolean completedAbnormally = task.isCompletedAbnormally();
```
- `getException()` si la tarea se completó anormalmente, devuelve la excepción encontrada; de lo contrario, devuelve null.
```java
Throwable exception = task.getException();
```
- `cancel(boolean mayInterruptIfRunning)` intenta cancelar la tarea. Devuelve true si la tarea ha sido cancelada con éxito.
```java
boolean cancelled = task.cancel(true);
```
- `complete(V value)` completa la tarea normalmente con el valor dado.
```java
task.complete(42);
```
- `completeExceptionally(Throwable ex)` completa la tarea excepcionalmente con la excepción dada.
```java
task.completeExceptionally(new RuntimeException("Error"));
```
- `getRawResult()` devuelve el resultado de la tarea sin esperar a que se complete. Este método es protegido y generalmente se usa en subclases.
```java
protected Integer getRawResult() {
    return 42;
}
```
- `setRawResult(V value)` establece el resultado de la tarea sin marcarla como completada. Este método es protegido y generalmente se usa en subclases.
```java
protected void setRawResult(Integer value) {
    // Establecer el resultado
}
```
- `exec()` método abstracto que debe ser implementado por las subclases para definir la lógica de la tarea. Este método es llamado internamente por el marco Fork/Join.
```java
protected boolean exec() {
    // Lógica de la tarea
    return true;
}
```

## 2.2 - Tarea asíncronas   

La clase `CountedCompleter` una subclase de `ForkJoinTask` diseñada para tareas que requieren acciones de finalización cuando se completan y no tienen acciones pendientes. Es especialmente útil para tareas que pueden dividirse en subtareas y necesitan manejar la finalización de manera más flexible.

- `compute()` método abstracto que debe ser implementado para definir la lógica de la tarea. En la mayoría de los casos, este método debe invocar `tryComplete()` antes de retornar.
- `onCompletion(CountedCompleter<?> caller)` se llama cuando la tarea y todas sus subtareas se han completado normalmente. Puede ser sobrescrito para realizar acciones adicionales al completar la tarea.
- `onExceptionalCompletion(Throwable ex, CountedCompleter<?> caller)` se llama cuando la tarea o alguna de sus subtareas encuentra una excepción. Puede ser sobrescrito para manejar excepciones.
- `addToPendingCount(int delta)` ajusta el conteo de tareas pendientes por el valor especificado. Este método es útil para incrementar o decrementar el número de tareas pendientes.
- `setPendingCount(int count)` establece el conteo de tareas pendientes al valor especificado.
- `getPendingCount()` devuelve el número actual de tareas pendientes.
- `tryComplete()` decrementa el conteo de tareas pendientes y, si llega a cero, completa la tarea y su completer, si existe.
- `propagateCompletion()` completa esta tarea y su completer, si existe, si el conteo de tareas pendientes es cero.

La `CompletableFuture` es una clase  que implementa las interfaces `Future` y `CompletionStage`. Permite manejar tareas asíncronas y encadenar múltiples etapas de computación de manera fluida.

- `runAsync(Runnable runnable)` ejecuta una tarea asíncrona que no devuelve un resultado.
- `supplyAsync(Supplier<U> supplier)` ejecuta una tarea asíncrona que devuelve un resultado.
- `thenApply(Function<? super T,? extends U> fn)` aplica una función al resultado de la etapa anterior y devuelve un nuevo `CompletableFuture` con el resultado de la función.
- `thenAccept(Consumer<? super T> action)` consume el resultado de la etapa anterior sin devolver un nuevo resultado.
- `thenRun(Runnable action)` ejecuta una acción después de que la etapa anterior se complete, sin usar el resultado de la etapa anterior.
- `thenCompose(Function<? super T,? extends CompletionStage<U>> fn)` encadena dos etapas de computación, donde la segunda etapa depende del resultado de la primera.
- `thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)` combina los resultados de dos etapas de computación.
- `thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)` consume los resultados de dos etapas de computación.
- `allOf(CompletableFuture<?>... cfs)` devuelve un nuevo `CompletableFuture` que se completa cuando todas las etapas dadas se completen.
- `anyOf(CompletableFuture<?>... cfs)` devuelve un nuevo `CompletableFuture` que se completa cuando cualquiera de las etapas dadas se complete.
- `handle(BiFunction<? super T, Throwable,? extends U> fn)` maneja el resultado o la excepción de la etapa anterior.
- `completeExceptionally(Throwable ex)` completa el `CompletableFuture` excepcionalmente con la excepción dada.

La interfaz `CompletionStage`  representa una etapa de una computación posiblemente asíncrona. Define métodos para encadenar etapas de computación.
- `thenApply(Function<? super T,? extends U> fn)` aplica  una función al resultado de la etapa anterior.
- `thenAccept(Consumer<? super T> action)` consume el resultado de la etapa anterior.
- `thenRun(Runnable action)` ejecuta una acción después de que la etapa anterior se complete.
- `thenCompose(Function<? super T,? extends CompletionStage<U>> fn)` encadena dos etapas de computación.
- `thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn`) combina los resultados de dos etapas de computación.
- `thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)` consume los resultados de dos etapas de computación.
- `allOf(CompletableFuture<?>... cfs)` devuelve un nuevo `CompletionStage` que se completa cuando todas las etapas dadas se completen.
- `anyOf(CompletableFuture<?>... cfs)` devuelve un nuevo `CompletionStage` que se completa cuando cualquiera de las etapas dadas se complete.
- `handle(BiFunction<? super T, Throwable,? extends U> fn)` maneja el resultado o la excepción de la etapa anterior.
- `exceptionally(Function<Throwable,? extends T> fn)` maneja excepciones de la etapa anterior.


# 3. CONCURRENCIA ESTRUCTURADA 
<hr />

## 3.1 - Programación estructurada vs no estructurada

- La **concurrencia no estructurada** se un modelo en el que las tareas se ejecutan en una red de hilos desorganizados. En este modelo, el inicio y el final de los hilos son difíciles de identificar en el código, lo que complica la comprensión y el mantenimiento del programa. Además, la gestión de errores es deficiente, y es común que aparezcan hilos huérfanos cuando la aplicación termina, lo que puede llevar a problemas de rendimiento y estabilidad.
- La **concurrencia estructurada** introduce una nueva estructura de control que mejora significativamente la gestión de tareas concurrentes. Este modelo ofrece varias ventajas clave:
  - **Ámbito claro:** Define un ámbito bien delimitado donde los hilos comienzan al principio y terminan al final. Esto facilita la comprensión y el seguimiento del flujo de ejecución.
  - **Gestión de errores:** Permite una gestión de errores más limpia y eficiente, asegurando que las excepciones se manejen de manera coherente y predecible.
  - **Cancelación de hilos:** Facilita la cancelación de hilos cuyos resultados ya no son necesarios, evitando la ejecución innecesaria y mejorando el uso de recursos.
 
## 3.2 - StructuredTaskScope 

Un **StructuredTaskScope** es una clase de la API de concurrencia estructurada. Permite gestionar un grupo de subtareas concurrentes como una unidad, asegurando que todas las subtareas se completen antes de que la tarea principal continúe. Posee los siguientes métodos:
- `fork(Callable<T> task)` inicia una nueva subtarea y devuelve un objeto `Subtask` que representa la subtarea.
- `join()` espera a que todas las subtareas se completen.
- `close()` cierra el ámbito de la tarea, asegurando que no se inicien nuevas subtareas.
- `shutdown()` cancela todas las subtareas no terminadas y evita que se inicien nuevas subtareas.
- `onCompletion(CountedCompleter<?> caller)` se llama cuando la tarea y todas sus subtareas se han completado normalmente. Puede ser sobrescrito para realizar acciones adicionales al completar la tarea.
- `addToPendingCount(int delta)`ajusta el conteo de tareas pendientes por el valor especificado.
- `setPendingCount(int count)` establece el conteo de tareas pendientes al valor especificado.
- `getPendingCount()` devuelve el número actual de tareas pendientes.
- `tryComplete()` decrementa el conteo de tareas pendientes y, si llega a cero, completa la tarea y su completer, si existe.
- `propagateCompletion()` completa esta tarea y su completer, si existe, si el conteo de tareas pendientes es cero.
- `onExceptionalCompletion(Throwable ex, CountedCompleter<?> caller)` se usa cuando la tarea o alguna de sus subtareas encuentra una excepción. Puede ser sobrescrito para manejar excepciones.
  

# 2. BIBLIOGRAFÍA 

<hr />

## 2.1 - TODO 

TODO
