package net.thumbtack.school.backend.threads.exercise_16;

import java.util.Queue;

public class TaskDeveloper extends Thread{

    private Queue<MyTask> taskQueue;
    private final Object lock;

    public TaskDeveloper(Queue<MyTask> taskQueue, Object lock){
        this.taskQueue = taskQueue;
        this.lock = lock;
    }

    public void run(){
        System.out.println("Developer started");
        while( Exercise_16.count < Exercise_16.LIMIT ){
            int i;
            synchronized (lock) {
               i = Exercise_16.count++;
            }

            if (i<Exercise_16.LIMIT) {
                taskQueue.add(new MyTask(i));
                System.out.println("Developer added: Task " + (i+1));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if(Exercise_16.count == Exercise_16.LIMIT + 100){
            taskQueue.add(new MyTask());
            System.out.println("Developer added: END Task");
        }


        System.out.println("Developer finished");
    }

}
