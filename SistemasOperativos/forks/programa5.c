#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>

int main (void) {
	pid_t hijo1;
	printf("Hoy soy el padre estoy solo y mi pid %d\n", getpid());
	hijo1 = fork();
	
	if(hijo1 == 0){
		printf("Hola soy el hijo mi PID %d\n", getpid());
		sleep(2);
		printf("SIGO VIOOOOOOOOOO el id de mi padre %d\n", getppid());
	}else{
		printf("Hola soy el padre %d\n", getpid());
		exit(0);
	}

	return 0;
}
