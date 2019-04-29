package net.thumbtack.school.backend.threads.exercise_15;

import java.util.Queue;

public class Writer extends Thread {

    private Queue<Data> dataQueue;
    private final Object lock;

    public Writer(Queue<Data> dataQueue, Object lock) {
        this.dataQueue = dataQueue;
        this.lock = lock;
    }

    public void run() {
        System.out.println("Writer started.");
        while( Exercise_15.count < Exercise_15.LIMIT ){
            int i;
            synchronized (lock) {
                i = Exercise_15.count++;
            }

            if(i<Exercise_15.LIMIT) {
                int ints[] = {(int)(100*Math.random()),
                        (int)(100*Math.random()),
                        (int)(100*Math.random()),
                        (int)(100*Math.random()),
                        (int)(100*Math.random())};
                dataQueue.add(new Data(ints));
                System.out.println("Writer added: Data. " + (i+1));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if(Exercise_15.count == Exercise_15.LIMIT+100){
            dataQueue.add(new Data());
            System.out.println("Writer added: END Data");
        }


        System.out.println("Writer finished.");
    }

}
