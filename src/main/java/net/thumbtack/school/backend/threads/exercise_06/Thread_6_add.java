package net.thumbtack.school.backend.threads.exercise_06;

import java.util.List;

public class Thread_6_add extends Thread {


    private List<Integer> list;

    Thread_6_add(List<Integer> list) {
        this.list = list;
    }

    public void run() {
        int i = 0;
        while (i < 1000) {
            list.add((int) (Math.random() * 1000));
            System.out.println(list.size());
            i++;
        }
    }

}
