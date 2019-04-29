package net.thumbtack.school.backend.asynchrony.exersice_5;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Asynchronous http server based on NIO selectors.
 */
public class SelectorsServer {

    public static void main(String[] args) throws IOException {

        Gson json = new Gson();
        String httpResponse = "";

        Map<SocketChannel,String> responses = new HashMap<>();

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
                    int start = result.indexOf('{');
                    int end = result.lastIndexOf('}');
                    System.out.println(start);
                    System.out.println(end);

                    if(start==-1||end==-1){
                        httpResponse = "HTTP/1.1 200 OK\n" +
                                "Content-Length:73\n" +
                                "Content-Type: text/html\n" +
                                "Connection: Closed\n\n" +
                                "<html>\n" +
                                "<body>\n" +
                                "<h1>Please, send correct JSON object!</h1>\n" +
                                "</body>\n" +
                                "</html>";
                        key.interestOps(SelectionKey.OP_WRITE);
                    }else {
                        String request = result.substring(start, end + 1);
                        try {
                            SumOfTwo sumOfTwo = json.fromJson(request, SumOfTwo.class);
                            int sum = sumOfTwo.doSum();
                            String response =
                                    "<html>\n" +
                                            "<body>\n" +
                                            "<h1>" + sum + "</h1>\n" +
                                            "</body>\n" +
                                            "</html>";
                            int content_length = response.length();
                            httpResponse = "HTTP/1.1 200 OK\n" +
                                    "Content-Length:" + content_length + "\n" +
                                    "Content-Type: text/html\n" +
                                    "Connection: Closed\n\n" + response;
                            responses.put(client,httpResponse);
                        } catch (JsonSyntaxException ex) {
                            System.out.println(ex);
                        }
                        log("Message received: " + result);
                        key.interestOps(SelectionKey.OP_WRITE);
                    }

                } else if (key.isWritable()) {
                    System.out.println(httpResponse);
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer writeBuffer = Charset.forName("UTF-8").encode(responses.get(client));
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
