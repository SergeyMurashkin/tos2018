package net.thumbtack.school.backend.threads.exercise_17;

import javafx.concurrent.Task;
import net.thumbtack.school.backend.threads.exercise_16.Executable;

public class MyMultiTask extends Task implements Executable {

    private Integer number;
    private int stage;
    private int stageCount;

    MyMultiTask(){
    }

    MyMultiTask(Integer number, int stage, int stageCount){
        this.number = number;
        this.stage = stage;
        this.stageCount = stageCount;
    }

    @Override
    protected Object call() throws Exception {
        execute();
        return null;
    }

    @Override
    public void execute() {

        System.out.println("Task started. Number: " + number + " Stage: " + stage + "/" + stageCount );
        System.out.println("Task in process. Number: " + number + " Stage: " + stage + "/" + stageCount);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task finished. Number: " + number + " Stage: " + stage + "/" + stageCount );
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStageCount() {
        return stageCount;
    }

    public void setStageCount(int stageCount) {
        this.stageCount = stageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyMultiTask myTask = (MyMultiTask) o;

        return number != null ? number.equals(myTask.number) : myTask.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
