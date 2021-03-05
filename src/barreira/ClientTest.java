/* RETIRADO DE https://github.com/kishanjavatrainer/CyclicBarrierExample */

package barreira;

import java.util.concurrent.CyclicBarrier;

public class ClientTest {
    public static void main(String args[]) {
        CyclicBarrier barrier = new CyclicBarrier(4);

        PassengerThread p1 = new PassengerThread(1000, barrier, "John");
        PassengerThread p2 = new PassengerThread(2000, barrier, "Martin");
        PassengerThread p3 = new PassengerThread(3000, barrier, "Adam");
        PassengerThread p4 = new PassengerThread(4000, barrier, "Sam");

        PassengerThread p5 = new PassengerThread(1000, barrier, "Steve");
        PassengerThread p6 = new PassengerThread(2000, barrier, "Dave");
        PassengerThread p7 = new PassengerThread(3000, barrier, "James");
        PassengerThread p8 = new PassengerThread(4000, barrier, "Brad");

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        p5.start();
        p6.start();
        p7.start();
        p8.start();
    }
}