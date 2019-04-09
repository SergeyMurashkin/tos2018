package net.thumbtack.school.backend.threads.exercise_06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise_06 {

    // 6) То же самое. использовать метод Collections.synchronizedList.

    public static void main(String[] args) {
        System.out.println("main start");
        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());

        Thread_6 thread_6_1 = new Thread_6(integers, true);
        Thread_6 thread_6_2 = new Thread_6(integers, false);
        thread_6_1.start();
        thread_6_2.start();
        try {
            thread_6_1.join();
            thread_6_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main stop");

        /*
        Thread_6_add thread_6_add = new Thread_6_add(integers);
        Thread_6_remove thread_6_remove = new Thread_6_remove(integers);
        thread_6_add.start();
        thread_6_remove.start();
        try {
            thread_6_add.join();
            thread_6_remove.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

}
