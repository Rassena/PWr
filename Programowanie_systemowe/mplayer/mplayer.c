#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <signal.h>

#define FIFO_PATH "/tmp/fifo" 
#define BUFFER_SIZE 256

void cleanup_fifo() {
    unlink(FIFO_PATH);
    printf("FIFO closed\n");
}

int main() {
    signal(SIGPIPE, SIG_IGN);
    atexit(cleanup_fifo);
    
    if (mkfifo(FIFO_PATH, 0666) == -1 && errno != EEXIST) {
        perror("error mkfifo");
        return 1;
    }
    
    printf("FIFO: %s\n", FIFO_PATH);
    printf("run mplayer:\n");
    printf("  mplayer -input file=%s plik\n", FIFO_PATH);
    fflush(stdout);

    int fd = open(FIFO_PATH, O_WRONLY);
    if (fd == -1) {
        perror("error open");
        return 1;
    }
    printf("Connected!\n");
    
    printf("znak + ENTER:\n");
    printf("[p] Pause \n");
    printf("[f] Forward 3 \n");
    printf("[b] Back 5 \n");
    printf("[q] Quit \n");
    
    char cmd;
    int running = 1;
    char buffer[BUFFER_SIZE];

    while (running) {
        cmd = getchar();

        if (cmd == '\n') continue;

        memset(buffer, 0, BUFFER_SIZE);
        
        switch (cmd) {
            case 'p':
                strcpy(buffer, "pause\n");
                break;
            case 'f':
                strcpy(buffer, "seek 3\n"); 
                break;
            case 'b':
                strcpy(buffer, "seek -5\n");
                break;
            case 'q':
                strcpy(buffer, "quit\n");
                running = 0;
                break;
            default:
                printf("unknow: %c\n", cmd);
                continue;
        }

        if (strlen(buffer) > 0) {
            if (write(fd, buffer, strlen(buffer)) == -1) {
                printf("Mplayer disconnected\n");
                break;
            }
        }
    }
    
    close(fd);
    return 0;
}
