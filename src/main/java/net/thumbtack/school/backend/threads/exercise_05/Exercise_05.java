package net.thumbtack.school.backend.threads.exercise_05;

import java.util.ArrayList;

public class Exercise_05 {

    // 5) То же самое, но оба потока на базе одного и того же класса, использовать synchronized методы.

    public static void main(String[] args) {
        System.out.println("main start");
        ArrayList<Integer> list = new ArrayList<>();
        Thread_5 thread_5_1 = new Thread_5(list, true);
        Thread_5 thread_5_2 = new Thread_5(list, false);
        thread_5_1.start();
        thread_5_2.start();
        try {
            thread_5_1.join();
            thread_5_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main stop");
    }

}
