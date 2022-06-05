#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <fcntl.h>

#define SIZE 256

char buffer[SIZE];

pthread_t lee, escribe, escribefichero;

void* leeFn();
void* escribeFn();
void* escribeficheroFn();
void onBufferModified();
void onFileWrite();

int main(){
    pthread_create(&lee, NULL, &leeFn, NULL);
    pthread_create(&escribe, NULL, &escribeFn, NULL);
    pthread_create(&escribefichero, NULL, &escribeficheroFn, NULL);
    
    pthread_join(lee, NULL);

    return 0;
}

void* leeFn(){
    printf("Escribe algo: ");
    
    fgets(buffer, SIZE, stdin);
    if(strlen(buffer) > 0 && buffer[strlen(buffer) - 1] == '\n') 
        buffer[strlen(buffer) - 1] = '\0';

    raise(SIGUSR1);
}

void* escribeFn(){
    signal(SIGUSR1, onBufferModified);
}

void onBufferModified(){
    char nombre[] = "prueba.txt";
    int fd, nleido;

    fd = open(nombre, O_WRONLY);
    write(fd, buffer, strlen(buffer));
    close(fd);

    raise(SIGUSR2);
}

void* escribeficheroFn(){
    signal(SIGUSR2, onFileWrite);
}

void onFileWrite(){
    char nombre[] = "prueba.txt";
    char readBuffer[SIZE];
    int fd, nleido;

    fd = open(nombre, O_RDONLY);
    read(fd, readBuffer, SIZE);
    
    printf("Has escrito %s\n", readBuffer);

    close(fd);
}