package net.thumbtack.school.backend.threads.exercise_15.version4playing;

import java.util.Queue;

public class Writer extends Thread {


    private Queue<Data> dataQueue;
    private final int dataNumber;
    private final int dataCount;

    public Writer(Queue<Data> dataQueue, int dataNumber, int dataCount) {
        this.dataQueue = dataQueue;
        this.dataNumber = dataNumber;
        this.dataCount = dataCount;
    }

    public void run() {
        System.out.println("Writer started.");

        if (dataNumber < dataCount) {
            int ints[] = {(int) (100 * Math.random()),
                    (int) (100 * Math.random()),
                    (int) (100 * Math.random()),
                    (int) (100 * Math.random()),
                    (int) (100 * Math.random())};
            dataQueue.add(new Data(ints));
            System.out.println("Writer added: Data " + (dataNumber + 1));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (dataNumber == dataCount) {
            dataQueue.add(new Data());
            System.out.println("Writer added: Data END.");
        }

        System.out.println("Writer finished.");
    }

}
