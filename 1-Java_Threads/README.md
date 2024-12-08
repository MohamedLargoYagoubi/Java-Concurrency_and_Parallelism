<p align="center">
  <img width=100% src="https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/blob/main/media/banner.gif" /> 
</p>

<h1 align="center">JAVA THREADS</h1>

<div align="center">
  <b>
    Bienvenido al repositorio, aquí se explicarán conceptos básicos y avanzados de programación concurrente y paralela, con ejemplos de código en Java. Este repositorio ha sido creado para consolidar los conocimientos adquiridos en las clases de "Sistemas Concurrentes y Paralelos" y resolver los ejercicios de los laboratorios. ¡Feliz aprendizaje!
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


# 1. HILOS EN JAVA

<hr />

## 1.1 - Aplicaciones Multi-hilo en Java


Java proporciona soporte integrado para la programación multihilo mediante la siguiente forma:
1.	Cuando se inicia máquina virtual de Java (JVM), se crea un hilo que se ejecuta de inmediato, se le suele llamar **hilo principal** y su tarea será ejecutar el método `main()`.
2.	El hilo principal ejecuta las instrucciones del programa una por una.
3.	De forma paralela se crea en el inicio de la JVM se crean otros hilos denominados **demonios** que se ejecutan en segundo plano y su función es apoyar la ejecución principal (por ejemplo, el Gargabe Collector). 
4.	Después de ejecutar todas las sentencias el hilo principal finaliza, junto al resto de hlos.


# 2. CREACIÓN DE HILOS

<hr />

## 2.1 - Creación de hilos

Además del hilo principal y los hilos demonio podemos crear hilos a merced mediante dos fromas distintas: **mediante herencia (extendiendo la clase Thread)** o **mediante composición (implementando la interface Runnable)**.

## 2.2 - Mediante herencia (extendiendo la clase Thread)

1. Crear una subclase que extienda de la clase `Thread`.
2. Sobrescribir el método `run()`, que contiene el código que ejecutará el hilo.
3. Para iniciar el hilo habrá que instanciar la subclase y llamar al método `start()`.

### EJEMPLO: Extendiendo la clase Thread
- **CÓDIGO** 
```java
class MiHilo extends Thread {
    public void run() {
        // Código que se ejecutará en el hilo
        System.out.println("Hilo ejecutando...");
    }
}
```

 ```java
public class EjemploHilo {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo();
        hilo.start();
    }
}
```

- **SALIDA**

`TODO SALIDA`

## 2.3 - Mediante composición (implementando la interface Runnable)

1. Crear una subclase que implemente interfaz `Runnable`. 
2. Sobrescribir el método `run()`, que contiene el código que ejecutará el hilo.
3. Para iniciar el hilo habrá que instanciar la subclase y pasarle como argumento al constructor un objeto tipo `Thread`, y finalmente llamar al método `start()`.

### EJEMPLO: Implementando la interface Runnable 

- **CÓDIGO** 
```java
class MiTarea implements Runnable {
    public void run() {
        // Código que se ejecutará en el hilo
        System.out.println("Tarea ejecutando...");
    }
}
```

 ```java
public class EjemploTarea {
    public static void main(String[] args) {
        MiTarea tarea = new MiTarea();
        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}
```

- **SALIDA**

`TODO SALIDA`

Es más recomendable utilizar la interfaz Runnable ya que a pesar de ser una solución más compleja nos ofrece una gran flexibilidad, ya que la clase puede implementar múltiples interfaces.

## 2.3 - Clase Thread  

La clase Thread permite crear hilos y administrar su comportamiento, proporcionando constructores y métodos para realizar operaciones en los hilos. 

### CONSTRUCTORES

- `Thread()`
- `Thread(String name)`
- `Thread(Runnable target)`
- `Thread(Runnable target, String name)`
- `Thread(ThreadGroup group, Runnable target)`
- `Thread(ThreadGroup group, Runnable target, String name)`
- `Thread(ThreadGroup group, Runnable target, String name, Long stackSize)`
- `Thread(ThreadGroup group, String name)`


| ARGUMENTO | DESCRIPCIÓN |
| :-: | :-: | 
| **ThreadGroup group**  | Colección de hilos que pueden ser gestionados como una unidad.  | 
| **Runnable target**  | Un `Runnable` es una interfaz funcional que define el método `run()`, contiene el código que se ejecutará en el hilo.  | 
| **String name** | Permite asignar nombres identificativos a hilos, útil para la depuración y el seguimiento de la ejecución de los hilos.  | 
| **long stackSize**  | Cantidad de memoria que se asignará para la pila del hilo. | 


