#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>

int main (void) {
	pid_t hijo1;
	printf("Hoy soy el padre estoy solo y mi pid %d\n", getpid());
	hijo1=fork();

	if(hijo1 > 0){
		printf("Hola soy el padre, mi PID sigue siendo %d y el tonto de mi hijo tiene el PID %d\n", getpid(), hijo1);
		wait(NULL);
	}else if(hijo1 == 0){
		printf("Hola, soy el hijo mi PID es %d y el de mi padre es %d\n", getpid(), getppid());
	}

	printf("Soy el padre mi PID sigue siendo %d", getpid());

	return 0;
}
