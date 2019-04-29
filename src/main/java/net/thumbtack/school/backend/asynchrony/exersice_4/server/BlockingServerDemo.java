package net.thumbtack.school.backend.asynchrony.exersice_4.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServerDemo {

    // crashed on 16200 connection

    private static final String HTTP_RESPONSE =
            "HTTP/1.1 200 OK\n" +
            "Content-Length: 50\n" +
            "Content-Type: text/html\n" +
            "Connection: Closed\n" +
            "\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Hello, World!</h1>\n" +
            "</body>\n" +
            "</html>";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSocket.getOutputStream().write(HTTP_RESPONSE.getBytes());
            clientSocket.close();
        }

    }
}
