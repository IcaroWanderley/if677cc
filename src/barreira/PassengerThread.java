/* RETIRADO DE https://github.com/kishanjavatrainer/CyclicBarrierExample */

package barreira;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PassengerThread extends Thread {
    private int duration;
    private CyclicBarrier barrier;

    public PassengerThread(int duration, CyclicBarrier barrier, String pname) {
        super(pname);
        this.duration = duration;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            System.out.println(Thread.currentThread().getName() + " chegou.");

            int await = barrier.await();
            if (await == 0) {
                System.out.println("Quatro passageiros chegaram, o táxi irá partir...");
            }
        } catch (InterruptedException | BrokenBarrierException ignored) { }
    }
}
