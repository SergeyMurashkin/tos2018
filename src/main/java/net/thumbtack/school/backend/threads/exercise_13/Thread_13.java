package net.thumbtack.school.backend.threads.exercise_13;

import java.util.Date;

public class Thread_13 extends Thread{

    private Formatter formatter;

    Thread_13(Formatter formatter){
        this.formatter = formatter;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName());
        System.out.println(formatter.format(new Date()));
    }
}
