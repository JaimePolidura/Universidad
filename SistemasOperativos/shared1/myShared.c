#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#define LIMITE 10000000

int main(void){
	pid_t proceso1, proceso2;
	int *numero;

	numero = mmap(NULL, sizeof *numero, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);

	proceso1 = fork();

	if(proceso1 == 0){ //Hijo suma 1
		for(int i = 0; i < LIMITE; i++)
			*numero = *numero + 1;

		exit(0);
	}else{
		proceso2 = fork();

		if(proceso2 == 0){ //Hijo resta 1
                	for(int i = 0; i < LIMITE; i++)
                        	*numero = *numero - 1;

			exit(0);
		}else{ //Padre
			while(wait(NULL) > 0);

                	printf("El numero es %d\n", *numero);	
		}

	}
	

	return 0;
}

