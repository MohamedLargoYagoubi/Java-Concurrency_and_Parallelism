/* 
 * SumarConcurrent.c - Aplicación multi-hilo para sumar un rango de números
 * 
 * Usage: SumarConcurrent [N=100] [Threads=5]
 */

#include <stdio.h>
#include <stdlib.h>

#define DDefaultN 100

long int Sumar(long int inicio, long int fin);

int main(int argc, char *argv[])
{
  	long int N, SumaTotal;
	
	if (argc>1)
		N = atol(argv[1]);
	else
		N = DDefaultN;
	 
	SumaTotal = Sumar(1, N);

	printf("Sumatorio 1 a %ld: %ld.\n", N, SumaTotal);

  	exit(0);
}


long int Sumar(long int inicio, long int fin)
{
	long int x, suma;
	
	suma = 0;
	for(x=inicio; x<=fin; x++)
		suma += x;

  	return (suma);
}
