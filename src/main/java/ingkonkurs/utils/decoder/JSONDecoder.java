package ingkonkurs.utils.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

public class JSONDecoder {
    protected static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }

    public static <T> T decode(ByteBuf request, Class<T> clazz) throws IOException {
        byte[] bytes = new byte[request.readableBytes()];
        request.readBytes(bytes);

        return objectMapper.readValue(bytes, clazz);
    }
}
