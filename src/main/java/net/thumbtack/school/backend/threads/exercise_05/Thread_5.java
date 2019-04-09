package net.thumbtack.school.backend.threads.exercise_05;

import java.util.ArrayList;

public class Thread_5 extends Thread {

    private ArrayList<Integer> list;
    private boolean isAdd;

    Thread_5(ArrayList<Integer> list, boolean isAdd) {
        this.list = list;
        this.isAdd = isAdd;
    }

    public void run() {
        if (isAdd) {
            add();
        } else {
            remove();
        }
    }

    public synchronized void add() {
        int i = 0;
        while (i < 1000) {
            list.add((int) (Math.random() * 1000));
            System.out.println(list.size());
            i++;
        }
    }

    public synchronized void remove() {
        int i = 0;
        while (i < 1000) {
            if (list.size() != 0) {
                int random = (int) (list.size() * Math.random());
                list.remove(random);
                System.out.println(list.size());
                i++;
            }
        }
    }

}
