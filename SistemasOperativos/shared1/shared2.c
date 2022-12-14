#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>

int calcularFactorial(int);

int main(void){
	pid_t hijo;
	int *numero;
	int *factorial;

	numero = mmap(NULL, sizeof *numero, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
	factorial = mmap(NULL, sizeof *factorial, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);

	hijo = fork();

	if(hijo == 0){ //Hijo
		while(*numero == 0);
		
		*factorial = calcularFactorial(*numero);
		printf("El facotiral es %d\n", *factorial);
		
		exit(0);

	}else{ //Padre
		printf("Introduce un numero\n");
		scanf("%d", *&numero);
	}
	munmap(numero, sizeof(numero));
	munmap(factorial, sizeof(factorial));

	return 0;
}

int calcularFactorial(int n){
        return n == 0 ? 1 : n * calcularFactorial(n - 1);
}

