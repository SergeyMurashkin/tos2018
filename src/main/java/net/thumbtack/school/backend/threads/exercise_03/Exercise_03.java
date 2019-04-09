package net.thumbtack.school.backend.threads.exercise_03;

public class Exercise_03 {

    // 3) Создать 3 новых потока и дождаться окончания их всех в первичном потоке.
    // Для каждого потока создать свой собственный класс.

    public static void main(String[] args) {
        System.out.println("main start");
        Thread_3_1 thread_3_1 = new Thread_3_1();
        Thread_3_2 thread_3_2 = new Thread_3_2();
        Thread_3_3 thread_3_3 = new Thread_3_3();
        thread_3_1.start();
        thread_3_2.start();
        thread_3_3.start();
        try {
            thread_3_1.join();
            thread_3_2.join();
            thread_3_3.join();
        } catch (InterruptedException e) {
            System.out.println("thread interrupted");
        }
        System.out.println("main stop");
    }
}
