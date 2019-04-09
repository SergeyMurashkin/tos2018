package net.thumbtack.school.backend.threads.exercise_08;

public class Exercise_08 {

    // 8) Система читатель-писатель.

    public static void main(String[] args) {
        Book book = new Book();
        new Writer(book).start();
        new Reader(book).start();
        System.out.println("Press Control-F2 to stop.");
    }

}
