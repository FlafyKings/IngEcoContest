package ingkonkurs.services.onlinegame.codecs;

import com.fasterxml.jackson.core.JsonGenerator;

import ingkonkurs.services.onlinegame.Clan;

import java.io.IOException;

public class ClanCodec {
    public static void serialize(JsonGenerator generator, Clan clan) throws IOException {
        generator.writeStartObject();

        generator.writeFieldName("numberOfPlayers");
        generator.writeNumber(clan.getNumberOfPlayers());

        generator.writeFieldName("points");
        generator.writeNumber(clan.getPoints());

        generator.writeEndObject();
    }
}
