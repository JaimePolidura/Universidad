#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#define LIMITE 10000000

int main(void){
	asm("cli");
	sleep();		
	asm("cli");
	

	return 0;
}

