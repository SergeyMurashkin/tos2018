package net.thumbtack.school.backend.threads.exercise_06;

import java.util.List;

public class Thread_6 extends Thread {

    private List<Integer> list;
    private boolean isAdd;

    Thread_6(List<Integer> list, boolean isAdd) {
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

    public void add() {
        int i = 0;
        while (i < 1000) {
            list.add((int) (Math.random() * 1000));
            System.out.println(list.size());
            i++;
        }
    }

    public void remove() {
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
