#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <signal.h>

void handler(int signum){
    puts("Inside the handler");
}

int main(void){
    signal(SIGUSR1, handler);
    printf("Dentro del main\n");
    raise(SIGUSR1);
    printf("Fuera del main\n");

	return 0;
}
