package net.thumbtack.school.backend.threads.exercise_15;

import java.util.Queue;

public class Reader extends Thread {

    private Queue<Data> dataQueue;

    public Reader(Queue<Data> dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        System.out.println("Consumer Started");
        while (!Exercise_15.isEnd) {
            Data data = dataQueue.poll();
            if (data == null) {
                System.out.println("Consumer : empty queue");
            } else {
                if (data.equals(new Data())) {
                    Exercise_15.isEnd = true;
                    System.out.println("Consumer find sign " + data);
                    break;
                }else {
                    System.out.println("Consumer retrieved: " + data);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
