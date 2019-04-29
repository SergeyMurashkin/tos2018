package net.thumbtack.school.backend.threads.exercise_16;

import java.util.Queue;

public class TaskPerformer extends Thread{

    private Queue<MyTask> taskQueue;

    public TaskPerformer(Queue<MyTask> taskQueue){
        this.taskQueue = taskQueue;
    }

    public void run() {
        System.out.println("Performer started");
        while (!Exercise_16.isEnd) {
            MyTask myTask = taskQueue.poll();
            if (myTask == null) {
                System.out.println("Performer : empty queue");
            } else {
                if (myTask.equals(new MyTask())) {
                    Exercise_16.isEnd = true;
                    System.out.println("Performer find END sign." );
                    break;
                }else {
                    myTask.execute();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Performer finished");
    }

}
