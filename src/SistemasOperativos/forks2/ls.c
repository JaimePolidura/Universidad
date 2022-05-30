#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>

int main(){
	pid_t hijo = fork();

	if(hijo > 0){
		wait(NULL);
		puts("El hijo ha acabado ls");
	}else if(hijo == 0){
		execlp("/bin/ls", "ls", NULL);
	}
}
