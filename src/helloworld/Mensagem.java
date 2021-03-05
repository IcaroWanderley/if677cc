package helloworld;

public class Mensagem extends Thread {
    private final int identificador;

    public Mensagem(int id) {
        this.identificador = id;
    }

    @Override
    public void run() {
        System.out.printf("Hello world! (Thread %d)\n", this.identificador);
    }
}
