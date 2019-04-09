package net.thumbtack.school.backend.threads.exercise_07;

public class Pong extends Thread {

    private final Object lock;
    private String pong = "pong";

    Pong(Object lock) {
        this.lock = lock;
    }

    public void pong() {
        System.out.println(pong);
    }

    public void run() {
        while(true) {
            synchronized (lock) {
                lock.notify();
                pong();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
