package net.thumbtack.school.backend.threads.exercise_17;

import net.thumbtack.school.backend.threads.exercise_16.Exercise_16;
import net.thumbtack.school.backend.threads.exercise_16.MyTask;

import java.util.Queue;

public class TaskDeveloper extends Thread{

    private Queue<MyMultiTask> taskQueue;
    private final Object lock;

    public TaskDeveloper(Queue<MyMultiTask> taskQueue, Object lock){
        this.taskQueue = taskQueue;
        this.lock = lock;
    }

    public void run(){
        System.out.println("Developer started");
        while( Exercise_17.countDevTask < Exercise_17.LIMIT ){
            int taskNumber;
            synchronized (lock) {
                taskNumber = ++Exercise_17.countDevTask;
            }

            if (taskNumber<=Exercise_17.LIMIT) {
                taskQueue.add(new MyMultiTask(taskNumber,1, (int)Math.round(2*Math.random() + 1)));
                System.out.println("Developer added: Task " + (taskNumber));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if(Exercise_17.countDevTask == Exercise_17.LIMIT + 100){
            taskQueue.add(new MyMultiTask());
            System.out.println("Developer added: END Task");
        }


        System.out.println("Developer finished");
    }

}
