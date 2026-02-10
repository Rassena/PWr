#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h> 

int main(int argc, char *argv[]) {
    int pipe_result[2]; // pipe_result[0] - read, pipe_result[1] - write
    pid_t pid;
    char message[4096];
    char buffer[4096];

    if (argc < 3) {
        fprintf(stderr, "Użycie: %s <program1> <program2>\n", argv[0]);
        exit(1);
    }

    if (pipe(pipe_result) == -1) {
        perror("Pipe failed");
        return 1;
    }

    pid = fork();

    if (pid < 0) {
        perror("Fork failed");
        return 1;
    } else if (pid > 0) { // Parent Process
        close(pipe_result[0]);
	execl(argv[1],NULL ,NULL);
        write(pipe_result[1], message, strlen(message) + 1);
        close(pipe_result[1]);
    } else { // Child Proces
        close(pipe_result[1]);
        read(pipe_result[0], buffer, sizeof(buffer));
        printf("Dziecko odebralo: %s\n", buffer);
        close(pipe_result[0]);
    }

    return 0;
}
