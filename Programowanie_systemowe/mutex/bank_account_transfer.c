#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>


#define NUM_TRANSFER_1_2_THREADS 7
#define NUM_TRANSFER_2_1_THREADS 5
#define NUM_DEPOSIT_THREADS 2
#define NUM_WITHDRAW_THREADS 2

#define TRANSFER_1_2 100
#define TRANSFER_2_1 50
#define NUM_TRANSFER_1_2 10
#define NUM_TRANSFER_2_1 20

#define NUM_DEPOSITS 10
#define DEPOSIT_AMOUNT 100

#define NUM_WITHDRAWS 5
#define WITHDRAW_AMOUNT 200


#define MIN_SLEEP 100
#define MAX_SLEEP 1000

int account_balance_1 = 0;
int account_balance_2 = 0;


typedef struct {
    int value;               
    pthread_mutex_t mutex;   
    pthread_cond_t cond;     
} MySemaphore;

MySemaphore sem;

void my_sem_init(MySemaphore *sem, int initial_value) {
    sem->value = initial_value;
    pthread_mutex_init(&sem->mutex, NULL);
    pthread_cond_init(&sem->cond, NULL);
}

void my_sem_wait(MySemaphore *sem) {
    pthread_mutex_lock(&sem->mutex);
    while (sem->value == 0) {
        pthread_cond_wait(&sem->cond, &sem->mutex);
    }
    sem->value--;
    pthread_mutex_unlock(&sem->mutex);
}

void my_sem_post(MySemaphore *sem) {
    pthread_mutex_lock(&sem->mutex);
    sem->value++;
    pthread_cond_signal(&sem->cond);
    pthread_mutex_unlock(&sem->mutex);
}

void my_sem_destroy(MySemaphore *sem) {
    pthread_mutex_destroy(&sem->mutex);
    pthread_cond_destroy(&sem->cond);
}


void *acc1_to_acc2(void *arg) {
    int thread_id = *(int *)arg;
    for (int i = 0; i < NUM_TRANSFER_1_2; i++) {
        usleep(rand() % MAX_SLEEP + MIN_SLEEP);

        printf("Thread %d (acc1_to_acc2): Próba uzyskania semafora\n", thread_id);
        my_sem_wait(&sem);
        printf("Thread %d (acc1_to_acc2): Uzyskano dostęp do semafora\n", thread_id);

        int local_balance_1 = account_balance_1;
        int local_balance_2 = account_balance_2;
        printf("Thread %d (acc1_to_acc2): Odczytano stan konta_1: %d, konta_2: %d\n", thread_id, local_balance_1,local_balance_2);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
        local_balance_1 -= TRANSFER_1_2;
        local_balance_2 += TRANSFER_1_2;

        account_balance_1 = local_balance_1;
        account_balance_2 = local_balance_2;

        printf("Thread %d (acc1_to_acc2): Transfer %d. Nowy stan konta_1: %d, konta_2: %d\n", thread_id, TRANSFER_1_2, account_balance_1,account_balance_2);
        my_sem_post(&sem);
        printf("Thread %d (acc1_to_acc2): Zwolniono semafor\n", thread_id);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
    }
    return NULL;
}

void *acc2_to_acc1(void *arg) {
    int thread_id = *(int *)arg;
    for (int i = 0; i < NUM_TRANSFER_2_1; i++) {
        usleep(rand() % MAX_SLEEP + MIN_SLEEP);

        printf("Thread %d (acc2_to_acc1): Próba uzyskania semafora\n", thread_id);
        my_sem_wait(&sem);
        printf("Thread %d (acc2_to_acc1): Uzyskano dostęp do semafora\n", thread_id);

        int local_balance_1 = account_balance_1;
        int local_balance_2 = account_balance_2;
        
        printf("Thread %d (acc2_to_acc1): Odczytano stan konta_1: %d, konta_2: %d\n", thread_id, local_balance_1,local_balance_2);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
        local_balance_2 -= TRANSFER_2_1;
        local_balance_1 += TRANSFER_2_1;

        account_balance_1 = local_balance_1;
        account_balance_2 = local_balance_2;

        printf("Thread %d (acc2_to_acc1): Transfer %d. Nowy stan konta_1: %d, konta_2: %d\n", thread_id, TRANSFER_2_1, account_balance_1,account_balance_2);
        my_sem_post(&sem);
        printf("Thread %d (acc2_to_acc1): Zwolniono semafor\n", thread_id);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
    }
    return NULL;
}

void *deposit_1(void *arg) {
    int thread_id = *(int *)arg;
    for (int i = 0; i < NUM_DEPOSITS; i++) {
        usleep(rand() % MAX_SLEEP + MIN_SLEEP);

        printf("Thread %d (Deposit): Próba uzyskania semafora\n", thread_id);
        my_sem_wait(&sem);
        printf("Thread %d (Deposit): Uzyskano dostęp do semafora\n", thread_id);

        int local_balance = account_balance_1;
        printf("Thread %d (Deposit): Odczytano stan konta_1: %d\n", thread_id, local_balance);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
        local_balance += DEPOSIT_AMOUNT;
        account_balance_1 = local_balance;

        printf("Thread %d (Deposit): Wpłata %d. Nowy stan konta_1: %d\n", thread_id, DEPOSIT_AMOUNT, account_balance_1);
        my_sem_post(&sem);
        printf("Thread %d (Deposit): Zwolniono semafor\n", thread_id);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
    }
    return NULL;
}

