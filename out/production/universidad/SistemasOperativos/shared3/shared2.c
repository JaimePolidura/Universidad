#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <semaphore.h>
#include <fcntl.h>

int main(void){
	pid_t lee, escribe;
	sem_t *snum, *saca;
	int *numero;
	int *acabar;
	
	sem_unlink("/snum");
	sem_unlink("/saca");

	numero = mmap(NULL, sizeof *numero, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
	acabar = mmap(NULL, sizeof *acabar, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
	snum = sem_open("/snum", O_CREAT, S_IRUSR | S_IWUSR, 1);
	saca = sem_open("/saca", O_CREAT, S_IRUSR | S_IWUSR, 1);

	*acabar = 0;
	
	sem_wait(saca);

	lee = fork();

	if(lee == 0){ //Hijo lee
		sem_wait(snum);
		printf("Introduce un numero\n");
                scanf("%d", *&numero);
		sem_post(snum);

		exit(0);

	}else{
		escribe = fork();

		if(escribe == 0){ //HIjo escribe
			sem_wait(snum);
			
			printf("El numero es %d\n", *numero);
			
			char xd;
			puts("Pulsa cualquier tecla para acabar");
			scanf("%c", &xd);	
		
			*acabar = 1;
			
			sem_post(snum);
			sem_post(saca);

			exit(0);
		}else{ //Padre
			sem_wait(saca);
			
			sem_close(snum);
                        sem_unlink("/snum");
			sem_close(saca);
                        sem_unlink("/saca");


			exit(0);	
		}

	}
	

	return 0;
}

