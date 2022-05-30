#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <signal.h>

void mathErrorHandler(int signum){
    puts("ERROR While executing your operation");

    exit(-1);
}

int main(void){
    signal(SIGFPE, mathErrorHandler);
    int a, b;
    scanf("%d", &a);
    printf(" / ");
    scanf("%d", &b);
    printf(" = ");

    int result = a / b;

    printf(" = %d\n", result);

	return 0;
}