void *withdraw_2(void *arg) {
    int thread_id = *(int *)arg;
    for (int i = 0; i < NUM_WITHDRAWS; i++) {
        usleep(rand() % MAX_SLEEP + MIN_SLEEP);

        printf("Thread %d (Withdraw_2): Próba uzyskania semafora\n", thread_id);
        my_sem_wait(&sem);
        printf("Thread %d (Withdraw_2): Uzyskano dostęp do semafora\n", thread_id);

        int local_balance = account_balance_2;
        printf("Thread %d (Withdraw_2): Odczytano stan konta_2: %d\n", thread_id, local_balance);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
        local_balance -= WITHDRAW_AMOUNT;
        account_balance_2 = local_balance;

        printf("Thread %d (Withdraw_2): Wypłata %d. Nowy stan konta_2: %d\n", thread_id, WITHDRAW_AMOUNT, account_balance_2);
        my_sem_post(&sem);
        printf("Thread %d (Withdraw_2): Zwolniono semafor\n", thread_id);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
    }
    return NULL;
}


int main() {
    pthread_t acc1_to_acc2_threads[NUM_TRANSFER_1_2_THREADS];
    pthread_t acc2_to_acc1_threads[NUM_TRANSFER_2_1_THREADS];
    pthread_t deposit_threads[NUM_DEPOSIT_THREADS];
    pthread_t withdraw_threads[NUM_WITHDRAW_THREADS];

    int transfer_thread_ids[NUM_TRANSFER_1_2_THREADS + NUM_TRANSFER_2_1_THREADS];
    int deposit_thread_ids[NUM_DEPOSIT_THREADS];
    int withdraw_threads_ids[NUM_WITHDRAW_THREADS];
    int seed = time(NULL)+getpid();

    srand(seed);
    my_sem_init(&sem, 1);

    printf("Seed: %d\n",seed);
    printf("Main: Startowy stan konta_1: %d, konta_2: %d\n", account_balance_1,account_balance_2);
    

    printf("Main: Tworzenie Thread Depozyt\n");
    for (int i = 0; i < NUM_DEPOSIT_THREADS; i++) {
        deposit_thread_ids[i] = i + 1;
        pthread_create(&deposit_threads[i], NULL, deposit_1, &deposit_thread_ids[i]);
    }

    printf("Main: Tworzenie Thread Withdraw\n");
    for (int i = 0; i < NUM_WITHDRAW_THREADS; i++) {
        withdraw_threads_ids[i] = i + 1;
        pthread_create(&withdraw_threads[i], NULL, withdraw_2, &withdraw_threads_ids[i]);
    }

    printf("Main: Tworzenie Thread acc1_to_acc2\n");
    for (int i = 0; i < NUM_TRANSFER_1_2_THREADS; i++) {
        transfer_thread_ids[i] = i + 1;
        pthread_create(&acc1_to_acc2_threads[i], NULL, acc1_to_acc2, &transfer_thread_ids[i]);
    }
    
    printf("Main: Tworzenie Thread acc2_to_acc1\n");
    for (int i = 0; i < NUM_TRANSFER_2_1_THREADS; i++) {
        transfer_thread_ids[NUM_TRANSFER_1_2_THREADS + i] = i + 1;
        pthread_create(&acc2_to_acc1_threads[i], NULL, acc2_to_acc1, &transfer_thread_ids[NUM_TRANSFER_1_2_THREADS + i]);
    }

    printf("Main: Czekam na koniec acc1_to_acc2 \n");
    for (int i = 0; i < NUM_TRANSFER_1_2_THREADS; i++) {
        pthread_join(acc1_to_acc2_threads[i], NULL);
    }
    printf("\nMain: Koniec acc1_to_acc2 \n\n");

    printf("Main: Czekam na koniec acc2_to_acc1 \n");
    for (int i = 0; i < NUM_TRANSFER_2_1_THREADS; i++) {
        pthread_join(acc2_to_acc1_threads[i], NULL);
    }
    printf("\nMain: Koniec acc2_to_acc1 \n\n");

    printf("Main: Czekam na koniec Depozytów \n");
    for (int i = 0; i < NUM_DEPOSIT_THREADS; i++) {
        pthread_join(deposit_threads[i], NULL);
    }
    printf("\nMain: Koniec Depozytów \n\n");


    printf("Main: Czekam na koniec wypłat \n");
    for (int i = 0; i < NUM_WITHDRAW_THREADS; i++) {
        pthread_join(withdraw_threads[i], NULL);
    }
    printf("\nMain: Koniec wypłat \n\n");


    printf("Main: Usuwanie semafor\n");
    my_sem_destroy(&sem);

    printf("Main: Koncowy stan konta_1: %d, konta_2: %d\n", account_balance_1,account_balance_2);
    
    return 0;
}
