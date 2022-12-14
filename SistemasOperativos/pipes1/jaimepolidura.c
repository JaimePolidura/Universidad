#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>

int main(void){
	int tuberiaPadreHaciaHijo[2];
	int tuberiaHijoHaciaPadre[2];
	pid_t hijo;

	pipe(tuberiaPadreHaciaHijo);
	pipe(tuberiaHijoHaciaPadre);

	hijo = fork();

	if(hijo > 0){ //Padre
		int numero1;
		int numero2;
		puts("Intruduce 1º numero: ");
		scanf("%d", &numero1);
		puts("Introduce 2º numero: ");
		scanf("%d", &numero2);

		close(tuberiaPadreHaciaHijo[0]);
		int mensaje[2] = {numero1, numero2};
		write(tuberiaPadreHaciaHijo[1], mensaje, sizeof(mensaje));
		
		wait(NULL);

		close(tuberiaHijoHaciaPadre[1]);
		int suma[1];
		read(tuberiaHijoHaciaPadre[0], suma, sizeof(suma));	
		
		printf("\nLa suma es %d\n", suma[0]);

	}else{ //Hijo
		close(tuberiaPadreHaciaHijo[1]);
		int buffer[2];
		read(tuberiaPadreHaciaHijo[0], buffer, sizeof(buffer));
		
		int suma[1] = { buffer[0] + buffer[1] };
		close(tuberiaHijoHaciaPadre[0]);
		write(tuberiaHijoHaciaPadre[1], suma, sizeof(suma));

		exit(0);
	}
}
