package net.thumbtack.school.backend.threads.exercise_06;

import java.util.List;

public class Thread_6_remove extends Thread {

    private List<Integer> list;

    Thread_6_remove(List<Integer> list) {
        this.list = list;
    }

    public void run() {
        int i = 0;
        while (i < 1000) {
            if (list.size() == 0) {
                continue;
            } else {
                int random = (int) (list.size() * Math.random());
                list.remove(random);
                System.out.println(list.size());
                i++;
            }
        }
    }

}
