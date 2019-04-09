package net.thumbtack.school.backend.threads.exercise_15.version_2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Exercise_15_2 {


    // 15) Реализовать очередь данных. Данные - экземпляр класса  Data с единственным методом int[] get().
    // Потоки-писатели ставят в очередь экземпляры Data, потоки - читатели берут их из очереди и распечатывают.
    // Количество тех и других потоков может быть любым и задается через командную строку main.
    // Рекомендуется для парсинга командной строки поискать подходящий пакет в Интернете и подключить его через pom.xml.

    public static void main(String[] args) {

        Queue<Data> dataQueue = new ConcurrentLinkedDeque<>();

        Publisher publisher = new Publisher(dataQueue, 10, 2);
        Observer observer = new Observer(dataQueue,5);

        publisher.start();
        observer.start();

        try {
            publisher.join();
            observer.join();
        } catch (InterruptedException e) {
            System.out.println("thread interrupted");
        }
        System.out.println("main stop");

    }

}
