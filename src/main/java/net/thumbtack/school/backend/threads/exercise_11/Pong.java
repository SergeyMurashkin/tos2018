package net.thumbtack.school.backend.threads.exercise_11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static net.thumbtack.school.backend.threads.exercise_11.Exercise_11.isTimeToPing;

public class Pong extends Thread {

    private final Lock lock;
    private final Condition ping;
    private final Condition pong;
    private String tune = "pong";

    Pong(Lock lock, Condition ping, Condition pong) {
        this.lock = lock;
        this.ping = ping;
        this.pong = pong;
    }

    public void pong() {
        System.out.println(tune);
    }

    public void run() {
        while(true) {
            lock.lock();
            try {
                while (isTimeToPing) {
                    pong.await();
                }
                pong();
                isTimeToPing = true;
                ping.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

}
