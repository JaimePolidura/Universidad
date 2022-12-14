#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>

#define LIMITE 1000000

void* funcion1();
void* funcion2();

int numeroIntroducidoPorPantalla = 0;

pthread_mutex_t lock;
pthread_t hilo1, hilo2;

int main(void){
	pthread_mutex_init(&lock, NULL);

	pthread_create(&hilo1, NULL, &funcion1, NULL);
	pthread_create(&hilo2, NULL, &funcion2, NULL);	
	
	pthread_join(hilo1, NULL);
	pthread_join(hilo2, NULL);

	printf("El valor de la variable es %d\n", numeroIntroducidoPorPantalla);
	pthread_mutex_destroy(&lock);
	
	return 0;
}

void* funcion1(){
	printf("Soy el proceso %d en el hilo %d\n", getpid(), (unsigned int)pthread_self());
	
	for(int i = 0; i < LIMITE; i++){
		pthread_mutex_lock(&lock);
		numeroIntroducidoPorPantalla = numeroIntroducidoPorPantalla + 1;
		pthread_mutex_unlock(&lock);
	}	
}

void* funcion2(){
	printf("Soy el proceso %d en el hilo %d\n", getpid(), (unsigned int)pthread_self());
	
	for(int i = 0; i < LIMITE; i++){
		pthread_mutex_lock(&lock);
		numeroIntroducidoPorPantalla = numeroIntroducidoPorPantalla - 1;
		pthread_mutex_unlock(&lock);
	}
}