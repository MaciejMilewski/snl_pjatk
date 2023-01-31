package pl.edu.pjwstk.snl.strategy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class StrategySerializer extends StdSerializer<Strategy> {
    public StrategySerializer() {
        this(null);
    }

    public StrategySerializer(Class<Strategy> t) {
        super(t);
    }

    @Override
    public void serialize(Strategy strategy, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", strategy.getId());
        jsonGenerator.writeStringField("name", strategy.getName());
        jsonGenerator.writeBinaryField("image", strategy.getImage());
        jsonGenerator.writeStringField("team", strategy.getTeam().getName());
        jsonGenerator.writeEndObject();
    }
}
