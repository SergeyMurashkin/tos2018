package net.thumbtack.school.backend.threads.exercise_04;

import java.util.ArrayList;

public class Thread_4_remove extends Thread  {

    private final Object lock;
    private ArrayList<Integer> list;

    Thread_4_remove(ArrayList<Integer> list,
                    Object lock) {
        this.list = list;
        this.lock = lock;
    }


    public void run() {
        int i = 0;
        while (i < 1000) {
            synchronized (lock) {
                if (list.size()==0){
                    continue;
                }else {
                    int random = (int) (list.size() * Math.random());
                    list.remove(random);
                    System.out.println(list.size());
                    i++;
                }
            }
        }
    }

}
