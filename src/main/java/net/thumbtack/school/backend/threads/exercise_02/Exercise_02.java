package net.thumbtack.school.backend.threads.exercise_02;

public class Exercise_02 {

    // 2) Создать новый поток и дождаться его окончания в первичном потоке.
    public static void main(String[] args) {
        System.out.println("main start");
        Thread_2 thread_2 = new Thread_2();
        thread_2.start();
        try {
            thread_2.join();
        } catch (InterruptedException e) {
            System.out.println("thread_2 interrupted");
        }
        System.out.println("main stop");
    }
}
