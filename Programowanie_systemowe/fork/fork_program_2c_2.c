#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Użycie: %s prog1 arg , prog2 arg ...\n", argv[0]);
        exit(1);
    }

    int programs_count = 1;
    for (int i = 1; i < argc; i++) {
        if (strcmp(argv[i], ",") == 0) {
            programs_count++;
            argv[i] = NULL;
        }
    }

    int num_pipes = programs_count - 1;
    int pipe_fds[num_pipes][2];

    for (int i = 0; i < num_pipes; i++) {
        if (pipe(pipe_fds[i]) == -1) {
            perror("Pipe failed");
            exit(1);
        }
    }

    int cmd_start_idx = 1;

    for (int i = 0; i < programs_count; i++) {
        pid_t pid = fork();

        if (pid == 0) {
            // jeśli nie pierwszy
            if (i > 0) {
                dup2(pipe_fds[i-1][0], STDIN_FILENO);
            }

            // jeśli nie ostatni
            if (i < num_pipes) {
                dup2(pipe_fds[i][1], STDOUT_FILENO);
            }

            // zamknięcie pipe
            for (int j = 0; j < num_pipes; j++) {
                close(pipe_fds[j][0]);
                close(pipe_fds[j][1]);
            }

	    // wykonaj zadanie
            execvp(argv[cmd_start_idx], &argv[cmd_start_idx]);
            perror("Execvp failed");
            exit(1);
        } else if (pid < 0) {
            perror("Fork failed");
            exit(1);
        }

        // NULL zamiast przecinka
        while (cmd_start_idx < argc && argv[cmd_start_idx] != NULL) {
            cmd_start_idx++;
        }
        cmd_start_idx++;
    }
    // Rodzic zamyka, dzieci dostaja EOF
    for (int i = 0; i < num_pipes; i++) {
        close(pipe_fds[i][0]);
        close(pipe_fds[i][1]);
    }

    // czekaj na dzieci
    for (int i = 0; i < programs_count; i++) {
        wait(NULL);
    }

    return 0;
}
