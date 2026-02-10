#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<unistd.h>

#define KEY ((key_t) 12345L)
#define PERM 0600
#define SIZE 4096


int main(int argc, char *argv[]) {
    if (argc < 3) {
        fprintf(stderr, "Użycie: %s <powtorzenia> <wartosc>\n", argv[0]);
        exit(1);
    }

    int shmid;
    
    if ((shmid=shmget(KEY,SIZE,PERM | IPC_CREAT)) <0) {
        printf("Nie mozna utworzyc bloku pamieci wspolnej\n");
        exit(1);
    }else{
        printf("Data space %d\n",shmid);	
    }

    sleep(1);
    int *data = (int*) shmat(shmid, (void*)0, 0);
    printf("started with %d\n",*data);
    shmdt(data);
    sleep(1);

    for (int i=0; i<atoi(argv[1]);i++) {

        data = (int*) shmat(shmid, (void*)0, 0);

        printf("Change shared memory: %d\n", atoi(argv[2]));
        int tmp = *data;
        // *data+=atoi(argv[2]);
        tmp+=atoi(argv[2]);
        usleep(100);
        *data=tmp;
        printf("Current state: %d\n", *data);

        shmdt(data);
        printf("released data\n");
        usleep(100);
}
    sleep(1);
    data = (int*) shmat(shmid, (void*)0, 0);
    printf("finished with %d\n",*data);
    shmdt(data);
    return 0;
}
