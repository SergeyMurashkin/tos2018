package net.thumbtack.school.backend.threads.exercise_15.version4playing;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Exercise_15 {

    // 15) Реализовать очередь данных. Данные - экземпляр класса  Data с единственным методом int[] get().
    // Потоки-писатели ставят в очередь экземпляры Data, потоки - читатели берут их из очереди и распечатывают.
    // Количество тех и других потоков может быть любым и задается через командную строку main.
    // Рекомендуется для парсинга командной строки поискать подходящий пакет в Интернете и подключить его через pom.xml.

    public static final int DATA_COUNT = 30;
    public static final int WRITER_COUNT = 5;
    public static final int READER_COUNT = 5;
    public static volatile Boolean isEnd = false;

    public static void main(String[] args) {

        Queue<Data> dataQueue = new ConcurrentLinkedDeque<>();

        WriterCommunity writerCommunity = new WriterCommunity(dataQueue,WRITER_COUNT, DATA_COUNT);
        ReaderCommunity readerCommunity = new ReaderCommunity(dataQueue,READER_COUNT);

        writerCommunity.start();
        readerCommunity.start();

        try {
            writerCommunity.join();
            readerCommunity.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
