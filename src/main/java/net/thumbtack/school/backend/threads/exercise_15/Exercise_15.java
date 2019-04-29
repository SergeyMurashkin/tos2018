package net.thumbtack.school.backend.threads.exercise_15;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Exercise_15 {

    // 15) Реализовать очередь данных. Данные - экземпляр класса  Data с единственным методом int[] get().
    // Потоки-писатели ставят в очередь экземпляры Data, потоки - читатели берут их из очереди и распечатывают.
    // Количество тех и других потоков может быть любым и задается через командную строку main.
    // Рекомендуется для парсинга командной строки поискать подходящий пакет в Интернете и подключить его через pom.xml.

    public static final int LIMIT = 20;
    public static volatile int count = 0;
    public static volatile Boolean isEnd = false;

    public static void main(String[] args) {

        Queue<Data> dataQueue = new ConcurrentLinkedDeque<>();
        final Object lock = new Object();

        Reader reader1 = new Reader(dataQueue);
        Reader reader2 = new Reader(dataQueue);
        Reader reader3 = new Reader(dataQueue);
        Reader reader4 = new Reader(dataQueue);
        Writer writer1 = new Writer(dataQueue, lock);
        Writer writer2 = new Writer(dataQueue, lock);
        Writer writer3 = new Writer(dataQueue, lock);
        Writer writer4 = new Writer(dataQueue, lock);

        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();
        writer1.start();
        writer2.start();
        writer3.start();

        try {
            writer1.join();
            writer2.join();
            writer3.join();
            writer4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        count=LIMIT+100;

        writer4.start();

        try {
            writer4.join();
            reader1.join();
            reader2.join();
            reader3.join();
            reader4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All finished");

    }

}
