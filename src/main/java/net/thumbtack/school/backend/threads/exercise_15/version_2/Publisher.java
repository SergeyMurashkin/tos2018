package net.thumbtack.school.backend.threads.exercise_15.version_2;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Publisher extends Thread{

    private Queue<Data> dataQueue;
    private ExecutorService writerPool;
    private int bookQuantity;

    Publisher(Queue<Data> dataQueue, int bookQuantity, int writerQuantity){
        this.dataQueue = dataQueue;
        this.bookQuantity = bookQuantity;
        writerPool = Executors.newFixedThreadPool(writerQuantity);
        System.out.println("Publisher created");
    }

    public void run(){
        System.out.println("Publisher started");
        for (int i=0; i<bookQuantity; i++) {
            writerPool.execute(new Writer(dataQueue));
        }

        writerPool.execute(new FakeWriter(dataQueue));
        writerPool.shutdown();
        while (!writerPool.isTerminated()) {
        }
        System.out.println("Publisher finished");

    }



}
