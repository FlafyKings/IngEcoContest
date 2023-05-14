package ingkonkurs.services.transactions.codecs;

import com.fasterxml.jackson.core.JsonGenerator;
import ingkonkurs.services.transactions.Account;
import java.io.IOException;

public class AccountCodec{

    public static void serialize(JsonGenerator generator, Account account) throws IOException {
        generator.writeStartObject();

        generator.writeFieldName("account");
        generator.writeString(account.getAccountNumber());

        generator.writeFieldName("debitCount");
        generator.writeNumber(account.getDebitCount());

        generator.writeFieldName("creditCount");
        generator.writeNumber(account.getCreditCount());

        generator.writeFieldName("balance");
        generator.writeNumber(account.getBalance());

        generator.writeEndObject();
    }
}
