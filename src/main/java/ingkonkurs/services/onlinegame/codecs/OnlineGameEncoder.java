package ingkonkurs.services.onlinegame.codecs;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

import ingkonkurs.services.onlinegame.Clan;
import ingkonkurs.utils.abstractcodecs.AbstractEncoder;

import ingkonkurs.utils.abstractcodecs.IndentedPrettyPrinter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.List;

public class OnlineGameEncoder extends AbstractEncoder {
    public static void encode(List<List<Clan>> queue, ByteBuf out) {
        try {
            ByteBufOutputStream outStream = new ByteBufOutputStream(out);

            JsonGenerator generator = jsonFactory.createGenerator((OutputStream) outStream, JsonEncoding.UTF8);
            generator.setPrettyPrinter(new IndentedPrettyPrinter());
            generator.writeStartArray();

            for (List<Clan> group : queue) {
                generator.writeStartArray();
                for (Clan clan : group) {
                    ClanCodec.serialize(generator, clan);
                }
                generator.writeEndArray();
            }

            generator.writeEndArray();
            generator.flush();
            generator.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
