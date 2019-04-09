package net.thumbtack.school.backend.threads.exercise_11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise_11 {

    // 11) “Ping Pong” на базе ReentrantLock и Conditional переменной.

    public static volatile boolean isTimeToPing = false;

    public static void main(String[] args) {

        final Lock lock = new ReentrantLock();
        final Condition ping = lock.newCondition();
        final Condition pong = lock.newCondition();
        Ping threadPing = new Ping(lock, ping, pong);
        Pong threadPong = new Pong(lock, ping, pong);
        threadPing.start();
        threadPong.start();
        try {
            threadPing.join();
            threadPong.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main stop");
    }


}
