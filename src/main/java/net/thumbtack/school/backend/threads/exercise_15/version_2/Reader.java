package net.thumbtack.school.backend.threads.exercise_15.version_2;

import java.util.Queue;

public class Reader extends Thread {

    private Queue<Data> dataQueue;

    public Reader(Queue<Data> dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        Data data = dataQueue.poll();
        if (data == null) {
            System.out.println("Reader : empty queue");
        } else {
            if (data.equals(new Data())) {
                Observer.setEnd();
                System.out.println("Reader find sign " + data);
            } else {
                System.out.println("Reader retrieved: " + data);
            }
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
