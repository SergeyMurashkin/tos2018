package net.thumbtack.school.backend.asynchrony.exersice_4;

import java.io.IOException;
import java.net.Socket;

public class CrashingServer {

    public static void main(String[] args) {
        int i;
        Socket[] sockets = new Socket[20000];
        for (i=0 ; i < sockets.length; i++) {
            try {
                sockets[i] = new Socket("localhost", 5000);
                if(i%100==0) {
                    System.out.println(i);
                }
            } catch (IOException e) {
                System.out.println( "Error on " + i + " connection.");
                break;
            }
        }
        try {
            System.out.println("I'm sleeping!");
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I wake up!");
    }



}
