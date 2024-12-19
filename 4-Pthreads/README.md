<p align="center">
  <img width=100% src="https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/blob/main/media/banner.gif" /> 
</p>

<h1 align="center">SYNCHRONISM</h1>

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


# 1. INTRODUCCIÓN A LA CONCURRENCIA 

<hr />

## 1.1 - Sincronización en Java

La **sincronización** en Java es un mecanismo que permite controlar el acceso de múltiples hilos a recursos compartidos para evitar problemas de consistencia de datos. Los hilos en Java se comunican principalmente a través de campos compartidos y los objetos de esos campos.

## 1.2 - Reglas de sincronización

Las reglas de sincronización en Java aseguran que los hilos accedan a los recursos compartidos de manera ordenada y segura:

"Si varios hilos acceden a un recurso común y uno de ellos lo modifica, todos los hilos deben sincronizar su acceso al recurso compartido." ⚠️ TODO CENTRAR

## 1.3 - Zona crítica y condiciones de carrera

- La **zona crítica** es una sección del código que accede a recursos compartidos y que debe ser ejecutada por un solo hilo a la vez para evitar inconsistencias.
- Las **condiciones de carrera** ocurren cuando dos o más hilos acceden a una zona crítica simultáneamente, causando resultados impredecibles. La sincronización ayuda a prevenir estas condiciones asegurando que solo un hilo pueda ejecutar la zona crítica a la vez.

```java
public class Contador {
    private int cuenta = 0;

    // INICIO ZONA CRÍTICA
    public void incrementar() { 
        cuenta++;
    } 
    // FIN ZONA CRÍTICA

    public int obtenerCuenta() {
        return cuenta;
    }
}
```

## 1.4 - Clases Thread-Safe
Las clases **Thread-Safe** son aquellas que pueden ser utilizadas por múltiples hilos simultáneamente sin causar problemas de consistencia de datos:
-	**Objetos sin estado o inmutabilidad:**  Las clases inmutables son intrínsecamente seguras para hilos porque su estado no puede cambiar después de su creación.
-	**Clases atómicas:**  Utilizan operaciones atómicas para asegurar la consistencia de datos sin necesidad de sincronización explícita.
-	**Colecciones concurrentes:** Proporcionan implementaciones seguras para hilos de las colecciones estándar.
-	**Sincronización:** Delimita bloques de código para que solamente puedan ser accedidos por un hilo a la vez.



# 2. MECANISMOS BÁSICOS DE SINCRONIZACIÓN JAVA 

<hr />

## 2.1 - Exclusión Mútua 

La **exclusión mutua** asegura que solo un hilo acceda a una sección crítica del código a la vez, evitando interferencias y garantizando la consistencia de los datos.

## 2.2 - Variables de condición

Las **variables atómicas** proporcionan operaciones atómicas sobre variables, lo que significa que estas operaciones se completan en un solo paso sin ser interrumpidas.
- **Clases atómicas:** `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, `AtomicReference`, etc.
Los métodos que proporcionan son los siguientes:
- `T get()` devuelve el valor actual de la variable atómica.
-	`T incrementAndGet()` incrementa el valor atómico en uno y devuelve el nuevo valor.
-	`T addAndGet(T delta)` añade el valor delta al valor atómico y devuelve el nuevo valor.
-	`void set(T value)` establece el valor atómico a value.
-	`boolean compareAndSet(T expectedValue, T newValue)` establece el valor atómico a `newValue` solo si el valor actual es igual a expectedValue. Devuelve true si la operación tuvo éxito, de lo contrario, false.

### EJEMPLO: Uso de los métodos de las clases atómicas
- **CÓDIGO** 
```java
public class AtomicCombinedExample {
    private AtomicInteger atomicInt = new AtomicInteger(10);

    public void demonstrateMethods() {
        // Estado inicial: 10
        System.out.println("Valor inicial: " + atomicInt.get()); // Imprime: 10

        // Incrementa en 1 y devuelve el nuevo valor
        int newValue = atomicInt.incrementAndGet();
        // Estado después de incrementAndGet(): 11
        System.out.println("Después de incrementAndGet(): " + newValue); // Imprime: 11

        // Añade 5 y devuelve el nuevo valor
        newValue = atomicInt.addAndGet(5);
        // Estado después de addAndGet(5): 16
        System.out.println("Después de addAndGet(5): " + newValue); // Imprime: 16

        // Establece el valor a 20
        atomicInt.set(20);
        // Estado después de set(20): 20
        System.out.println("Después de set(20): " + atomicInt.get()); // Imprime: 20

        // Compara y establece: si el valor actual es 20, cámbialo a 30
        boolean wasUpdated = atomicInt.compareAndSet(20, 30);
        // Estado después de compareAndSet(20, 30): 30
        System.out.println("compareAndSet(20, 30) fue exitoso: " + wasUpdated); // Imprime: true
        System.out.println("Después de compareAndSet(20, 30): " + atomicInt.get()); // Imprime: 30

        // Intento de compareAndSet fallido: si el valor actual es 20, cámbialo a 40
        wasUpdated = atomicInt.compareAndSet(20, 40);
        // Estado después de compareAndSet(20, 40): 30 (sin cambios)
        System.out.println("compareAndSet(20, 40) fue exitoso: " + wasUpdated); // Imprime: false
        System.out.println("Después de compareAndSet(20, 40): " + atomicInt.get()); // Imprime: 30
    }

