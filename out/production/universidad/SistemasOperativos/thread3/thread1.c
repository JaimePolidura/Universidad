#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>

void* hilo1Func();
void* hilo2Func();
void sigurgHandler(int);

pid_t id_proc;
pthread_t hilo1, hilo2;
pthread_mutex_t lock;

int main(void){
    signal(SIGURG, sigurgHandler);

    id_proc = getppid();

    pthread_create(&hilo1, NULL, &hilo1Func, NULL);
    pthread_create(&hilo2, NULL, &hilo2Func, NULL);
    pthread_join(hilo1, NULL);
    pthread_join(hilo2, NULL);

    return 0;
}

void* hilo1Func(){
    while (1){
        sleep(5);
        puts("Vamos a para al hilo 2");
        pthread_mutex_lock(&lock);
        kill(id_proc, SIGURG);
        pthread_mutex_unlock(&lock);
    }   
}

void* hilo2Func(){
    while(1){
        puts("Soy el hilo 2");
        sleep(2);
    }
}

void sigurgHandler(int signum){
    puts("Entramos en la funcin manejeradora");
    pthread_mutex_lock(&lock);
    pthread_mutex_unlock(&lock);
}

long pow(int base, int exp){
    return powRecursive(base, exp, 1);
}

long powRecursive(int base, int exp, int act){
    return exp == 0 ? act : base * powRecursive(base, exp - 1, act * base);
}