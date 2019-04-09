package net.thumbtack.school.backend.threads.exercise_03;

public class Thread_3_1 extends Thread {

    public Thread_3_1(){
        System.out.println("Thread_3_1 created");
    }

    public void run(){
        System.out.println("Thread_3_1 start");
        try {
            sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Thread_3_1 interrupted");
        }
        finally {
            System.out.println("Thread_3_1 stop");
        }


    }

}
