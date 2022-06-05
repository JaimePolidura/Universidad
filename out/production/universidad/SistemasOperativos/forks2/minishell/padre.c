#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <string.h>

int main(){
	char commandInput[10];
	printf("\n");

	for(;;) {
		scanf("%c\n", commandInput);

		pid_t hijo;
                hijo = fork();

		if(hijo == 0){
                	if(strcmp(commandInput, "exit")){
                        	exit(0);
                	}else if(strcmp(commandInput, "ls")){
                        	execlp("/bin/ls", "ls", NULL);
                	}else if(strcmp(commandInput, "who")){
                        	execlp("/bin/who", "who", NULL);
                	}else if(strcmp(commandInput, "pwd")){
                        	execlp("/bin/pwd", "pwd", NULL);
               		}else if(strcmp(commandInput ,"ps")){
                        	execlp("/bin/ps", "ps", NULL);
                	}
                }else if(hijo > 0){
                        wait(NULL);
                }
	}
}
