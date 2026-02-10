#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>   


int main(int argc, char *argv[]) {
    if (argc < 3) {
        fprintf(stderr, "Użycie: %s <program1> <program2>\n", argv[0]);
        exit(1);
    }

    int pipe_result[2]; // pipe_result[0] - read, pipe_result[1] - write
    pid_t pid1, pid2;

    for (int i = 0; i < argc; i++) {
        printf("argv[%d] = %s\n", i, argv[i]);
    }
    printf("\n");
	
    if (pipe(pipe_result) == -1) {
        perror("Pipe failed");
        return 1;
    }

    if ((pid1 = fork()) == 0) {
        printf("pid1 %s \n",argv[1]);
        close(pipe_result[0]); 
        dup2(pipe_result[1], STDOUT_FILENO);
        close(pipe_result[1]);
        execlp(argv[1], argv[1], NULL);
        perror("Exec failed");
        exit(1);
    }

    if ((pid2 = fork()) == 0) {
        printf("pid2 %s \n",argv[2]);
        close(pipe_result[1]); 
        dup2(pipe_result[0], STDIN_FILENO);
        close(pipe_result[0]);
        execlp(argv[2], argv[2], NULL);
       	perror("Exec failed");
        exit(1);
    }

    close(pipe_result[0]);
    close(pipe_result[1]);

    wait(NULL);
    wait(NULL);

    return 0;
}
