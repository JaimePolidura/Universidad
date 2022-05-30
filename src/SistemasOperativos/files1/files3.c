#include <unistd.h>
#include <sys/types.h>
#include <wait.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <stdio.h>
#include <pthread.h>
#include <string.h>
#include <dirent.h>

#define SIZE 256

int main(){
    char myDir[SIZE];
    struct dirent *direntp;
    DIR *dirp;
    getcwd(myDir, SIZE);
    
    dirp = opendir(myDir);
    while((direntp=readdir(dirp)) != NULL){
        printf("%s %d \n", direntp->d_name, direntp->d_reclen);
    }
    closedir(dirp);

    return 0;
}