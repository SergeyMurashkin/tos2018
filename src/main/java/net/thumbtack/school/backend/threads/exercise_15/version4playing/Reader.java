package net.thumbtack.school.backend.threads.exercise_15.version4playing;

import java.util.Queue;

public class Reader extends Thread {

    private Queue<Data> dataQueue;

    public Reader(Queue<Data> dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        System.out.println("Reader Started");
        while (!Exercise_15.isEnd) {
            Data data = dataQueue.poll();
            if (data == null) {
                System.out.println("Reader : empty queue");
            } else {
                if (data.equals(new Data())) {
                    Exercise_15.isEnd = true;
                    System.out.println("Reader find sign " + data);
                    break;
                }else {
                    System.out.println("Reader retrieved: " + data);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
