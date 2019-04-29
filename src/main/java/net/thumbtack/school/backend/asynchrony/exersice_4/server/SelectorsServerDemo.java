package net.thumbtack.school.backend.asynchrony.exersice_4.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;



/**
 * Asynchronous http server based on NIO selectors.
 */
public class SelectorsServerDemo {

    // crashed on 16200 connection

    private static final String HTTP_RESPONSE = "HTTP/1.1 200 OK\n" +
            "Content-Length: 50\n" +
            "Content-Type: text/html\n" +
            "Connection: Closed\n\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Hello, World!</h1>\n" +
            "</body>\n" +
            "</html>";

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 5000));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT, null);

        while (true) {

            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + clientChannel.getLocalAddress() + "\n");

                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int n = client.read(buffer);
                    if (n == -1) {
                        client.close();
                    }
                    String result = new String(buffer.array()).trim();
                    log("Message received: " + result);
                    key.interestOps(SelectionKey.OP_WRITE);

                } else if (key.isWritable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer writeBuffer = Charset.forName("UTF-8").encode(HTTP_RESPONSE);
                    client.write(writeBuffer);
                    if (!writeBuffer.hasRemaining()) {
                        writeBuffer.compact();
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    client.close();
                }
                iterator.remove();
            }
        }
    }

    private static void log(String str) {
        System.out.println(str);
    }
}
