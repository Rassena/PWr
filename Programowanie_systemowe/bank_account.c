#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define NUM_DEPOSIT_THREADS 5
#define NUM_WITHDRAW_THREADS 5
#define DEPOSIT_AMOUNT 100
#define WITHDRAW_AMOUNT 50
#define NUM_DEPOSITS 10
#define NUM_WITHDRAWS 20
#define MIN_SLEEP 100
#define MAX_SLEEP 1000

int account_balance = 0;
pthread_mutex_t account_mutex = PTHREAD_MUTEX_INITIALIZER;


void *deposit(void *arg) {
    int thread_id = *(int *)arg;
    for (int i = 0; i < NUM_DEPOSITS; i++) {
        usleep(rand() % MAX_SLEEP + MIN_SLEEP);

        printf("Thread %d (Deposit): Próba uzyskania mutexa...\n", thread_id);
        pthread_mutex_lock(&account_mutex);
        printf("Thread %d (Deposit): Uzyskano dostęp do mutexa.\n", thread_id);

        int local_balance = account_balance;
        printf("Thread %d (Deposit): Odczytano stan konta: %d\n", thread_id, local_balance);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
        local_balance += DEPOSIT_AMOUNT;
        account_balance = local_balance;

        printf("Thread %d (Deposit): Wpłata %d. Nowy stan konta: %d\n", thread_id, DEPOSIT_AMOUNT, account_balance);
        pthread_mutex_unlock(&account_mutex);
        printf("Thread %d (Deposit): Zwolniono mutex.\n", thread_id);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
    }
    return NULL;
}

void *withdraw(void *arg) {
    int thread_id = *(int *)arg;
    for (int i = 0; i < NUM_WITHDRAWS; i++) {
        usleep(rand() % MAX_SLEEP + MIN_SLEEP);

        printf("Thread %d (Withdraw): Próba uzyskania mutexa...\n", thread_id);
        pthread_mutex_lock(&account_mutex);
        printf("Thread %d (Withdraw): Uzyskano dostęp do mutexa.\n", thread_id);

        int local_balance = account_balance;
        printf("Thread %d (Withdraw): Odczytano stan konta: %d\n", thread_id, local_balance);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
        local_balance -= WITHDRAW_AMOUNT;
        account_balance = local_balance;

        printf("Thread %d (Withdraw): Wypłata %d. Nowy stan konta: %d\n", thread_id, WITHDRAW_AMOUNT, account_balance);
        pthread_mutex_unlock(&account_mutex);
        printf("Thread %d (Withdraw): Zwolniono mutex.\n", thread_id);

        usleep(rand() % MAX_SLEEP + MIN_SLEEP);
    }
    return NULL;
}

int main() {
    pthread_t deposit_threads[NUM_DEPOSIT_THREADS];
    pthread_t withdraw_threads[NUM_WITHDRAW_THREADS];
    int thread_ids[NUM_DEPOSIT_THREADS + NUM_WITHDRAW_THREADS];

    srand(time(NULL));

    printf("Main: Startowy stan konta: %d\n", account_balance);
    
    printf("Main: Tworzenie Thread Depozyt: %d\n", account_balance);
    for (int i = 0; i < NUM_DEPOSIT_THREADS; i++) {
        thread_ids[i] = i + 1;
        pthread_create(&deposit_threads[i], NULL, deposit, &thread_ids[i]);
    }
    
    printf("Main: Tworzenie Thread Withdraw: %d\n", account_balance);
    for (int i = 0; i < NUM_WITHDRAW_THREADS; i++) {
        thread_ids[NUM_DEPOSIT_THREADS + i] = i + 1;
        pthread_create(&withdraw_threads[i], NULL, withdraw, &thread_ids[NUM_DEPOSIT_THREADS + i]);
    }

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

    printf("Main: Usuwanie Mutex\n");
    pthread_mutex_destroy(&account_mutex);

    printf("Main: Koncowy stan konta: %d\n", account_balance);
    
    return 0;
}
