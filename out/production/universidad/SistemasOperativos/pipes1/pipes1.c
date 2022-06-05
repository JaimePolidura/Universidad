#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>

int main(void){
	int tuberia[2];
	pid_t hijo;
		
	if(pipe(tuberia) < 0 ){
		perror("Error al crear la tuberia");
		
		return -1;
	}	
	if((hijo = fork ()) < 0){
		perror("Error al crear el hijo");
		
		return -1;
	}	
	if(hijo > 0){ //Padre
		close(tuberia[0]);
		char mensaje[] = "hola hijo, mecago en tus muertos";
		write(tuberia[1], mensaje, sizeof(mensaje));
		wait(NULL);
			
	}else{ //Hijo
		close(tuberia[1]);
		char buffer[100];
		read(tuberia[0], buffer, sizeof(buffer));
		printf("Mensaje del padre: %s\n", buffer);
		exit(0);
	}
}
