#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>

#define LIMITE 5

int isThreadLastThreadRemaining(int);
void* funcion();

//Array donde guardamos los hilos
pthread_t hilos[LIMITE];

//Posicion del ultimo hilo en el array
int lastThreadRemainingPosition = 0;
int startKillingThreads = 0;

pthread_mutex_t lastThreadRemainingPositionLock;

int main(void){
	pthread_mutex_init(&lastThreadRemainingPositionLock, NULL);
    
    //Creamos los hilos
    for(int i = 0; i < LIMITE; i++){
        pthread_t hilo;
        hilos[i] = hilo;
        lastThreadRemainingPosition = i;

        //Le pasamos a la funcion del hilo, la posicion del hilo en el array
        pthread_create(&hilo, NULL, &funcion, &i);

        sleep(1);
    }

    //Si el usuario pone algo por texto ponemos startKillingThreads a 1, como si fuese un booleano
    //Esta variable nos indicara si hay que empeza a matar los hilos, si su valor es 1
    scanf("%d", &startKillingThreads);
    puts(" ");
    
    //Eperamos a los hilos
    for(int i = 0; i < LIMITE; i++) {
        pthread_join(hilos[i], NULL);
    }

    //Al poner esto en 1 decimos que se pueda empezar a matar a los hilos
    startKillingThreads = 1;

    puts("All done");
	pthread_mutex_destroy(&lastThreadRemainingPositionLock);

	return 0;
}

void* funcion(void* position){
    int thisThreadPosition = *(int*) position;

	printf("%d Running thread %d\n", thisThreadPosition, (unsigned int)pthread_self());   

    //Esperamos hasta que se puede empezar a matar a los hilos
    while(startKillingThreads == 0);

    //Esperamos al turno de "matar" al hilo que ejecuta esta funcion
    while(isThreadLastThreadRemaining(thisThreadPosition) != 1);

    pthread_mutex_lock(&lastThreadRemainingPositionLock);

    //Restamos 1 a la variable que alamacenara el hilo a borrar
    lastThreadRemainingPosition = lastThreadRemainingPosition - 1;    
	printf("%d Stopped thread %d\n", thisThreadPosition, (unsigned int)pthread_self());   
    
	pthread_mutex_unlock(&lastThreadRemainingPositionLock);

    return 0;
}


//Utlizamos mutex para evitar condiciones de carrera
int isThreadLastThreadRemaining(int threadPosition){
    pthread_mutex_lock(&lastThreadRemainingPositionLock);
    int isSame = (threadPosition - lastThreadRemainingPosition) == 0 ? 1 : 0;
	pthread_mutex_unlock(&lastThreadRemainingPositionLock);

    return isSame;
}