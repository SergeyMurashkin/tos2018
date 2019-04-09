package net.thumbtack.school.backend.threads.exercise_10;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class Thread_10_add extends Thread {

    private final Lock lock;
    private ArrayList<Integer> list;

    Thread_10_add(ArrayList<Integer> list, Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    public void run() {
        int i = 0;
        while (i < 1000) {
            lock.lock();
            try {
                list.add((int) (Math.random() * 1000));
                System.out.println(list.size());
                i++;
            }finally {
                lock.unlock();
            }

        }
    }
}
