package ingkonkurs.services.atmservice.codecs;

import com.fasterxml.jackson.core.JsonGenerator;

import ingkonkurs.services.atmservice.Task;

import java.io.IOException;

public class TaskCodec {
    public static void serialize(JsonGenerator generator, Task task) throws IOException {
        generator.writeStartObject();

        generator.writeFieldName("region");
        generator.writeNumber(task.getRegion());

        generator.writeFieldName("atmId");
        generator.writeNumber(task.getAtm());

        generator.writeEndObject();
    }
}
