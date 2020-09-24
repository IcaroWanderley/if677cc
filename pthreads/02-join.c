#include <stdio.h>
#include <pthread.h>

int count = 0;

void *hello(void *arg)
{
    count = count + 1;
    printf("Hello world! (Thread %d)\n", count);
    pthread_exit(NULL);
}

int main()
{
    int size = 10;
    pthread_t threads[size];

    for (int i = 0; i < size; i++)
    {
        pthread_create(&threads[i], NULL, &hello, NULL);
    }

    for (int i = 0; i < size; i++)
    {
        pthread_join(threads[i], NULL);
    }

    printf("Isso será executado após o loop do pthread_join().\n");

    pthread_exit(NULL);
}
