#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <signal.h>

int numeroIntroducidoPorPantalla;

void onNumeroNuevoEscribeHiloHandler(int);

void* masterFuncion();
void* primoFuncion();
void* escribeFuncion();
void* fibonacciFuncion();

void fibonacci(int);
int esPrimo(int);
long factorial(int);

pthread_t masterHilo, primoHilo, escribeHilo, fibonacciHilo;

int main(void){
    pthread_create(&masterHilo, NULL, &masterFuncion, NULL);
    pthread_create(&primoHilo, NULL, &primoFuncion, NULL);
    pthread_create(&escribeHilo, NULL, &escribeFuncion, NULL);
    pthread_create(&fibonacciHilo, NULL, &fibonacciFuncion, NULL);

    pthread_join(masterHilo, NULL);

	return 0;
}

void* masterFuncion(){
    while(1){
        printf("[Master] Introduce unn numero: ");
        scanf("%d", &numeroIntroducidoPorPantalla);

        if(numeroIntroducidoPorPantalla == -1) {
            exit(0);
        }

        raise(SIGUSR1);
    }
}

void onNumeroNuevoEscribeHiloHandler(int sigsum){
    long factorialDelNumero = factorial(numeroIntroducidoPorPantalla);

    printf("[Escribe]: El factorial es %lu\n", factorialDelNumero);
}

void* escribeFuncion(){
    signal(SIGUSR1, onNumeroNuevoEscribeHiloHandler);
}

void* primoFuncion(){
}

void* fibonacciFuncion(){
}



void fibonacci(int n){
    unsigned long int i, t1 = 0, t2 = 1, siguiente;

    for(int i = 1; i <= n; ++i){
        printf("%lu, ", t1);
        siguiente = t1 + t2;
        t2 = siguiente;
    }

    return;
}

long factorial(int num){
    return num == 0 ? 1 : num * factorial(num - 1);
}

int esPrimo(int num){
    int flag = 1;
    for(int i = 2; i < num; i++){
        if(num % i == 0){
            flag = 0;
            break;
        }
    }

    return flag;
}
