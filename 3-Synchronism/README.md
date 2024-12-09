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

La sincronización en Java es un mecanismo que permite controlar el acceso de múltiples hilos a recursos compartidos para evitar problemas de consistencia de datos. Los hilos en Java se comunican principalmente a través de campos compartidos y los objetos de esos campos.

## 1.2 - Reglas de sincronización

Java

## 1.3 - Clases Thread-Safe
Java


# 2. MECANISMOS BÁSICOS DE SINCRONIZACIÓN JAVA 

<hr />

## 2.1 - Exclusión Mútua 

add

## 2.2 - Variables de condición

add

## 2.3 - Semáforos

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
