package net.thumbtack.school.backend.threads.exercise_07;

public class Exercise_07 {

    // 7) “Ping Pong”, задача заключается в том чтобы бесконечно выводить на консоль сообщения “ping” или “pong”
    // из двух разных потоков. При этом сообщения обязаны чередоваться,
    // т.е. не может быть ситуации когда ping или pong появляется в консоли более одного раза подряд.

    public static void main(String[] args) {
        final Object lock = new Object();
        Ping ping = new Ping(lock);
        Pong pong = new Pong(lock);
        ping.start();
        pong.start();
        try {
            ping.join();
            pong.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main stop");
    }

}
