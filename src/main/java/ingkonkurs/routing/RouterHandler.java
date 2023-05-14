package ingkonkurs.routing;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.Map;

import static ingkonkurs.routing.Routes.getRoutes;

public class RouterHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest request) {
            String method = request.method().name();
            URI uri = new URI(request.uri());
            String endpoint = uri.getPath();


            Map<String, ChannelHandler> methodRoutes = getRoutes().get(method);
            if (methodRoutes != null) {
                ChannelHandler handler = methodRoutes.get(endpoint);
                if (handler != null) {
                    ctx.pipeline().addAfter(ctx.name(), "handler", handler);
                    ctx.fireChannelRead(request.content());
                }
            }else{
                ctx.fireExceptionCaught(new IllegalArgumentException("Niewspierana metoda HTTP: " + method));
                FullHttpResponse response = new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST,
                        Unpooled.copiedBuffer("Niewspierana metoda HTTP: " + method, CharsetUtil.UTF_8));
                ctx.writeAndFlush(response);
                ctx.close();
            }
        }
    }
}
