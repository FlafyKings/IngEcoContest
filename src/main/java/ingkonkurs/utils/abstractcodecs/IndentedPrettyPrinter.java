package ingkonkurs.utils.abstractcodecs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.IOException;

public class IndentedPrettyPrinter extends DefaultPrettyPrinter {

    public IndentedPrettyPrinter() {
        this._arrayIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
    }

    @Override
    public DefaultPrettyPrinter createInstance() {
        return new IndentedPrettyPrinter();
    }

    @Override
    public void writeObjectFieldValueSeparator(JsonGenerator jg) throws IOException {
        jg.writeRaw(": ");
    }
}
