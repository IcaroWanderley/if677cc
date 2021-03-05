package helloworld;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("INÍCIO DO PROGRAMA");

        int numThreads = 15;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++)
            threads[i] = new Mensagem(i+1);

        for (Thread t : threads)
            t.start();

        System.out.println("FIM DO PROGRAMA");
    }
}
