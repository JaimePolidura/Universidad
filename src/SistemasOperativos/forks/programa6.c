#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <ctype.h>

int main (void) {
	pid_t hijo1;
	pid_t hijo2;
	pid_t hijo3;

	char letra;
	scanf("%c", &letra);

	hijo1 = fork();

	if(hijo1 > 0){
		hijo2 = fork();

		if(hijo2 > 0)
			hijo3 = fork();
	}

	if(hijo1 == 0){
		printf("[Hijo 1]: Ascii of %c -> %d\n", letra, letra);
	}
	if(hijo2 == 0){
		printf("[Hijo 2]: Next ascci letter %c\n", letra + 1);
	}
	if(hijo3 == 0){
		if(letra >= 'a' || letra <= 'z')
			printf("[Hijo 3]: To upper case %c\n", toupper(letra));
		else if(letra >= 'A' || letra <= 'Z')
			printf("[Hijo 3]: To lower case %c\n", tolower(letra));
	}

	return 0;
}
