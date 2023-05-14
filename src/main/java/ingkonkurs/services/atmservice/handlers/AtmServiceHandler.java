package ingkonkurs.services.atmservice.handlers;

import ingkonkurs.services.atmservice.Task;
import ingkonkurs.services.atmservice.codecs.AtmServiceEncoder;
import ingkonkurs.utils.decoder.JSONDecoder;
import ingkonkurs.utils.response.ResponseHandler;
import ingkonkurs.utils.sorting.ParallelMergeSort;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;



@ChannelHandler.Sharable
public class AtmServiceHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf request) throws Exception {

        //Zamiana formatu JSON na tablice klasy Task
        Task[] tasks = JSONDecoder.decode(request, Task[].class);

        //Sortowanie po polu "region" i "requestType"
        ParallelMergeSort.parallelMergeSort(tasks, false);

        //Zamiana posortowanej tablicy klasy Task na format JSON
        ByteBuf out = Unpooled.buffer();
        AtmServiceEncoder.encode(tasks, out);

        //Wysłanie odpowiedzi do klienta
        ResponseHandler.sendResponse(ctx, OK, out);

        //Usunięcie serwisu z szyny Netty
        ctx.pipeline().remove(this);
    }
}

