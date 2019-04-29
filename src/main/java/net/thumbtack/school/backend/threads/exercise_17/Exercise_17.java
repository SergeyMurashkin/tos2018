package net.thumbtack.school.backend.threads.exercise_17;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Exercise_17 {

    // 17) Реализовать очередь многостадийных  задач.
    // Многостадийная задача - экземпляр класса Task, имеющего список из стадий - List<Executable>,
    // где Executable - интерфейс из задания 16. Потоки - разработчики ставят в очередь экземпляры Task,
    // потоки - исполнители берут из очереди задачу, исполняют очередную ее стадию, после чего,
    // если это не последняя стадия,  ставят задачу обратно в очередь.
    // Единственный поток - наблюдатель периодически распечатывает информацию о задачах,
    // которые в настоящее время исполняются (имя задачи, номер текущей стадии, количество стадий).
    // Количество тех и других потоков может быть любым и задается через командную строку main.
    // Предусмотреть вариант, когда число потоков конфигурируется автоматически.

    public static final int LIMIT = 30;
    public static volatile int countDevTask = 0;
    public static volatile int countPerfTask = 0;
    public static volatile Boolean isEnd = false;

    public static void main(String[] args) {

        Queue<MyMultiTask> taskQueue = new ConcurrentLinkedDeque<>();
        Object writeLock = new Object();
        Object readLock = new Object();

        TaskDeveloper taskDeveloper1 = new TaskDeveloper(taskQueue, writeLock);
        TaskDeveloper taskDeveloper2 = new TaskDeveloper(taskQueue, writeLock);
        TaskDeveloper taskDeveloper3 = new TaskDeveloper(taskQueue, writeLock);
        TaskDeveloper taskDeveloper4 = new TaskDeveloper(taskQueue, writeLock);

        TaskPerformer taskPerformer1 = new TaskPerformer(taskQueue,readLock);
        TaskPerformer taskPerformer2 = new TaskPerformer(taskQueue,readLock);
        TaskPerformer taskPerformer3 = new TaskPerformer(taskQueue,readLock);
        TaskPerformer taskPerformer4 = new TaskPerformer(taskQueue,readLock);

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

        while(countPerfTask!=LIMIT){
        }

        countDevTask = LIMIT+100;
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