### MÉTODOS

| MÉTODO | DESCRIPCIÓN |
| :-: | :-: | 
| **void start()**  | Inicia la ejecución del hilo. El método `run()` del hilo se llama por el sistema de hilos de Java. | 
| **void run()**  | Este método contiene el código que se ejecutará en el hilo. Es necesario sobrescribirlo cuando se crea una subclase de Thread. | 
| **void stop()** | Este método detiene el hilo. Sin embargo, está obsoleto y no se recomienda su uso debido a problemas de seguridad y consistencia. | 
| **void yield()**  | Hace que el hilo actual ceda el procesador, permitiendo que otros hilos de igual prioridad puedan ejecutarse. | 
| **int getId()**  | Devuelve el identificador único del hilo. | 
| **public static Thread currentThread()**  | Devuelve una referencia al hilo que está ejecutándose actualmente. | 
| **String getName()**  | Devuelve el nombre del hilo. | 
| **setName(String name)**  | Establece el nombre del hilo. | 
| **int getPriority()**  | Devuelve la prioridad del hilo. | 
| **setPriority(int priority)**  | Establece la prioridad del hilo. | 
| **Thread.State getState()**  | Devuelve el estado actual del hilo (por ejemplo, `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`). | 
| **Boolean isAlive()**  | Indica si el hilo está vivo. | 
| **Boolean isDaemon()**  | Indica si el hilo es un hilo demonio. | 
| **Boolean isInterrupted()**  | Indica si el hilo ha sido interrumpido. | 
| **void join()**  | Espera a que el hilo termine su ejecución. | 
| **void join(long millis)**  | Espera como máximo el tiempo especificado (en milisegundos) a que el hilo termine su ejecución. | 
| **void interrupt()**  | Interrumpe el hilo. | 
| **void setDaemon(boolean on)**  | Marca el hilo como un hilo demonio o no. | 
| **void checkAccess()**  | Comprueba si el hilo actual tiene permiso para modificar este hilo. | 
| **int activeCount()**  | Devuelve una estimación del número de hilos activos en el grupo de hilos actual. | 
| **static void sleep(long millis)**  | Hace que el hilo actual se suspenda durante el tiempo especificado (en milisegundos). | 
| **StackTraceElement[] getStackTrace()**  | Devuelve una matriz de elementos de traza de pila que representan la traza de pila de este hilo. | 
| **ThreadGroup getThreadGroup()**  | Devuelve el grupo de hilos al que pertenece este hilo. | 

# 3. CICLO DE VIDA DE LOS HILOS

<hr />

## 3.1 - Estados de los hilos 

<p align="center">
  <img width="500" height="300" src="https://media.geeksforgeeks.org/wp-content/uploads/20240318155846/Lifecycle-and-States-of-a-Thread-in-Java-1.png" />
</p>

-	**New**(nuevo estado o creado) : Se crea una instancia de la clase Thread o de una clase que implementa `Runnable`, pero aún no se llama a su método `start()`).
-	**Runnable** (ejecutable) : El hilo está listo para ejecutarse, es decir se ha llamado al método `start()` y puede ser ejecutado por la JVM, pero no necesariamente está en ejecución activa. El hilo entra en la cola de hilos listos y espera a que se le asignen recursos.
-	**Running** (ejecutándose) : El sistema operativo selecciona al hilo para ejecutarlo en un procesador, se ejecuta el método `run()`.
-	**Blocked State** (bloqueado) : El hilo intenta acceder a un recurso bloqueado por otro hilo. Por ejemplo, entra en un bloque synchronized ocupado ya por otro hilo.
-	**Waiting State** (espera) : El hilo entra en este estado cuando está esperando indefinidamente en un `wait()` a que otro hilo lo notifique de que puede continuar. No ejecuta ninguna acción hasta que otro hilo lo notifique mediante `notify()` o `notifyAll()`.
-	**Timed Waiting State** (dormido) : El hilo regresará al estado ejecutable si no se produce la notificación antes de que expire el tiempo. Se usan los método `sleep(int milliseconds)`, `sleep(int milliseconds, int nanoseconds)` o `join(long milliseconds)`.
-	**Terminated State** (finalizado) : La ejecución del hilo ha finalizado, ya sea porque completó su tarea (se ha completado el método `run()`) o porque fue interrumpido (se lanza una excepción no controlada). Seguidamente se invoca al método de destrucción del hilo `destroy()`.


