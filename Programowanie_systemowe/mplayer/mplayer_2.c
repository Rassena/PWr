/* mplayer_control.c - Fixed: Non-blocking input & robust pipe handling */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <termios.h>
#include <signal.h>

#define FIFO_PATH "/tmp/mplayer_fifo"
#define BUFFER_SIZE 256

struct termios orig_termios;

/* Restore terminal settings on exit */
void disable_raw_mode() {
    tcsetattr(STDIN_FILENO, TCSAFLUSH, &orig_termios);
    printf("\nTerminal settings restored.\n");
}

/* Enable non-canonical mode (no enter needed) and disable echo */
void enable_raw_mode() {
    tcgetattr(STDIN_FILENO, &orig_termios);
    atexit(disable_raw_mode); // Register cleanup

    struct termios raw = orig_termios;
    raw.c_lflag &= ~(ECHO | ICANON); // Disable echo and canonical mode
    tcsetattr(STDIN_FILENO, TCSAFLUSH, &raw);
}

void cleanup_fifo() {
    unlink(FIFO_PATH);
}

/* Handle Ctrl+C to ensure atexit functions run */
void handle_signal(int sig) {
    exit(0);
}

int main() {
    /* Setup cleanup and signals */
    signal(SIGINT, handle_signal);
    signal(SIGPIPE, SIG_IGN); // Ignore broken pipe signal to handle error manually
    atexit(cleanup_fifo);
    
    printf("Creating FIFO: %s\n", FIFO_PATH);
    if (mkfifo(FIFO_PATH, 0666) == -1 && errno != EEXIST) {
        perror("mkfifo");
        return 1;
    }
    
    printf("Step 1: Run mplayer in a separate terminal:\n");
    printf("  mplayer -input file=%s song.mp3\n\n", FIFO_PATH);
    
    printf("Step 2: Waiting for mplayer to connect... ");
    fflush(stdout);

    /* Blocks here until mplayer opens the pipe */
    int fd = open(FIFO_PATH, O_WRONLY);
    if (fd == -1) {
        perror("open");
        return 1;
    }
    printf("Connected!\n");

    enable_raw_mode(); // Turn on instant key detection
    
    printf("\nControls:\n");
    printf(" [p] Pause/Resume\n");
    printf(" [+] Forward 10s\n");
    printf(" [-] Rewind 10s\n");
    printf(" [SPACE] Next file\n");
    printf(" [q] Quit\n");
    
    int running = 1;
    char cmd;
    
    while (running && read(STDIN_FILENO, &cmd, 1) == 1) {
        char buffer[BUFFER_SIZE] = {0};
        
        switch (cmd) {
            case 'p':
                snprintf(buffer, BUFFER_SIZE, "pause\n");
                printf("\rCommand: Pause   ");
                break;
            case '+':
            case '=': // Support unshifted + key often found as =
                snprintf(buffer, BUFFER_SIZE, "seek 10\n");
                printf("\rCommand: +10s    ");
                break;
            case '-':
                snprintf(buffer, BUFFER_SIZE, "seek -10\n");
                printf("\rCommand: -10s    ");
                break;
            case ' ':
                snprintf(buffer, BUFFER_SIZE, "pt_step 1\n");
                printf("\rCommand: Next    ");
                break;
            case 'q':
                printf("\nExiting.\n");
                running = 0;
                snprintf(buffer, BUFFER_SIZE, "quit\n"); // Optional: tell mplayer to quit too
                break;
            default:
                continue;
        }
        
        /* Write to pipe and check if mplayer is still alive */
        if (strlen(buffer) > 0) {
            ssize_t bytes = write(fd, buffer, strlen(buffer));
            if (bytes == -1) {
                if (errno == EPIPE) {
                    printf("\nMPlayer closed the connection.\n");
                } else {
                    perror("write");
                }
                running = 0;
            }
        }
        fflush(stdout); // Ensure text updates immediately
    }
    
    close(fd);
    return 0;
}
