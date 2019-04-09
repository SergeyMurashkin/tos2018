package net.thumbtack.school.backend.threads.exercise_16;

import javafx.concurrent.Task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Exercise_16 {

    // 16) Реализовать очередь задач. Задача - экземпляр класса  Task или его наследника,
    // имплементирующего Executable - свой интерфейс с единственным методом void execute().
    // Потоки - разработчики ставят в очередь экземпляры Task , потоки - исполнители берут их из очереди и исполняют.
    // Количество тех и других потоков может быть любым и задается через командную строку main.

    public static void main(String[] args) {

        Queue<Task> taskQueue = new ConcurrentLinkedDeque<>();



    }


}