# 4. FACTORÍA DE HILOS

<hr />

## 4.1 - Patrón Factory 

Una `ThreadFactory` es una interfaz que proporciona Java y permite crear de manera estándar y flexible instancias de hilos, es especialmente útil en aplicaciones que requieren la creación de muchos hilos.

### EJEMPLO: Uso del ThreadFactory
- **PROBLEMA** 
```java
public class SimpleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SimpleTask());
            thread.start();
        }
    }
}
```
La anterior implementación presenta los siguientes problemas:

·	Todos los hilos tienen nombres por defecto y no se pueden establecer otras propiedades como prioridad o estado daemon. Si se añaden estas propiedades en el código la sentencia `for` podría crecer en exceso.

·	Si se necesita cambiar la configuración de los hilos, hay que modificar múltiples lugares en el código.

·	No hay una forma sencilla de recopilar estadísticas sobre los hilos creados. 

- **SOLUCIÓN** 
```java
public class DaemonThreadFactory implements ThreadFactory {
    private int counter = 0;
    private String name;

    public DaemonThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-DaemonThread_" + counter);
        thread.setDaemon(true);
        counter++;
        return thread;
    }
}
```

```java
public class NormalThreadFactory implements ThreadFactory {
    private int counter = 0;
    private String name;

    public NormalThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-NormalThread_" + counter);
        counter++;
        return thread;
    }
}
```

```java
public class GroupThreadFactory implements ThreadFactory {
    private int counter = 0;
    private String name;
    private ThreadGroup group;

    public GroupThreadFactory(String name, ThreadGroup group) {
        this.name = name;
        this.group = group;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, name + "-GroupThread_" + counter);
        counter++;
        return thread;
    }
}
```

```java
public class SimpleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName() + ", Daemon: " + Thread.currentThread().isDaemon());
    }

    public static void main(String[] args) {
        DaemonThreadFactory daemonFactory = new DaemonThreadFactory("DaemonFactory");
        NormalThreadFactory normalFactory = new NormalThreadFactory("NormalFactory");
        ThreadGroup group = new ThreadGroup("Group");
        GroupThreadFactory groupFactory = new GroupThreadFactory("GroupFactory", group);

        for (int i = 0; i < 5; i++) {
            Thread daemonThread = daemonFactory.newThread(new SimpleTask());
            daemonThread.start();

            Thread normalThread = normalFactory.newThread(new SimpleTask());
            normalThread.start();

            Thread groupThread = groupFactory.newThread(new SimpleTask());
            groupThread.start();
        }
    }
}
```

Mejoras trás usar la interface `ThreadFactory` :

·	Cada tipo de hilo tiene configuraciones específicas, como ser daemon o pertenecer a un grupo.

·	La creación de hilos se gestiona en un solo lugar, facilitando cambios futuros.

·	Es más fácil monitorear y gestionar hilos cuando están organizados en grupos o tienen nombres personalizados.


# 5. GESTIÓN DE EXCEPCIONES EN HILOS

<hr />

## 5.1 - Introducción 

Los hilos pueden definir su propia función para gestionar las excepciones `unchecked` (errores de programación fatal, detectado en tiempo de ejecución y que no se puede recuperar: `NullPointerException` o `IndexOutOfBoundsException`) mediante el uso de `Thread.UncaughtExceptionHandler`.  

## 5.2 - JVM y excepciones en hilos 

1.	Cuando un hilo está a punto de terminar debido a una excepción no capturada, la JVM invoca su `UncaughtExceptionHandler`.
2.	Si no se establece un manejador personalizado, la JVM delega el manejo al grupo de hilos (`ThreadGroup`), que a su vez puede llamar a un manejador predeterminado, se imprime la pila de la traza por la consola y finaliza la ejecución del hilo que había lanzado la excepción.
3.	En una solución basada en hilos individuales; cada hilo puede tener su propio manejador.

## 5.3 - UncaughtExceptionHandler

Aunque el uso de `try-catch` es una solución para manejar excepciones, envolver cada línea de código con estos bloques no es práctico ni escalable. En cambio, `UncaughtExceptionHandler` es un mecanismo se utiliza para realizar acciones importantes, como guardar registros, cerrar recursos o realizar cierres controlados antes de que la aplicación se cierre abruptamente.

### EJEMPLO: Manejador personalizado de excepciones

