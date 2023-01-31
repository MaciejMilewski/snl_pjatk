package pl.edu.pjwstk.snl.match_user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class MatchUserSerializer extends StdSerializer<MatchUser> {
    public MatchUserSerializer(Class<MatchUser> t) {
        super(t);
    }

    public MatchUserSerializer() {
        this(null);
    }

    @Override
    public void serialize(MatchUser matchUser, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", matchUser.getId());
        jsonGenerator.writeStringField("username",matchUser.getUser().getUsername());
        jsonGenerator.writeStringField("role", matchUser.getRole().toString());

        jsonGenerator.writeEndObject();
    }
}
