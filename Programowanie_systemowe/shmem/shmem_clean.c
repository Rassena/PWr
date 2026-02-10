#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define KEY ((key_t) 12345L)
#define PERM 0600
#define SIZE 4096


int main() {

    int shmid;

    if ((shmid=shmget(KEY,SIZE,PERM | IPC_CREAT)) <0) {
        printf("Nie mozna utworzyc bloku pamieci wspolnej\n");
        exit(1);
    }
       shmctl(shmid, IPC_RMID, NULL);

}
