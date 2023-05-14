package ingkonkurs.services.transactions.codecs;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import ingkonkurs.services.transactions.Account;
import ingkonkurs.utils.abstractcodecs.AbstractEncoder;
import ingkonkurs.utils.abstractcodecs.IndentedPrettyPrinter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.TreeMap;

public class TransactionsEncoder extends AbstractEncoder {
    public static void encode(TreeMap<String, Account> array, ByteBuf out) {
        try {
            ByteBufOutputStream outStream = new ByteBufOutputStream(out);

            JsonGenerator generator = jsonFactory.createGenerator((OutputStream) outStream, JsonEncoding.UTF8);
            generator.setPrettyPrinter(new IndentedPrettyPrinter());
            generator.writeStartArray();

            for (Map.Entry<String, Account> entry : array.entrySet()) {
                Account account = entry.getValue();
                AccountCodec.serialize(generator, account);
            }

            generator.writeEndArray();

            generator.flush();
            generator.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
