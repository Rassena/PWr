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
        fprintf(stderr, "Użycie: %s <dzialanie> <text>\n", argv[0]);
        exit(1);
    }

    printf("%s\n",argv[1]);

    int shmid;

    if ((shmid=shmget(KEY,SIZE,PERM | IPC_CREAT)) <0) {
        printf("Nie mozna utworzyc bloku pamieci wspolnej\n");
        exit(1);
    }

    printf("%d\n",shmid);

    char *data = (char*) shmat(shmid, (void*)0, 0);
    printf("reserved data %s\n",argv[1]);

    if (strcmp(argv[1], "write") == 0) {
        // Check the current length of data in shared memory and append new data
        size_t current_length = strlen(data);
        if (current_length + strlen(argv[2]) >= SIZE) {
            fprintf(stderr, "Brak miejsca w pamieci wspolnej, aby dodac nowe dane\n");
        }

        // Append new data to the end of existing data
        strcat(data, argv[2]);
        printf("Data appended to shared memory: %s\n", data);
    }
    
    if (strcmp(argv[1],"read")==0) {
        printf("Data read from shared memory: %s\n", data);
    }

    sleep(5);
    printf("release data\n");
    shmdt(data);

    return 0;
}
