package net.thumbtack.school.backend.threads.exercise_01;

public class Exercise_01 {

    // 1) Напечатать все свойства текущего потока.
    public static void main(String[] args) {
        System.out.println("main start");
        Thread thread1 = Thread.currentThread();
        System.out.println(thread1);
        System.out.println("Id = " + thread1.getId());
        System.out.println("Name = " + thread1.getName());
        System.out.println("State = " + thread1.getState());
        System.out.println("Is alive = " + thread1.isAlive());
        System.out.println("Is daemon = " + thread1.isDaemon());
        System.out.println("Is interrupted = " + thread1.isInterrupted());
        System.out.println("main stop");
    }

}
