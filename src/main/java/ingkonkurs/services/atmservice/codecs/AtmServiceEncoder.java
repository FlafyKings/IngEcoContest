package ingkonkurs.services.atmservice.codecs;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

import ingkonkurs.services.atmservice.Task;
import ingkonkurs.utils.abstractcodecs.AbstractEncoder;
import ingkonkurs.utils.abstractcodecs.IndentedPrettyPrinter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

public class AtmServiceEncoder extends AbstractEncoder {

    public static void encode(Task[] tasks, ByteBuf out) {
        try {
            ByteBufOutputStream outStream = new ByteBufOutputStream(out);

            JsonGenerator generator = jsonFactory.createGenerator((OutputStream) outStream, JsonEncoding.UTF8);
            generator.setPrettyPrinter(new IndentedPrettyPrinter());
            generator.writeStartArray();

            Map<Integer, Integer> map = new HashMap<>();
            int prevKey = tasks[0].getRegion();

            for (Task task : tasks) {
                int key = task.getRegion();

                if (key != prevKey) {
                    map.clear();
                    prevKey = key;
                }

                int val = task.getRequestType();
                int subKey = task.getAtm();

                if (map.containsKey(subKey)) {
                    if (val < map.get(subKey)) {
                        map.put(subKey, val);
                    }
                } else {
                    map.put(subKey, val);
                    TaskCodec.serialize(generator, task);
                }
            }

            generator.writeEndArray();
            generator.flush();
            generator.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
