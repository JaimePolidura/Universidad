#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <signal.h>

void handler(int signum){
    puts("holi puticurnios");
}

int main(void){

    //signal(SIGINT, handler);
    while(1){
        puts("Aqui estoy haciendo nada");
        sleep(1);
    }

	return 0;
}