/* RETIRADO DE https://github.com/kishanjavatrainer/JavaConcurrencyConditionExample */

package lockcondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientTest {

    public static void main(String[] args) {
        ExecutorService executorService;

        try {
            executorService = Executors.newFixedThreadPool(2);
            final ProducerConsumer producerConsumer = new ProducerConsumer();

            Runnable producerTask = () -> {
                while (true) {
                    try {
                        producerConsumer.pushToStack();
                    } catch (InterruptedException e) {
                        System.out.println("Erro no producerTask()");
                    }
                }
            };

            Runnable consumerTask = () -> {
                while (true) {
                    try {
                        producerConsumer.popFromStack();
                    } catch (InterruptedException e) {
                        System.out.println("Erro no consumerTask()");
                    }
                }
            };

            executorService.submit(producerTask);
            executorService.submit(consumerTask);

        } catch (Exception ignored) { }
    }
}
