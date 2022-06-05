#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>

int main (void){
	pid_t hijo1 = fork();
	pid_t hijo2;
	
	if(hijo1 > 0){
		hijo2 = fork();
	}
	
	if(hijo1 == 0){
		puts("proceso 1");
		return 0;

	}else if(hijo2 == 0){
		puts("proceso 2");
		return 0;
	}

	while(wait(NULL) > 0);

	puts("bye");
	
	return 0;
}
