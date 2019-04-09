package net.thumbtack.school.backend.threads.exercise_15;

import java.util.Queue;

public class Writer extends Thread {

    private Queue<Data> dataQueue;

    public Writer(Queue<Data> dataQueue) {
        this.dataQueue = dataQueue;
    }

    public void run() {
        System.out.println("Producer Started");
        while( Exercise_15.count < Exercise_15.LIMIT ){
            // array creating
            int[] ints = new int[5];
            ints[0] = 5;
            ints[1] = 5;
            ints[2] = 5;
            ints[3] = 5;
            ints[4] = 5;
            //
            dataQueue.add(new Data(ints));
            System.out.println("Producer added: Data ");

            Exercise_15.count++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(Exercise_15.count >= Exercise_15.LIMIT){
            dataQueue.add(new Data());
            System.out.println("Producer added: END Data");
        }


        System.out.println("Producer finished");
    }

}
