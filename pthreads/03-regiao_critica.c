#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

double saldo = 600.00;

void *debitar(void *arg)
{
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

    pthread_exit(NULL);
}

/* Se executarmos varias vezes esse codigo, perceberemos que o resultado sera diferente */
int main()
{
    int size = 3;
    pthread_t threads[size];

    printf("Saldo atual: R$ %.2f\n", saldo);

    for (int i = 0; i < size; i++)
        pthread_create(&threads[i], NULL, debitar, NULL);

    for(int j = 0; j < size; j++)
        pthread_join(threads[j], NULL);

    sleep(1);
    printf("Saldo após os saques: R$ %.2f\n", saldo);

    pthread_exit(NULL);
}
