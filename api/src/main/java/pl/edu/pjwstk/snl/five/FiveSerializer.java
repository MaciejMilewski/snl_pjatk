package pl.edu.pjwstk.snl.five;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import pl.edu.pjwstk.snl.match.MatchPlayer;
import pl.edu.pjwstk.snl.player.Player;

public class FiveSerializer extends StdSerializer<Five> {
    public FiveSerializer(Class<Five> t) {
        super(t);
    }

    public FiveSerializer() {
        this(null);
    }

    @Override
    public void serialize(Five five, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", five.getId());
        jsonGenerator.writeStringField("name", five.getName());

        jsonGenerator.writeArrayFieldStart("players");
        for (FivePlayer player : five.getPlayers()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", player.getPlayer().getId());
            jsonGenerator.writeNumberField("fivePlayerId", player.getId());
            jsonGenerator.writeStringField("name", player.getPlayer().getName());
            jsonGenerator.writeStringField("surname", player.getPlayer().getSurname());
            jsonGenerator.writeStringField("position", player.getPosition().toString());
            jsonGenerator.writeNumberField("number", player.getNumber());
            jsonGenerator.writeBinaryField("image", player.getPlayer().getImage());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
