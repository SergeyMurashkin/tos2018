package net.thumbtack.school.backend.threads.exercise_15.version_2;

import java.util.Queue;

public class Writer extends Thread {

    private Queue<Data> dataQueue;

    public Writer(Queue<Data> dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        dataQueue.add(createData());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Data createData(){
        int[] ints = new int[5];
        ints[0] = 5;
        ints[1] = 5;
        ints[2] = 5;
        ints[3] = 5;
        ints[4] = 5;
        return new Data((ints));
    }

}
