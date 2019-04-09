package net.thumbtack.school.backend.threads.exercise_04;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise_04 {

    // 4) В основном потоке создать ArrayList<Integer>. Запустить 2 потока на базе разных классов,
    // один поток 10000 раз добавляет в список случайное целое число,
    // другой 10000 раз удаляет элемент по случайному индексу. Использовать внешний synchronized блок.

    public static void main(String[] args) {
        System.out.println("main start");
        ArrayList<Integer> list = new ArrayList<>();
        final Lock lock = new ReentrantLock();
        Thread_4_add thread_4_add = new Thread_4_add(list, lock);
        Thread_4_remove thread_4_remove = new Thread_4_remove(list, lock);
        thread_4_add.start();
        thread_4_remove.start();
        try {
            thread_4_add.join();
            thread_4_remove.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main stop");
    }

}
