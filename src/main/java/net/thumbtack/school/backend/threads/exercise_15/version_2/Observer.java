package net.thumbtack.school.backend.threads.exercise_15.version_2;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Observer extends Thread {

    private static volatile Boolean isEnd = false;

    private Queue<Data> dataQueue;
    private ExecutorService readerPool;

    Observer(Queue<Data> dataQueue, int readerQuantity){
        this.dataQueue = dataQueue;
        readerPool = Executors.newFixedThreadPool(readerQuantity);
        System.out.println("Observer created");
    }

    public void run(){
        System.out.println("Observer started");

        while(!isEnd){
            System.out.println(isEnd);
            readerPool.execute(new Reader(dataQueue));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readerPool.shutdown();
        while (!readerPool.isTerminated()) {
        }
        System.out.println("Observer finished");
    }

    public static void setEnd(){
        Observer.isEnd = true;
    }


}
