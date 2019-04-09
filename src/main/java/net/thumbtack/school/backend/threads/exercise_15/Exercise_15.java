package net.thumbtack.school.backend.threads.exercise_15;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;

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

        Runnable readerTask = () -> new Reader(dataQueue).start();
        Runnable writerTask = () -> new Writer(dataQueue).start();

        Executor executor = (runnable) -> {
            new Thread(runnable).start();
        };
        executor.execute(writerTask);
        executor.execute(readerTask);
        executor.execute(readerTask);
        executor.execute(readerTask);
        executor.execute(writerTask);
    }

}
