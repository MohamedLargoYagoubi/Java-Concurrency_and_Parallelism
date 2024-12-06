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

## 5.1 - TODO

TODO 


# 2. BIBLIOGRAFÍA 

<hr />

## 2.1 - TODO 

TODO

