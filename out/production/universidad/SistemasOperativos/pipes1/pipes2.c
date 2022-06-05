#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>

int main(void){
	int tuberia[2];
	pid_t hijo;
	
	pipe(tuberia);
	hijo = fork();
    
	if(hijo > 0){ //Padre
        for(;;){
            char input [256];
            puts("Introduce el mensaje");
            scanf("%s", input);
        
            close(tuberia[0]);
            write(tuberia[1], input, sizeof(input));
        }

	}else{ //Hijo
        for(;;){

            close(tuberia[1]);
            char buffer[256];
            read(tuberia[0], buffer, sizeof(buffer));

            printf("Mensaje recibido: %s", buffer);
        }
	}
}