    public static void main(String[] args) {
        AtomicCombinedExample example = new AtomicCombinedExample();
        example.demonstrateMethods();
    }
}
```

- **SALIDA**

`TODO SALIDA`


## 2.3 - Métodos synchronized

Los **bloques synchronized** permiten sincronizar solo una parte del código dentro de un método, lo que puede mejorar el rendimiento al reducir el tiempo que un hilo mantiene el bloqueo. 

- **Exclusión Mutua:** Se garantiza que solo un hilo pueda ejecutar un método sincronizado en un objeto a la vez. Esto evita que dos invocaciones de métodos sincronizados del mismo objeto se mezclen.

```java
public class SynchronizedExample {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

- **Visibilidad de Cambios:** Cuando un método sincronizado termina, se garantiza que todos los hilos verán los cambios realizados sobre el objeto. Esto asegura la consistencia de los datos.
- **Flag de Bloqueo:**  Cada objeto en Java tiene un "flag de bloqueo" que se controla con la palabra clave synchronized. Este flag permite accesos exclusivos al objeto, asegurando la exclusión mutua. Además, cada objeto tiene una cola de espera para los hilos que intentan adquirir el bloqueo.
- **Bloqueo Intrínseco:** Cuando un hilo invoca un método sincronizado, adquiere el bloqueo intrínseco del objeto correspondiente. Si el método es estático, adquiere el bloqueo intrínseco de la clase. Es recomendable usar objetos finales como bloqueos para garantizar la coherencia.

```java
public class SynchronizedStaticExample {
    private static int count = 0;

    public static synchronized void increment() {
        count++;
    }

    public static synchronized int getCount() {
        return count;
    }
}
```

- **Liberación del Bloqueo:** El flag de bloqueo de un objeto se libera cuando: el hilo termina el método sincronizado o el método sincronizado lanza una excepción.
- **Deadlock:** Un deadlock ocurre cuando dos hilos están esperando indefinidamente por los flags de bloqueo que posee el otro. Esto se puede evitar mediante: decidiendo un orden de obtención de los bloqueos, siguiendo rigurosamente ese orden, liberando los bloqueos en orden inverso y evitando la sincronización anidada.

```java
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                // Código
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            synchronized (lock1) {
                // Código
            }
        }
    }
}
```

## 2.4 - Objetos synchronized

Para sincronizar una porción de código, debemos asociar un atributo u objeto sobre el que se requiere el bloqueo intrínseco. Este objeto se conoce como "testigo" y puede ser cualquier objeto en Java.

```java
public class SynchronizedBlockExample {
    private final Object lock = new Object();
    private int count = 0;

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
```

- **Sincronización de Porciones de Métodos:** Podemos sincronizar solo partes específicas de un método, lo que permite una sincronización más granular y eficiente.
- **Sincronización con Atributos Estáticos:** Si el testigo es un atributo estático, se bloquea toda la clase, lo que significa que todos los hilos que intenten acceder a métodos sincronizados en la clase se bloquearán hasta que el bloqueo se libere.

```java
public class StaticSynchronizationExample {
    private static final Object lock = new Object();
    private static int count = 0;

    public static void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public static int getCount() {
        return count;
    }
}
```

## 2.5 - Locks

A partir de JDK 1.5, Java introdujo la interfaz Lock en el paquete `java.util.concurrent.locks`, proporcionando una forma más flexible y controlada de manejar la sincronización de hilos en comparación con los métodos synchronized. 

El uso de candado ofrece las siguientes ventajas:
- **Mayor Flexibilidad:** Los locks permiten un control más granular sobre la sincronización, incluyendo la posibilidad de intentar adquirir un lock sin bloquear indefinidamente.
- **Condiciones de Bloqueo:** Los locks pueden crear objetos de condición (`Condition`), que facilitan la comunicación entre hilos.

Los principales métodos de la interfaz `Lock` son:
- `void lock()` adquiere el bloqueo si está disponible. Si no lo está, el hilo se bloquea hasta que el bloqueo se libere.
- `void lockInterruptibly()` similar a `lock()`, pero permite que el hilo sea interrumpido mientras espera el bloqueo, lanzando una InterruptedException.
- `boolean tryLock()` intenta adquirir el bloqueo inmediatamente y devuelve true si tiene éxito; de lo contrario, devuelve false.
- `boolean tryLock(long timeout, TimeUnit unit)` intenta adquirir el bloqueo dentro de un tiempo especificado. Devuelve true si tiene éxito; de lo contrario, devuelve false.
- `void unlock()` libera el bloqueo.

### EJEMPLO: Uso de Locks 
- **CÓDIGO** 
```java
public class LockExample {
    private final Lock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void incrementWithTimeout() throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                count++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("No se pudo adquirir el bloqueo dentro del tiempo especificado.");
        }
    }

    public void incrementInterruptibly() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        LockExample example = new LockExample();

        // Incrementar usando lock()
        example.increment();
        System.out.println("Count después de increment(): " + example.getCount());

        // Incrementar usando tryLock() con timeout
        try {
            example.incrementWithTimeout();
            System.out.println("Count después de incrementWithTimeout(): " + example.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Incrementar usando lockInterruptibly()
        try {
            example.incrementInterruptibly();
            System.out.println("Count después de incrementInterruptibly(): " + example.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

- **SALIDA**

`TODO SALIDA`

Los locks también permiten crear objetos de condición (Condition) que facilitan la comunicación entre hilos. Un `Condition` se crea a partir de un lock y proporciona métodos como `await()`, `signal()`, y `signalAll()` para gestionar la espera y notificación de hilos.


## 2.6 - Semáforos

aadad

# 3. PATRONES SINCRONIZACIÓN DE ALTO NIVEL 

<hr />


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

1. Crea
