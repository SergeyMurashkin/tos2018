package net.thumbtack.school.backend.threads.exercise_15.version4playing;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriterCommunity extends Thread {

    private Queue<Data> dataQueue;
    private final int writerCount;
    private final int dataCount;

    public WriterCommunity(Queue<Data> dataQueue, int writerCount, int dataCount){
        this.dataQueue = dataQueue;
        this.writerCount = writerCount;
        this.dataCount = dataCount;
    }


    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(writerCount);
        for (int i=0; i<=dataCount; i++){
            Runnable writer = new Writer(dataQueue,i,dataCount);
            executor.execute(writer);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        ExecutorService endExecutor = Executors.newSingleThreadExecutor();
        Runnable endWriter = new Writer(dataQueue,dataCount,dataCount);
        endExecutor.execute(endWriter);
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }



}
