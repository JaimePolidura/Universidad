#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>

int main(void){
	pid_t hijo;
	int *variable;

	variable = mmap(NULL, sizeof *variable, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
	
	hijo = fork();

	if(hijo == 0){
		printf("Soy el hijo, la variable: %d\n", *variable);
		scanf("%d", *&variable);
	}else{
		printf("Soy el padre la variable es %d\n", *variable);
		wait(NULL);
		printf("Valor variable, %d", *variable);
	}
	munmap(variable, sizeof(variable));

	return 0;
}
