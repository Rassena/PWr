#include <stdio.h>
#include <unistd.h>

int main(void){
	int hell=666;
	printf("Hello World! %d \n",hell);
	//fflush(stdout);
	while(1){
	printf("while  loop %d \n",hell);
	sleep(1);
	}
return 0;
}
