package net.thumbtack.school.backend.threads.exercise_15.version4playing;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReaderCommunity extends Thread {

    private Queue<Data> dataQueue;
    private final int readerCount;

    public ReaderCommunity(Queue<Data> dataQueue, int readerCount){
        this.dataQueue = dataQueue;
        this.readerCount = readerCount;
    }

    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(readerCount);
        while (!Exercise_15.isEnd) {
            Runnable reader = new Reader(dataQueue);
            executor.execute(reader);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }


    }


}
