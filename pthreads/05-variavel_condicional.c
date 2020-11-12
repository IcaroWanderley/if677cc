#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

/* Assim como mutex, variáveis condicionais podem ser declaradas estaticamente
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
    pthread_mutex_lock(&mutex);
    contador++;
    printf("[Threads] contador = %d. Sinalizando...\n", contador);
    /* Sinalizamos para a thread que esta aguardando a condição o status atual
     * da nossa variável condicional */
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

    /* Devemos garantir que apenas a thread main obtenha os recursos */
    pthread_mutex_lock(&mutex);

    /* Quando a thread main atingir a condição PTHREAD_COND_WAIT, ela ficará em espera
     * enquanto as threads da função não sinalizarem através do PTHREAD_COND_SIGNAL */
    while (contador < NUMTHREADS)
    {
        pthread_cond_wait(&cond, &mutex);
        printf("[Thread main] acordada. Condição sinalizada.\n");
    }

    printf("[Thread main] encerrando...\n");

    pthread_mutex_unlock(&mutex);

    return 0;
}
