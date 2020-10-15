#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

double saldo = 600.00;

/* Inicializacao estatica de um mutex */
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void *debitar(void *arg)
{
    /* Usando o LOCK, a thread em execucao acessara a regiao critica
     * e excluira outras de acessarem ate que ela seja finalizada. Ao final, sempre devemos
     * liberar a regiao com o UNLOCK */
    pthread_mutex_lock(&mutex);

    sleep(1);
    if ((saldo - 400.00) > 0.00)
    {
        printf("Saque solicitado: R$ 400,00...\n");
        saldo = saldo - 400.00;
    }
    else
    {
        printf("Saldo insuficiente!\n");
    }

    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int main()
{
    int size = 3;
    pthread_t threads[size];

    printf("Saldo atual: R$ %.2f\n", saldo);

    for (int i = 0; i < size; i++)
        pthread_create(&threads[i], NULL, debitar, NULL);

    for (int j = 0; j < size; j++)
        pthread_join(threads[j], NULL);

    sleep(1);
    printf("Saldo após os saques: R$ %.2f\n", saldo);

    return 0;
}