- **CÓDIGO** 
```java
public class LastChanceHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Registrar en un archivo, subir a un servidor, cerrar recursos, etc.
    }
}
```

```java
Thread.setDefaultUncaughtExceptionHandler(new LastChanceHandler());
```

## 5.4 - Interrupciones de hilos

En ocasiones es necesario detener un hilo de forma ordenada y segura. No queremos “matar” al hilo de inmediato, más bien deseamos enviarle al hilo una solicitud **interrupción** que el hilo deberá atender y manejar, para terminar sus tareas en progreso y asegurando la consistencia del estado del programa. 

Usaremos el método `interrupt()`, tendremos que tener en cuenta que hilo bloqueado en operaciones como `sleep()`, `wait()`, o `join()` puede ser interrumpido mediante el método `interrupt()`, lo que genera una excepción `InterruptedException` y permite a los hilos salir del estado de bloqueo de manera controlada y manejar la interrupción.

Si el hilo no se encuentra en alguno de los estados anteriores no lanzará la `InterruptedException`. En este caso, se debe verificar explícitamente si el hilo ha sido interrumpido usando el método `interrupted()` o `isInterrupted()`):

```java
if (Thread.interrupted()) {
    // Manejo de la interrupción
}
```

```java
while (!Thread.currentThread().isInterrupted()) {
    // Lógica del bucle
}
```

Las principales diferencias entre ambos métodos son la siguientes:
- Método `Thread.interrupted()`:
  - Es un método **estático**.
  - Devuelve si el hilo actual fue interrumpido y restablece el estado de interrupción a falso.

- Método `Thread.currentThread().isInterrupted()`:
  - Es un método de **instancia**.
  - Devuelve si el hilo fue interrumpido, pero **no restablece el estado**.

Podemos usar bloques `try-catch` para restaurar el estado tras interrupción. Si se atrapa una `InterruptedException`, restaura el estado de interrupción si es necesario:

```java
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt(); // Restaura el estado de interrupción
    // Finalizar o manejar el hilo
}
```

### EJEMPLO: Finalización controlada de un hilo

- **CÓDIGO** 

```java
public class SimpleStopThread implements Runnable {
    private volatile Thread blinker; // Bandera de control

    public void stop() {
        Thread moribund = blinker; // Captura el hilo actual
        blinker = null; // Marca el hilo para detenerse
        if (moribund != null) {
            moribund.interrupt(); // Interrumpe el hilo si está bloqueado
        }
    }

    @Override
    public void run() {
        Thread thisThread = Thread.currentThread(); // Obtiene el hilo actual
        blinker = thisThread; // Asigna el hilo a la bandera de control

        while (blinker == thisThread && !thisThread.isInterrupted()) {
            try {
                System.out.println("Hilo ejecutándose...");
                Thread.sleep(1000); // Simula trabajo
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido durante el sueño");
                Thread.currentThread().interrupt(); // Restaura el estado de interrupción
                break; // Sal del bucle si el hilo fue interrumpido
            }
        }
        System.out.println("Hilo detenido de forma segura");
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleStopThread task = new SimpleStopThread();
        Thread thread = new Thread(task);

        thread.start(); // Inicia el hilo
        Thread.sleep(3000); // Simula tiempo de ejecución
        System.out.println("Solicitando detener el hilo...");
        task.stop(); // Solicita la detención del hilo
        thread.join(); // Espera a que el hilo termine
    }
}
```

- **SALIDA**

`TODO SALIDA`

# 6. PLANIFICACIÓN DE HILOS

<hr />

## 6.1 - Prioridades de hilos 

En Java, cada hilo tiene una prioridad que ayuda al sistema operativo a determinar el orden en que los hilos se programan. Puedes obtener y establecer la prioridad de un hilo utilizando métodos y constantes proporcionados por la clase `Thread`. Las prioridades de los hilos en Java están en el rango entre `MIN_PRIORITY` (1) y `MAX_PRIORITY` (10). Por defecto, el hilo principal tiene la prioridad `NORM_PRIORITY` (5), los hilos hijos heredan la prioridad del padre:

- `Thread.MIN_PRIORITY`: Especifica la prioridad mínima que puede tener un hilo.
- `Thread.NORM_PRIORITY`: Especifica la prioridad por defecto asignada a un hilo.
- `Thread.MAX_PRIORITY`: Especifica la prioridad máxima que puede tener un hilo.

Además, Java también posee métodos para establecer y obtener la prioridad de un hilo:

