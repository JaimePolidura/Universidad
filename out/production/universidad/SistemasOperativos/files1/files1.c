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
    char buffer[SIZE];
    printf("EScribe tu numbre y a que te dedicas");
     
    fgets(buffer, SIZE, stdin);
    if(strlen(buffer) > 0 && buffer[strlen(buffer) - 1] == '\n'){
        buffer[strlen(buffer) - 1] = '\0';
    }

    printf("Has escrito %s\n", buffer);

    return 0;
}