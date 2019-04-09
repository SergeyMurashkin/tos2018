package net.thumbtack.school.backend.threads.exercise_10;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class Thread_10_remove extends Thread {


    private final Lock lock;
    private ArrayList<Integer> list;

    Thread_10_remove(ArrayList<Integer> list,
                     Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    public void run() {
        int i = 0;
        while (i < 1000) {
           lock.lock();
            try {
                if (list.size() == 0) {
                    continue;
                } else {
                    int random = (int) (list.size() * Math.random());
                    list.remove(random);
                    System.out.println(list.size());
                    i++;
                }
            } finally {
                lock.unlock();
            }

        }
    }
}
