package ingkonkurs.services.onlinegame.handlers;

import ingkonkurs.services.onlinegame.Clan;
import ingkonkurs.services.onlinegame.OnlineGame;
import ingkonkurs.services.onlinegame.codecs.OnlineGameEncoder;
import ingkonkurs.utils.decoder.JSONDecoder;
import ingkonkurs.utils.response.ResponseHandler;
import ingkonkurs.utils.sorting.ParallelMergeSort;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

@ChannelHandler.Sharable
public class OnlineGameHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf request) throws Exception {

        //Zamiana formatu JSON na obiekt klasy OnlineGame
        OnlineGame onlineGame = JSONDecoder.decode(request, OnlineGame.class);

        //Sortowanie po polu "points" i "numberOfPlayers"
        ParallelMergeSort.parallelMergeSort(onlineGame.getClans(), true);

        //Rozdzielenie klanów na osobne grupy
        List<List<Clan>> queue = new ArrayList<>();
        clanSplitter(onlineGame, queue);

        //Zamiana pogrupowanych klanów na format JSON
        ByteBuf out = Unpooled.buffer();
        OnlineGameEncoder.encode(queue, out);

        //Wysłanie odpowiedzi do klienta
        ResponseHandler.sendResponse(ctx, OK, out);

        //Usunięcie serwisu z szyny Netty
        ctx.pipeline().remove(this);
    }

    private void clanSplitter(OnlineGame onlineGame, List<List<Clan>> queue){
        List<Integer> counter = new ArrayList<>();
        boolean used;

        for (Clan clan : onlineGame.getClans()) {
            used = false;
            for (int j = 0; j < counter.size(); j++) {
                if (onlineGame.getGroupCount() - counter.get(j) >= clan.getNumberOfPlayers()) {
                    counter.set(j, counter.get(j) + clan.getNumberOfPlayers());
                    queue.get(j).add(clan);
                    used = true;
                    break;
                }
            }
            if (!used) {
                List<Clan> temp = new ArrayList<>();
                temp.add(clan);
                queue.add(temp);
                counter.add(clan.getNumberOfPlayers());
            }
        }
    }
}
