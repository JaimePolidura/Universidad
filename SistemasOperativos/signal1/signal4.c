#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <signal.h>

void sleepHandler(int signum){
    puts("Sleeping");
}

void wakeUpHandler(int signnum){
    puts("Awake");
}

int main(void){
    pid_t hijo = fork();

    if(hijo == 0){ //HIJO
        signal(SIGUSR1, sleepHandler);
        signal(SIGUSR2, wakeUpHandler);
    }else{ //Padre
        sleep(5);
        signal(SIGN, handler);
    }

	return 0;
}