- `Thread.getPriority()`: Este método se utiliza para obtener la prioridad de un hilo.
- `Thread.setPriority(int priority)`: Este método se utiliza para establecer la prioridad de un hilo, aceptando un valor de prioridad y actualizando la prioridad existente con el valor dado.

### EJEMPLO: Modificar la prioridad de un hilo en Java

- **CÓDIGO** 

```java
public class EjemploPrioridadHilo extends Thread {
    public void run() {
        System.out.println("Nombre del hilo: " + Thread.currentThread().getName());
        System.out.println("Prioridad del hilo: " + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        EjemploPrioridadHilo hilo1 = new EjemploPrioridadHilo();
        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo1.start();

        EjemploPrioridadHilo hilo2 = new EjemploPrioridadHilo();
        hilo2.setPriority(Thread.MAX_PRIORITY);
        hilo2.start();
    }
}
```

- **SALIDA**

`TODO SALIDA`


## 6.2 - Hilos virtuales 

A parte de los hilos demonio, hilo principal y el resto de hilos que podamos crear, se tratan de **hilos plataforma** pero también podremos crear **hilos virtuales**.

- **Hilos de  Plataforma**: Hilos de Java asociados directamente con un hilo del sistema operativo (SO).
  - Disponen de una pila grande.
  - Pueden acceder a otros recursos mantenidos por el SO.
  - El número de hilos de plataforma es limitado.
  
- **Hilos Virtuales**: Hilos de Java que no están asociados con un hilo específico del SO.
  - Comparten los hilos del SO. Cuando un hilo virtual se bloquea, libera el hilo de plataforma, que puede ser reutilizado por otro hilo virtual.
  - Se puede crear un número ilimitado de hilos virtuales, pero con menos recursos (pila poco profunda).
  - Es importante limitar los recursos usados por los hilos virtuales (variables locales, recursividad).

Para crear hilos virutales diponemos de dos métodos:
- `Thread.startVirtualThread(Runnable task)`: Este método crea y empieza un hilo virtual directamente con la tarea especificada.
- `Thread.ofVirtual()`: Este método devuelve un constructor de hilos virtuales. Luego, puedes usar el método `start()` para iniciar el hilo con la tarea especificada.

Es importante tener en cuanta lo siguiente a la hora de decantarse por usar hilos virtuales:
- Para tareas intensivas en CPU o con un estado grande, es mejor usar hilos de plataforma.
- Los hilos virtuales son siempre de tipo Daemon.
- Tienen una prioridad fija (`Thread.NORM_PRIORITY`) que no se puede cambiar.
- No se recomienda utilizar pools de hilos con hilos virtuales.


### EJEMPLO: Creación de hilos virtuales  

- **CÓDIGO** 

