#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <semaphore.h>
#include <fcntl.h>

#define LIMITE 1000

int main(void){
	pid_t proceso1, proceso2;
	int *numero;
	sem_t *semaforo;	

	numero = mmap(NULL, sizeof *numero, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
	semaforo = sem_open("/semaforo", O_CREAT, S_IRUSR | S_IWUSR, 1);

	proceso1 = fork();

	if(proceso1 == 0){ //Hijo suma 1
		for(int i = 0; i < LIMITE; i++){
			sem_wait(semaforo);
			*numero = *numero +1;
			sem_post(semaforo);
		}

		exit(0);
	}else{
		proceso2 = fork();

		if(proceso2 == 0){ //Hijo resta 1
                	for(int i = 0; i < LIMITE; i++){
				sem_wait(semaforo);
				*numero = *numero - 1;
				sem_post(semaforo);
			}

			exit(0);
		}else{ //Padre
			while(wait(NULL) > 0);
			sem_close(semaforo);
		        sem_unlink("/semaforo");

                	printf("El numero es %d\n", *numero);	
		}

	}

	return 0;
}

