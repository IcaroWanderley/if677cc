#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

/* Assim como mutex, variaveis condicionais podem ser declaradas estaticamente
 * ou dinamicamente */
int contador = 0;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

void *loops(void *arg)
{
    /* Loop simulando um trabalho custoso ao processamento */
    int max = 5;
    for (int i = 0; i < max; i++)
    {
        printf("[Threads] trabalhando... (%d/%d)\n", i, max);
        sleep(1);
    }

    /* Devemos utilizar mutex para garantir que uma thread por vez adquira os recursos */
    while (pthread_mutex_trylock(&mutex));
    contador++;
    printf("[Threads] contador = %d. Sinalizando...\n", contador);

    /* Sinalizamos para a thread que esta aguardando a condicao o status atual
     * da nossa variavel condicional */
    pthread_cond_signal(&cond);
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int main()
{
    int NUMTHREADS = 10;
    printf("[Thread main] iniciada.\n");

    pthread_t threads[NUMTHREADS];

    for (int i = 0; i < NUMTHREADS; i++)
    {
        pthread_create(&threads[i], NULL, loops, NULL);
    }

    /* Quando a thread main atingir a condicao PTHREAD_COND_WAIT, ela ficara em espera
     * enquanto as threads da funcao nao sinalizarem atraves do PTHREAD_COND_SIGNAL */
    while (contador < NUMTHREADS)
    {
        pthread_cond_wait(&cond, &mutex);
        printf("[Thread main] acordada. Condicao sinalizada.\n");
    }

    printf("[Thread main] encerrando...\n");

    return 0;
}
