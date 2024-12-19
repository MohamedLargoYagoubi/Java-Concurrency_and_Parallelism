<p align="center">
  <img width=100% src="https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/blob/main/media/banner.gif" /> 
</p>

<h1 align="center">INTRODUCCIÓN</h1>

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

1. [Java Threads](https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/tree/main/1-Java_Threads)

   ↳ 📁 **Ejercicio - Sesión 1:** SumatorioSec.zip
   
   ↳ 📁 **Ejercicio - Sesión 2:** ProcesarFicheros.zip
   
2. [APIs de Concurrencia de Alto Nivel](https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/tree/main/2-High_Level_Concurrent_APIs)


   ↳ 📁 **Ejercicio - Sesión 3:** SumatorioSec.zip
   
   ↳ 📁 **Ejercicio - Sesión 4:** MergeSort.zip
   

3. [Sincronización](https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/tree/main/3-Synchronism)


   ↳ 📁 **Ejercicio - Sesión 5:** SumatorioConc.zip (con Athomic)
   
   ↳ 📁 **Ejercicio - Sesión 6:** SumatorioConc2.zip
   

4. [PThreads](https://github.com/MohamedLargoYagoubi/Java-Concurrency_and_Parallelism/tree/main/4-Pthreads)


   ↳ 📁 **Ejercicio - Sesión 7:** Sumatorio.zip
   
   ↳ 📁 **Ejercicio - Sesión 8:** ProductorConsumidor.zip
   

# 1. INTRODUCCIÓN A LA CONCURRENCIA

<hr />

## 1.1 - Definición de la concurrencia 

La concurrencia se define como la capacidad de un sistema para descomponer un programa en partes que pueden ser ejecutadas independientemente unas de otras. No significa necesariamente que estas partes se ejecuten de forma simultánea (paralelismo), sino que el sistema puede gestionarlas de manera intercalada, compartiendo recursos y tiempos de ejecución de manera eficiente.

Por lo tanto, un hilo o thread es una unidad pequeña de procesamiento que puede ser gestionada de manera independiente por el sistema operativo o por bibliotecas a nivel de usuario. Los hilos pueden ejecutarse de manera intercalada en procesadores de un solo núcleo, o en paralelo en sistemas con múltiples núcleos, creando la ilusión de que todos los hilos están en ejecución simultánea.

## 1.2 - Procesos e hilos

Un proceso es una instancia de un programa en ejecución y posee los siguientes elementos:
-	**Stack o pila:** contiene un historial de ejecución donde se almacenan variables locales, direcciones de retorno y datos de la función en ejecución.
-	**Heap o montón:** es el área de memoria dinámica (creación de objetos).
-	**Espacio de datos y código:** segmentos de datos (globales, estáticos) y código (instrucciones ejecutables).

Un proceso posee memoria aislada de otro proceso, y para compartir datos se requerirá de mecanismos como pipes o memorias compartidas.
Por otro lado, los hilos, procesos ligeros o mini procesos, comparten el heap y los datos globales del proceso, pero tienen stacks individuales. Es decir, un hilo tiene un historial de ejecución distinto al resto de los hilos. Además, tampoco están aislados unos de otros, lo que puede provocar problemas de sincronización como condiciones de carrera.


## 1.3 - Simultáneamente y al mismo tiempo  

Aunque los términos “mismo tiempo” y “simultáneamente” puedan parecer sinónimos, no son equivalentes y tienen matices importantes:
-	**Mismo tiempo:** Dos o más procesos ocurren en el mismo periodo de tiempo. Esto no implica necesariamente que las tareas que ejecuta cada proceso comiencen y terminen al mismo tiempo.
-	**Simultáneamente:** Dos o más procesos se ejecutan literalmente al mismo tiempo, lo cual implica que diferentes recursos de hardware están trabajando en paralelo. No requiere que las tareas comiencen ni terminen al mismo tiempo, pero sí que se ejecuten en el mismo instante en diferentes núcleos o procesadores.

> [!TIP]
> **ANALOGÍA:**
> - Si dos personas (procesos) dialogan en el mismo tiempo, significa que cada intervención (tarea) de ambas personas se intercala, pero no se solapan. Esto es como la concurrencia.
> - Si dos personas (procesos) dialogan simultáneamente, significa que ambas hablan exactamente al mismo tiempo, como ocurre en el paralelismo.



## 1.4 - Tipos de aplicaciones

Definiremos la siguiente clasificación de los programas en función de su modelo de ejecución y gestión de tareas: 

<div align="center">

  | TIPO DE APLICACIÓN | DEFINICIÓN | REQUERIMIENTOS DE HARDWARE |
| :-: | :-: | :-: |
| **👤 Secuencial**  | Tarea única | 1 procesador o núcleo |
| **🧵 Concurrente**  | Tareas intercaladas | 1 procesador o núcleo |
| **👥 Paralela** | Tareas simultáneas  | Múltiples núcleos o procesador  |
| **🌐 Distribuida**  | Tareas distribuidas  | Sistemas en red |

</div>


### **1. Aplicaciones secuenciales:**

Orden estrictamente lineal, cada paso del programa debe completarse antes de que se inicie el siguiente. Todas las operaciones se ejecutan en un único flujo de control (hilo de ejecución) y en un único procesador o núcleo.

> [!TIP]
> **ANALOGÍA:**
> 
> Un cocinero sigue una receta paso a paso. Solo prepara un plato a la vez y no comienza un nuevo paso de la receta hasta que termina el anterior. Por ejemplo, primero corta las verduras, luego las cocina y finalmente emplata el plato.

### **2. Aplicaciones concurrentes:**

Formadas por múltiples tareas independientes que se intercalan y ejecutan al mismo tiempo, pero no necesariamente se ejecutan simultáneamente y suelen gestionarse por múltiplos hilos o procesos ligeros.

> [!TIP]
> **ANALOGÍA:**
> 
> El cocinero ahora gestiona múltiples recetas al mismo tiempo. Mientras las verduras se cocinan en el horno, comienza a cortar otros ingredientes o mezcla una salsa. Aunque trabaja en varias tareas a la vez, solo realiza una acción a la vez (por ejemplo, no puede cortar y mezclar simultáneamente).

### **3. Aplicaciones paralelas:**

Múltiples tareas se ejecutan simultáneamente en múltiples procesadores o núcleos. Estas aplicaciones se ejecutan simultáneamente, es decir múltiples instrucciones se ejecutan al mismo tiempo.

> [!TIP]
> **ANALOGÍA:**
> 
> Tenemos varios cocineros trabaja en una tarea diferente de forma simultánea. Por ejemplo, mientras un cocinero corta los vegetales, otro mezcla la salsa y un tercero cocina la carne. Cada tarea avanza en paralelo, acelerando el tiempo total de preparación.

### **4. Aplicaciones distribuidas:**

Los programas se ejecutan en sistemas independientes y separados físicamente (como en diferentes máquinas conectadas por una red). Los programas deben compartirse la infromación entre ellos mediante mensajes.

> [!TIP]
> **ANALOGÍA:**
> 
> Cada cocinero tiene su propia cocina y se encarga de una parte específica del menú. Por ejemplo, un cocinero en una cocina prepara la ensalada, otro elabora la sopa en otra ubicación, y un tercero hornea el postre en otro lugar. Al final, los platos se reúnen para servir la comida completa.


# 2. BIBLIOGRAFÍA 

<hr />

## 2.1 - TODO 

TODO

