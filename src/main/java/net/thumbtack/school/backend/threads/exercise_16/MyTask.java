package net.thumbtack.school.backend.threads.exercise_16;

import javafx.concurrent.Task;

public class MyTask extends Task implements Executable {

    private Integer number;

    MyTask(){
    }

    MyTask(Integer number){
        this.number = number+1;
    }

    @Override
    protected Object call() throws Exception {
        execute();
        return null;
    }

    @Override
    public void execute() {
        System.out.println("Task started" + number);
        System.out.println("Task in process" + number);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task finished" + number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyTask myTask = (MyTask) o;

        return number != null ? number.equals(myTask.number) : myTask.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
