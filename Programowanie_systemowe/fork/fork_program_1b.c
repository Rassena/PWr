#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main() {
    int pipe_result[2]; // pipe_result[0] - read, pipe_result[1] - write
    pid_t pid;
    // char buffer[sizeof(message)];
    char buffer[100];

    if (pipe(pipe_result) == -1) {
        perror("Pipe failed");
        return 1;
    }

    pid = fork();

    if (pid < 0) {
        perror("Fork failed");
        return 1;
    } else if (pid > 0) { // Parent Process
        char message[] = "test test test test test ala ma kota";
        close(pipe_result[0]);
        for(int i = 0; i< 1000000;i++){
        printf("%d Rodzic wysyła wiadomość: %s \n",i, message);
        write(pipe_result[1], message, strlen(message) + 1);
        }
        close(pipe_result[1]);
	wait(NULL);
	printf("parent exit 0\n");
    } else { // Child Proces 
        close(pipe_result[1]);
        sleep(5);
           while (read(pipe_result[0], buffer, sizeof(buffer)) > 0) {
            printf("Dziecko odebrało dane: %s \n", buffer);
        }
        close(pipe_result[0]);
	printf("Child exit 0\n");
	_exit(0);
    }
    printf("return 0");
    return 0;
}
