package net.thumbtack.school.backend.threads.exercise_08;

import net.thumbtack.school.backend.threads.exercise_08.Book;

class Writer extends Thread {

    private Book book;

    public Writer(Book book) {
        this.book = book;
    }

    public void run() {
        int i = 0;
        while (true) {
            book.put(i++);
        }
    }

}


