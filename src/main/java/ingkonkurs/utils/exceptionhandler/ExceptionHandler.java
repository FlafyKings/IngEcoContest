package ingkonkurs.utils.exceptionhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ExceptionHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof java.net.SocketException) {
            System.err.println("Connection reset by client - closing context");
             ctx.close();
        } else {
            super.exceptionCaught(ctx, cause);
        }
    }
}
