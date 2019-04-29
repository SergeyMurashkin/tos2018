package net.thumbtack.school.backend.asynchrony.exersice_7.ThirdPartyCourseServer;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.bootstrap.Bootstrap;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class RestService {

    private final static String REQUEST = "GET /api/v7/convert?q=USD_RUB&compact=y&apiKey=e8433a5176ac39fa04d2 HTTP/1.1\r\n" +
            "Host: free.currconv.com\r\n" +
            "Accept: */*\r\n" +
            "Connection: close\r\n\r\n";


    public void process(ChannelHandlerContext ctx, String requestJson) throws Exception {
        Channel inboundChannel = ctx.channel();

        Bootstrap b = new Bootstrap();
        b.group(inboundChannel.eventLoop())
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("free.currconv.com", 80))
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    public void initChannel(Channel ch) {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new HttpResponseDecoder());
                        p.addLast(new HttpObjectAggregator(100 * 1024));
                        p.addLast(new ClientHandler(ctx, requestJson));
                    }
                });
        b.connect();
    }


    private static class ClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

        private ChannelHandlerContext serverctx;
        private String quantity;

        public ClientHandler(ChannelHandlerContext serverctx, String quantity) {
            this.serverctx = serverctx;
            this.quantity = quantity;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println(REQUEST);
            ctx.writeAndFlush(Unpooled.copiedBuffer(REQUEST, CharsetUtil.UTF_8));
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, FullHttpResponse in) {
            String result = in.content().toString(CharsetUtil.UTF_8);

            JsonElement parsedObject = new JsonParser().parse(result);
            JsonObject jsonObject = parsedObject.getAsJsonObject();
            jsonObject = jsonObject.getAsJsonObject("USD_RUB");
            double USD_RUB = jsonObject.get("val").getAsDouble();
            System.out.println(USD_RUB);
            System.out.println(quantity);
            double q = Double.valueOf(quantity);



            String responseAmount = "USD amount: " + q + ",\n" +
                    "USD exchange rate: " + USD_RUB + ",\n" +
                    "RUB amount: " + q*USD_RUB + ".";

            FullHttpResponse response = new DefaultFullHttpResponse(
                    HTTP_1_1,
                    OK,
                    Unpooled.copiedBuffer(responseAmount, CharsetUtil.UTF_8));
            response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

            serverctx.writeAndFlush(response)
                    .addListener(ChannelFutureListener.CLOSE);

        }

        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }




}
