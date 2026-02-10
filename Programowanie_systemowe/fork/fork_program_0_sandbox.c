#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
int main()
{
    int num;
    pid_t pid;

    //printf("Enter a number:\n");
    //scanf("%d", &num);

        pid = fork();
        if (pid == 0)
        {
            printf("Child is doing something \n");
            sleep(3);
            printf("Child ends\n");
        }
        else
        {
            printf("Parent is waiting for child to complete \n");
            wait(NULL);
            printf("Parent ends\n");
        }
    return 0;
}
