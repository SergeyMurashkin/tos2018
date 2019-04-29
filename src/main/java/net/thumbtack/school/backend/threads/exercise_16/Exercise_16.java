package net.thumbtack.school.backend.threads.exercise_16;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Exercise_16 {

    // 16) Реализовать очередь задач. Задача - экземпляр класса  Task или его наследника,
    // имплементирующего Executable - свой интерфейс с единственным методом void execute().
    // Потоки - разработчики ставят в очередь экземпляры Task , потоки - исполнители берут их из очереди и исполняют.
    // Количество тех и других потоков может быть любым и задается через командную строку main.

    public static final int LIMIT = 30;
    public static volatile int count = 0;
    public static volatile Boolean isEnd = false;


    public static void main(String[] args) {

        Queue<MyTask> taskQueue = new ConcurrentLinkedDeque<>();
        Object lock = new Object();

        TaskDeveloper taskDeveloper1 = new TaskDeveloper(taskQueue, lock);
        TaskDeveloper taskDeveloper2 = new TaskDeveloper(taskQueue, lock);
        TaskDeveloper taskDeveloper3 = new TaskDeveloper(taskQueue, lock);
        TaskDeveloper taskDeveloper4 = new TaskDeveloper(taskQueue, lock);

        TaskPerformer taskPerformer1 = new TaskPerformer(taskQueue);
        TaskPerformer taskPerformer2 = new TaskPerformer(taskQueue);
        TaskPerformer taskPerformer3 = new TaskPerformer(taskQueue);
        TaskPerformer taskPerformer4 = new TaskPerformer(taskQueue);

        taskDeveloper1.start();
        taskDeveloper2.start();
        taskDeveloper3.start();

        taskPerformer1.start();
        taskPerformer2.start();
        taskPerformer3.start();
        taskPerformer4.start();

        try {
            taskDeveloper1.join();
            taskDeveloper2.join();
            taskDeveloper3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count = LIMIT+100;
        taskDeveloper4.start();

        try {
            taskDeveloper4.join();
            taskPerformer1.join();
            taskPerformer2.join();
            taskPerformer3.join();
            taskPerformer4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }


}
