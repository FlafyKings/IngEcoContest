package ingkonkurs.utils.abstractcodecs;

import com.fasterxml.jackson.core.JsonFactory;

public class AbstractEncoder {
    protected static final JsonFactory jsonFactory = getDefaultJsonFactory();

    private static JsonFactory getDefaultJsonFactory() {
        return new JsonFactory();
    }
}
