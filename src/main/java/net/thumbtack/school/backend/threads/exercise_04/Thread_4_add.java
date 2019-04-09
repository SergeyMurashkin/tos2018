package net.thumbtack.school.backend.threads.exercise_04;

import java.util.ArrayList;

public class Thread_4_add extends Thread {

    private final Object lock;
    private ArrayList<Integer> list;

    Thread_4_add(ArrayList<Integer> list, Object lock) {
        this.list = list;
        this.lock = lock;
    }

    public void run() {
        int i = 0;
        while (i < 1000) {
            synchronized (lock) {
                list.add((int) (Math.random() * 1000));
                System.out.println(list.size());
                i++;
            }
        }
    }
}
