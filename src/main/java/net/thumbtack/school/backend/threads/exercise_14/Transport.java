package net.thumbtack.school.backend.threads.exercise_14;

public class Transport extends Thread {

    private Message message;

    public Transport(Message message) {
        this.message = message;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start.");
        send(message);
        System.out.println(Thread.currentThread().getName() + " End.");
    }


    public void send(Message message) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Message not send " + message);
        }
        System.out.println("Send " + message);
    }

}
