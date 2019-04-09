package net.thumbtack.school.backend.threads.exercise_16;

import javafx.concurrent.Task;

public class MyTask extends Task implements Executable {

    private Integer number;

    MyTask(){

    }

    MyTask(Integer number){
        this.number = number;
    }

    @Override
    protected Object call() throws Exception {
        execute();
        return null;
    }

    @Override
    public void execute() {
        System.out.println("Task started");
        System.out.println("Task in process");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task finished");

    }
}
