package net.thumbtack.school.backend.threads.exercise_15.version_2;

import java.util.Queue;

public class FakeWriter extends Thread {

    private Queue<Data> dataQueue;

    public FakeWriter(Queue<Data> dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        System.out.println("FakeWriter Started");

        dataQueue.add(new Data());

        System.out.println("FakeWriter added: empty Data");

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("FakeWriter finished");
    }


}
