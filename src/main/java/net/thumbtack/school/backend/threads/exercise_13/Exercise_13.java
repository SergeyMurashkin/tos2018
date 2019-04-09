package net.thumbtack.school.backend.threads.exercise_13;

import java.text.SimpleDateFormat;

public class Exercise_13 {

    // 13) Написать класс Formatter, с методом format(Date date), форматирующим дату-время.
    // Использовать для форматирования класс SimpleDateFormat.
    // В основном потоке создается единственный экземпляр класса Formatter.
    // Создать 5 потоков, каждому потоку передается при инициализации этот экземпляр.
    // Потоки должны корректно форматировать дату-время, синхронизация не разрешается.

    public static void main(String[] args) {

        Formatter formatter = new Formatter(new SimpleDateFormat());

        Thread_13 thread_13_1 = new Thread_13(formatter);
        Thread_13 thread_13_2 = new Thread_13(formatter);
        Thread_13 thread_13_3 = new Thread_13(formatter);
        Thread_13 thread_13_4 = new Thread_13(formatter);
        Thread_13 thread_13_5 = new Thread_13(formatter);

        thread_13_1.start();
        thread_13_2.start();
        thread_13_3.start();
        thread_13_4.start();
        thread_13_5.start();

        try {
            thread_13_1.join();
            thread_13_2.join();
            thread_13_3.join();
            thread_13_4.join();
            thread_13_5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
