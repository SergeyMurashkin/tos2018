package net.thumbtack.school.backend.threads.exercise_16;

import javafx.concurrent.Task;

import java.util.Queue;

public class TaskDeveloper extends Thread{

    private Queue<Task> taskQueue;

    TaskDeveloper(Queue<Task> taskQueue){
        this.taskQueue = taskQueue;
    }


    public void run(){
        for (int i=0; i<10; i++){
            MyTask myTask = new MyTask(i);
            taskQueue.add(myTask);
        }
        taskQueue.add(new MyTask());
    }

}
