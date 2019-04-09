package net.thumbtack.school.backend.threads.exercise_10;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise_10 {

    // 10) Переписать упражнение 4, используя ReentrantLock.

    // 4) В основном потоке создать ArrayList<Integer>. Запустить 2 потока на базе разных классов,
    // один поток 10000 раз добавляет в список случайное целое число,
    // другой 10000 раз удаляет элемент по случайному индексу. Использовать внешний synchronized блок.

    public static void main(String[] args) {
        System.out.println("main start");
        ArrayList<Integer> list = new ArrayList<>();
        final ReentrantLock lock = new ReentrantLock();
        Thread_10_add thread_10_add = new Thread_10_add(list, lock);
        Thread_10_remove thread_10_remove = new Thread_10_remove(list, lock);
        thread_10_add.start();
        thread_10_remove.start();
        try {
            thread_10_add.join();
            thread_10_remove.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main stop");
    }

}
