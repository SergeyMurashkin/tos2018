package net.thumbtack.school.backend.threads.exercise_17;

import net.thumbtack.school.backend.threads.exercise_16.Exercise_16;
import net.thumbtack.school.backend.threads.exercise_16.MyTask;

import java.util.Queue;

public class TaskPerformer extends Thread{

    private Queue<MyMultiTask> taskQueue;
    private final Object lock;

    public TaskPerformer(Queue<MyMultiTask> taskQueue, Object lock){
        this.taskQueue = taskQueue;
        this.lock = lock;
    }

    public void run() {
        System.out.println("Performer started");
        while (!Exercise_17.isEnd) {
            MyMultiTask myMultiTask = taskQueue.poll();
            if (myMultiTask == null) {
                System.out.println("Performer : empty queue");
            } else {
                if (myMultiTask.equals(new MyMultiTask())) {
                    Exercise_17.isEnd = true;
                    System.out.println("Performer find END sign." );
                    break;
                }else {
                    if(myMultiTask.getStage()==myMultiTask.getStageCount()) {
                        myMultiTask.execute();
                        synchronized (lock){
                            Exercise_17.countPerfTask++;
                        }
                    }else{
                        myMultiTask.execute();
                        myMultiTask.setStage(myMultiTask.getStage()+1);
                        taskQueue.add(myMultiTask);
                    }
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
