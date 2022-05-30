#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <string.h>

#define SIZE 256

int main(){
    char myDir[SIZE];

    if(getcwd(myDir, SIZE) == NULL){
        return -1;
    }

    printf("EL directorio actual es %s\n", myDir);
    return 0;
}