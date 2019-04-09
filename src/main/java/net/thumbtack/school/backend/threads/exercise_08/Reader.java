package net.thumbtack.school.backend.threads.exercise_08;

import net.thumbtack.school.backend.threads.exercise_08.Book;

class Reader extends Thread {
    private Book book;

    public Reader(Book book) {
        this.book = book;
    }

    public void run() {
        while (true) {
            book.get();
        }
    }

}

