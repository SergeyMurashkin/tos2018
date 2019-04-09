package net.thumbtack.school.backend.threads.exercise_03;

public class Thread_3_2 extends Thread{

        public Thread_3_2(){
            System.out.println("Thread_3_2 created");
        }

        public void run(){
            System.out.println("Thread_3_2 start");
            try {
                sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Thread_3_2 interrupted");
            }
            finally {
                System.out.println("Thread_3_2 stop");
            }


        }
}
