package ingkonkurs.config;

import ingkonkurs.routing.RouterHandler;
import ingkonkurs.utils.exceptionhandler.ExceptionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    private final int maxLength = 400 * 1024 * 1024;

    @Override
    public void initChannel(SocketChannel ch){
        ChannelPipeline pipeline = ch.pipeline();

        // HTTP Codec do przetwarzania zapytań
        pipeline.addLast(new HttpServerCodec());

        //HTTP Aggregator do łączenia wielu komunikatów w jeden FullHttpRequest lub FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(maxLength));

        //Router Handler do przekierowywania zapytań do poszczególnych serwisów
        pipeline.addLast(new RouterHandler());

        pipeline.addLast(new ExceptionHandler());
    }
}
