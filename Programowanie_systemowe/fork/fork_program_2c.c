#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>   


int main(int argc, char *argv[]) {
    if (argc < 3) {
        fprintf(stderr, "Użycie: %s <program1> -x -y , <program2> -z , <program3> \n", argv[0]);
        exit(1);
    }

    int forks = 1;

//TODO policzyc separatory v programy

    for (int i = 0; i < argc; i++) {
       //printf("argv[%d] = %s\n", i, argv[i]);
	 //if(argv[i] == ",") {
	if(!strcmp(argv[i],",")){ 
	//printf("fork %s added \n", argv[i-1]);
	 forks++;
	}
    }

   printf("forks %d \n",forks);

  int pipe_result[forks][2]; // pipe_result[i][0] - read, pipe_result[i][1] - write  <----- zamienic na wiele czytan i zapisow?

//


//TODO utworzyc forks-1 pipe 

     for (int i=0; i<forks-1;i++) {
            if (pipe(pipe_result[i]) == -1) {
        	perror("Pipe failed");
        	return 1;
	    }
     }


//TODO tworzyc w petli forki  

    int cmd_start = 1;   // początek aktualnego polecenia
    int cmd_idx = 0; // indeks aktualnego polecenia

     for (int i = 1; i <= argc; i++) {
        if (i == argc || strcmp(argv[i], ".") == 0) {
            argv[i] = NULL; // konczenie komendy execlp na NULL

            pid_t pid = fork();
            if (pid == 0) {
 		// nie pierwsza
                if (cmd_idx > 0){
		    close(pipe_result[cmd_idx - 1][1]);
                    dup2(pipe_result[cmd_idx - 1][0], STDIN_FILENO);
		   close(pipe_result[cmd_idx - 1][0]);
		}               
 // nie ostatnia
                if (cmd_idx < forks - 1) {
		    close(pipe_result[cmd_idx][0]);
                    dup2(pipe_result[cmd_idx][1], STDOUT_FILENO);
		    close(pipe_result[cmd_idx - 1][1]);
 		}	
                // zamknac wszystkie pipe
                // for (int j = 0; j < forks - 1; j++) {
                //    close(pipe_result[j][0]);
                //    close(pipe_result[j][1]);
               // }

                execvp(argv[cmd_start], &argv[cmd_start]);
                exit(1);
            }
	
            cmd_start = i + 1; // nowy segment
            cmd_idx++;
	}    
}

//TODO writer?	
//    if ((fork()) == 0) {
//        //printf("pid1 %s \n",argv[1]);
//        close(pipe_result[0]); 
//        dup2(pipe_result[1], STDOUT_FILENO);
//        close(pipe_result[1]);
//        execlp(argv[1], argv[1], NULL);
//        perror("Exec failed");
//        exit(1);
//    }

//TODO reader?
//    if ((fork()) == 0) {
        //printf("pid2 %s \n",argv[2]);
//        close(pipe_result[1]); 
//        dup2(pipe_result[0], STDIN_FILENO);
//        close(pipe_result[0]);
//        execlp(argv[2], argv[2], NULL);
//        perror("Exec failed");
//        exit(1);
//    }
//

//TODO zamknac wszystkie pipe n-1

     for (int i=0; i<forks-1;i++) {
    	close(pipe_result[i][0]);
    	close(pipe_result[i][1]);
        wait(NULL);
    }
    printf("\n");
    return 0;
}

