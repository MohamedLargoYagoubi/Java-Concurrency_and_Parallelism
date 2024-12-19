#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define Tamany 10
#define MAX_TOTAL 1000  // Número total de elementos a producir

// Variables globales de sincronización
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t buffer_lleno = PTHREAD_COND_INITIALIZER;
pthread_cond_t buffer_vacio = PTHREAD_COND_INITIALIZER;

// Variables de estado del buffer
int in = 0, out = 0, producidas = 0, totalproducidas = 0;
int buffer[Tamany] = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

void* HiloProductor(int *Par) {
    int error = 1;
    int elementos_sumados = 0;
    
    fprintf(stdout, "Hilo %ld Productor iniciado.\n", pthread_self());
    
    while (totalproducidas < MAX_TOTAL) {
        // Bloquear mutex
        pthread_mutex_lock(&mutex);
        
        // Esperar si el buffer está lleno
        while (producidas == Tamany) {
            pthread_cond_wait(&buffer_vacio, &mutex);
        }
        
        // Producir elemento
        buffer[in] = totalproducidas + 1;
        totalproducidas++;
        producidas++;
        
        // Mostrar estado parcial cada M/10 elementos
        elementos_sumados++;
        if (elementos_sumados % (MAX_TOTAL/10) == 0) {
            printf("Productor: Parcial %d elementos producidos\n", elementos_sumados);
        }
        
        // Actualizar índice de entrada circular
        in = (in + 1) % Tamany;
        
        // Señalar que hay elemento disponible
        pthread_cond_signal(&buffer_lleno);
        
        // Liberar mutex
        pthread_mutex_unlock(&mutex);
    }
    
    // Señalar fin de producción
    pthread_mutex_lock(&mutex);
    pthread_cond_broadcast(&buffer_lleno);
    pthread_mutex_unlock(&mutex);
    
    int* resultado = malloc(sizeof(int));
    *resultado = error;
    return resultado;
}

void* HiloConsumidor(int *Par) {
    int error = 2;
    int elementos_consumidos = 0;
    int suma_total = 0;
    
    fprintf(stdout, "Hilo %ld Consumidor iniciado.\n", pthread_self());
    
    while (1) {
        // Bloquear mutex
        pthread_mutex_lock(&mutex);
        
        // Esperar si el buffer está vacío
        while (producidas == 0 && totalproducidas < MAX_TOTAL) {
            pthread_cond_wait(&buffer_lleno, &mutex);
        }
        
        // Condición de terminación
        if (producidas == 0 && totalproducidas >= MAX_TOTAL) {
            pthread_mutex_unlock(&mutex);
            break;
        }
        
        // Consumir elemento
        int elemento = buffer[out];
        suma_total += elemento;
        buffer[out] = -1;
        out = (out + 1) % Tamany;
        producidas--;
        
        // Mostrar estado parcial cada M/10 elementos
        elementos_consumidos++;
        if (elementos_consumidos % (MAX_TOTAL/10) == 0) {
            printf("Consumidor: Parcial %d elementos. Suma acumulada: %d\n", 
                   elementos_consumidos, suma_total);
        }
        
        // Señalar que hay espacio libre
        pthread_cond_signal(&buffer_vacio);
        
        // Liberar mutex
        pthread_mutex_unlock(&mutex);
    }
    
    printf("Consumidor: Suma total final: %d\n", suma_total);
    
    int* resultado = malloc(sizeof(int));
    *resultado = error;
    return resultado;
}

int main() {
    pthread_attr_t attr;
    pthread_t tid[2];
    int par;
    int *Estado, *Estado2;

    // Inicializar atributos de los hilos
    pthread_attr_init(&attr);

    // Crear hilos Productor y Consumidor
    par = 1;
    if (pthread_create(&tid[0], &attr, (void*)HiloProductor, (void*)&par) != 0) {
        perror("Hilo Productor");
        exit(-1);
    }

    par = -22;
    if (pthread_create(&tid[1], &attr, (void*)HiloConsumidor, (void*)&par) != 0) {
        perror("Hilo Consumidor");
        exit(-1);
    }

    // Esperar a que los hilos terminen
    pthread_join(tid[0], (void**)&Estado);
    pthread_join(tid[1], (void**)&Estado2);

    // Mostrar estados finales
    printf("Estado Hilo Productor %d.\n", *Estado);
    printf("Estado Hilo Consumidor %d.\n", *Estado2);

    // Liberar memoria de los estados
    free(Estado);
    free(Estado2);

    // Destruir mutex y variables de condición
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&buffer_lleno);
    pthread_cond_destroy(&buffer_vacio);

    return 0;
}