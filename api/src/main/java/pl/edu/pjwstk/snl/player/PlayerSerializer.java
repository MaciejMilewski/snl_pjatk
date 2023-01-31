package pl.edu.pjwstk.snl.player;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import pl.edu.pjwstk.snl.strategy.Strategy;
import pl.edu.pjwstk.snl.team.Team;

public class PlayerSerializer extends StdSerializer<Player> {
    public PlayerSerializer() {
        this(null);
    }

    public PlayerSerializer(Class<Player> t) {
        super(t);
    }
    @Override
    public void serialize(Player player, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", player.getId());
        jsonGenerator.writeStringField("name", player.getName());
        jsonGenerator.writeStringField("surname", player.getSurname());
        jsonGenerator.writeNumberField("playerNumber", player.getPlayerNumber());
        jsonGenerator.writeStringField("position", player.getPosition().toString());
        jsonGenerator.writeStringField("birthDate", player.getBirthDate().toString());
        jsonGenerator.writeStringField("birthPlace", player.getBirthplace());
        jsonGenerator.writeNumberField("weight", player.getWeight());
        jsonGenerator.writeStringField("stick", player.getStick().toString());
        jsonGenerator.writeBinaryField("image", player.getImage());

//        jsonGenerator.writeArrayFieldStart("teams");
//        for (Team team : player.getTeams()) {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeStringField("name", team.getName());
//            jsonGenerator.writeBinaryField("image", team.getImage());
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndArray();
        if (player.getTeams().stream().toList().size() != 0) {
            jsonGenerator.writeNumberField("teamId", player.getTeams().stream().toList().get(0).getId());
            jsonGenerator.writeBinaryField("teamImage", player.getTeams().stream().toList().get(0).getImage());
        }
        else {
            jsonGenerator.writeStringField("teamId", "No team assigned");
            byte[] emptyImage = new byte[0];
            jsonGenerator.writeBinaryField("teamImage", emptyImage);
        }

        jsonGenerator.writeEndObject();
    }
}
