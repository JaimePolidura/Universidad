#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>

int main (void){
	pid_t hijo = fork();
	int n = 5;

	

	if(hijo > 0){ //Proceso padre
		puts("Soy el proceso padre y quedo a la espera");
	}else if(hijo == 0){ //Proceso hijo
		n++;
		printf("Soy el hijo y n vale %d\n", n);
		puts("Soy el hijo y ahora me voy a tomar por culo");
		
		return 0;
	}else{
		perror("akjldfskldajfñ");
	}

	printf("Mi hijo ha muerto solo quedo yo el padre y la variable vale %d", n);

	
	return 0;
}
