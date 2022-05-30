#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>

pthread_t calculatorThread;
void* calculatorThreadFunc();
void oNewOperation(int);
void onErrorWhileExecutingOperation(int);

pthread_mutex_t lock;

void restart();

long n1, n2, result;
int running = 1;

int main(){
    pthread_create(&calculatorThread, NULL, &calculatorThreadFunc, NULL);
	pthread_mutex_init(&lock, NULL);

    signal(SIGFPE, onErrorWhileExecutingOperation);

    while(running != 0){
        printf("First number: ");
        scanf("%lu", &n1);
        printf("Second number number: ");
        scanf("%lu", &n2);

        raise(SIGUSR1);
        sleep(0.1);
        printf("Result %lu\n", result);
    }

	pthread_mutex_destroy(&lock);

    return 0;
}

void* calculatorThreadFunc(){
    signal(SIGUSR1, oNewOperation);
}

void oNewOperation(int signum){
    pthread_mutex_lock(&lock);
    result = n1 / n2;
}

void onErrorWhileExecutingOperation(int signum){
    puts("Rebooting...");

    restart();
}

void restart(){
    exit(0);

    n1 = 1;
    n2 = 1;
    pthread_cancel(calculatorThread);
    pthread_create(&calculatorThread, NULL, &calculatorThreadFunc, NULL);
}