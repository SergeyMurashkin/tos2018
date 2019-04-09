package net.thumbtack.school.backend.threads.exercise_07;

public class Ping extends Thread {

    private final Object lock;
    private String ping = "ping";

    Ping(Object lock) {
        this.lock = lock;
    }

    public void ping() {
        System.out.println(ping);
    }

    public void run() {
        while(true) {
            synchronized (lock) {

                lock.notify();
                ping();

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
