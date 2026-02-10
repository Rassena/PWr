#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main() {
    int pipe_result[2]; // pipe_result[0] - read, pipe_result[1] - write
    pid_t pid;
    //char message[] = "test test test test test ala ma kota";
    char message[] ="";
    char buffer[4096];
    char str[25];

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
        printf("podaj dowolne slowo: ");
        scanf("%[^\n]s", message);
        write(pipe_result[1], message, strlen(message) + 1);
        close(pipe_result[1]);
	wait(NULL);
	printf("parent exit 0\n");
	exit(0);

    } else { // Child Proces
        close(pipe_result[1]);
	while (read(pipe_result[0], buffer, sizeof(buffer)) > 0) {
            printf("Dziecko odebrało: %s \n", buffer);
        }
        close(pipe_result[0]);
	printf("child exit 0\n");
	_exit(0);
    }

    printf("exit return 0\n");
    return 0;
}
