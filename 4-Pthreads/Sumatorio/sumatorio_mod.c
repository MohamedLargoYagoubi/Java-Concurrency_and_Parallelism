#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define DEFAULT_N 100
#define DEFAULT_THREADS 4

// Estructura para pasar argumentos a los hilos
typedef struct {
    long int thread_id;     // ID del hilo
    long int start;         // Inicio del rango a sumar
    long int end;           // Fin del rango a sumar
    long int partial_sum;   // Suma parcial calculada por el hilo
} ThreadData;

// Función de suma para cada hilo
void* thread_summation(void* arg) {
    ThreadData* data = (ThreadData*)arg;
    long int sum = 0;

    // Calcular suma parcial
    for (long int x = data->start; x <= data->end; x++) {
        sum += x;
    }

    // Guardar resultado parcial
    data->partial_sum = sum;

    return NULL;
}

// Función para distribuir trabajo entre hilos
long int concurrent_sum(long int N, int num_threads) {
    pthread_t* threads;
    ThreadData* thread_data;
    long int total_sum = 0;
    long int range_per_thread;
    int i;

    // Validar número de hilos
    if (num_threads <= 0) {
        num_threads = DEFAULT_THREADS;
    }

    // Asignar memoria para hilos y datos de hilos
    threads = malloc(num_threads * sizeof(pthread_t));
    thread_data = malloc(num_threads * sizeof(ThreadData));

    if (!threads || !thread_data) {
        fprintf(stderr, "Error: No se pudo asignar memoria\n");
        exit(1);
    }

    // Calcular rango para cada hilo
    range_per_thread = N / num_threads;
    long int remainder = N % num_threads;

    // Crear hilos
    for (i = 0; i < num_threads; i++) {
        // Calcular inicio y fin para cada hilo
        thread_data[i].thread_id = i;
        thread_data[i].start = i * range_per_thread + 1;
        
        // Distribución equitativa de rangos residuales
        if (i < remainder) {
            thread_data[i].start += i;
            thread_data[i].end = thread_data[i].start + range_per_thread;
        } else {
            thread_data[i].start += remainder;
            thread_data[i].end = thread_data[i].start + range_per_thread - 1;
        }

        // Crear hilo
        if (pthread_create(&threads[i], NULL, thread_summation, &thread_data[i]) != 0) {
            fprintf(stderr, "Error: No se pudo crear el hilo %d\n", i);
            exit(1);
        }
    }

    // Esperar a que todos los hilos terminen y sumar resultados
    for (i = 0; i < num_threads; i++) {
        pthread_join(threads[i], NULL);
        total_sum += thread_data[i].partial_sum;
    }

    // Liberar memoria
    free(threads);
    free(thread_data);

    return total_sum;
}

int main(int argc, char *argv[]) {
    long int N;
    int num_threads;
    long int total_sum;

    // Parsear argumentos
    if (argc > 1)
        N = atol(argv[1]);
    else
        N = DEFAULT_N;

    if (argc > 2)
        num_threads = atoi(argv[2]);
    else
        num_threads = DEFAULT_THREADS;

    // Calcular suma concurrente
    total_sum = concurrent_sum(N, num_threads);

    // Imprimir resultados
    printf("Sumatorio concurrente de 1 a %ld con %d hilos: %ld\n", 
           N, num_threads, total_sum);

    return 0;
}