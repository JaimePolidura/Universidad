#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>

int main(void){
	pid_t lee;
	pid_t escribe;
	int *numero;
	int *acabar;

	numero = mmap(NULL, sizeof *numero, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
	acabar = mmap(NULL, sizeof *acabar, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
	
	*acabar = 0;

	lee = fork();

	if(lee == 0){ //Hijo lee
		printf("Introduce un numero\n");
                scanf("%d", *&numero);
		
		while(*acabar == 0);

		exit(0);

	}else{
		escribe = fork();

		if(escribe == 0){ //HIjo escribe
			while(*numero == 0);

			printf("El numero es %d\n", *numero);
			
			char xd;
			puts("Pulsa cualquier tecla para acabar");
			scanf("%c", &xd);	
		
			*acabar = 1;

			exit(0);
		}else{ //Padre
			while(*acabar == 0);
			exit(0);	
		}

	}
	

	return 0;
}

