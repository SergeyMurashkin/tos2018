package net.thumbtack.school.backend.threads.exercise_03;


public class Thread_3_3 extends Thread {

    public Thread_3_3() {
        System.out.println("Thread_3_3 created");
    }

    public void run() {
        System.out.println("Thread_3_3 start");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Thread_3_3 interrupted");
        } finally {
            System.out.println("Thread_3_3 stop");
        }


    }
}