```java
public class EjemploHilosVirtuales {
    public static void main(String[] args) {
        // Crear un hilo virtual usando Thread.startVirtualThread(Runnable task)
        Thread hiloVirtual1 = Thread.startVirtualThread(() -> {
            System.out.println("Hilo virtual 1 ejecutándose: " + Thread.currentThread().getName());
        });

        // Crear un hilo virtual usando Thread.ofVirtual()
        Thread hiloVirtual2 = Thread.ofVirtual().start(() -> {
            System.out.println("Hilo virtual 2 ejecutándose: " + Thread.currentThread().getName());
        });

        // Esperar a que los hilos virtuales terminen
        try {
            hiloVirtual1.join();
            hiloVirtual2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

- **SALIDA**

`TODO SALIDA`


# 7. COLECCIONES CONCURRENTES 

<hr />

## 7.1 - Colecciones concurrentes

Java proporciona una API robusta para manejar la concurrencia a través del paquete `java.util.concurrent`. Este paquete incluye varias clases e interfaces diseñadas para facilitar la programación concurrente y asegurar la integridad de los datos cuando se accede a ellos desde múltiples hilos. Son clases `thread-safe`, es decir métodos que pueden ser llamados simultáneamente por varios hilos sin que el estado interno de la clase quede inconsistente o corrupto. 

## 7.2 - Colecciones sincronizadas vs colecciones concurrentes 

- **Colecciones Sincronizadas**: Utilizan métodos sincronizados para asegurar que solo un hilo pueda acceder a la colección a la vez. Esto se puede lograr utilizando la clase Collections para envolver una colección estándar. Por ejemplo: `synchronizedCollection`, `synchronizedList`, `synchronizedMap`, `synchronizedNavigableMap`, `synchronizedNavigableSet`, `synchronizedSet`, `synchronizedSortedMap` y `synchronizedSortedSet`.

- **Colecciones Concurrentes**: Están diseñadas específicamente para la concurrencia y utilizan técnicas avanzadas como el bloqueo por segmentos o la copia en escritura para mejorar el rendimiento y la escalabilidad. Diferenciamos los siguientes subtipos:
  - **Colecciones Bloqueantes**: Estas colecciones detienen (bloquean) el hilo que intenta realizar una operación que no puede completarse inmediatamente. Esto ocurre, por ejemplo: cuando se intenta extraer un elemento de una colección vacía o cuando se intenta insertar un elemento en una colección llena. Por ejemplo: `ArrayBlockingQueue`, `PriorityBlockingQueue`, `DelayQueue`, `SynchronousQueue`, `LinkedBlockingDeque` y `LinkedTransferQueue`, 
  - **Colecciones No Bloqueantes**: Estas colecciones no detienen el hilo si la operación no puede completarse de inmediato. En su lugar: devuelven un valor especial (como null) o lanzan una excepción específica para indicar que la operación no pudo realizarse. Por ejemplo: `CopyOnWriteArrayList`, `CopyOnWriteArraySet`, `ConcurrentSkipListSet`, `ConcurrentHashMap`
y `ConcurrentSkipListMap`.

### EJEMPLO: TODO

- **CÓDIGO** 

```java
public class TODO {
    // TODO
}
```

- **SALIDA**

`TODO SALIDA`



# 8. FRAMEWORK DE EJECUCIÓN

<hr />

## 8.1 - Executor Framework 

Un `Executor` es un objeto que ejecuta tareas enviadas de tipo `Runnable`. Este proporciona una forma de desacoplar la presentación de tareas de los detalles de cómo se ejecutarán, incluyendo el uso de hilos, la planificación, etc.

-	El método `execute(Runnable task)` toma un objeto `Runnable` y lo ejecuta en algún momento en el futuro.

```java
Executor executor = Executors.newSingleThreadExecutor();
executor.execute(() -> {
    System.out.println("Tarea ejecutada");
});
```

## 8.2 - Interface Callable<V> 

La interfaz `Callable<V>` es similar a `Runnable`, representa una tarea asíncrona que puede devolver un resultado y lanzar una excepción. La interfaz `Callable` es bastante simple y contiene un único método sin argumentos llamado `call()`, que se invoca para ejecutar la tarea asíncrona.
-	Si la tarea se ejecuta de forma asíncrona, el resultado se propaga típicamente de vuelta al creador de la tarea a través de un `Future`.

```java
public interface Callable<V> {
    V call() throws Exception;
}
```


## 8.3 - Interface Future<V>

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

## 8.4 - ExecutorService

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

## 8.5 - ScheduledExecutorService

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

## 8.6 - ThreadPool

Los `ThreadPools` son una técnica para gestionar la ejecución de múltiples tareas en hilos reutilizables, optimizando el uso de recursos y mejorando el rendimiento de las aplicaciones concurrentes. 
- **Single Thread Executor** crea un único hilo para ejecutar las tareas de manera secuencial y evitando problemas de concurrencia. Se crea mediante el método `newSingleThreadExecutor()`.
- **Fixed Thread Pool** crea un número fijo de hilos que se reutilizan para ejecutar las tareas. Si todas las hilos están ocupados, las tareas adicionales se colocan en una cola de tareas sin límite (`LinkedBlockingQueue`) hasta que un hilo esté disponible, es útil cuando se desea limitar el número de hilos concurrentes para evitar el consumo excesivo de recursos y se invoca con el método `newFixedThreadPool(int nThreads)`.
- **Cached Thread Pool** crea nuevos hilos según sea necesario, pero reutiliza los hilos existentes si están disponibles (si no se usan durante 60 segundos se terminan y se eliminan del pool), usa una cola síncrona (`SynchronousQueue`) y puede crecer dinámicamente hasta un número muy grande de hilos (`Integer.MAX_VALUE`). Este tipo de pool es adecuado para ejecutar muchas tareas cortas y de vida corta, se invoca con el método `newCachedThreadPool()`.

Además de los métodos, mencionados en el `ExecutorService` mediante `isShutdown()` podremos saber si el pool ha sido parado y con `isTerminated()` si se ha completado la parada.



# 2. BIBLIOGRAFÍA 

<hr />

## 2.1 - TODO 

TODO

