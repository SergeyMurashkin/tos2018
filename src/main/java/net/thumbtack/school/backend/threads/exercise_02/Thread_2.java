package net.thumbtack.school.backend.threads.exercise_02;

public class Thread_2 extends Thread{

    public Thread_2(){
        System.out.println("Thread2 created");
    }

    public void run(){
        System.out.println("Thread_2 start");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Thread_2 interrupted");
        }
        finally {
            System.out.println("Thread_2 stop");
        }


    }

}
