package pl.edu.pjwstk.snl.schedule;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class ScheduleSerializer extends StdSerializer<Schedule> {
    public ScheduleSerializer() {
        this(null);
    }

    public ScheduleSerializer(Class<Schedule> t) {
        super(t);
    }

    @Override
    public void serialize(Schedule schedule, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", schedule.getId());
        jsonGenerator.writeStringField("description", schedule.getDescription());
        jsonGenerator.writeBinaryField("image", schedule.getImage());
        jsonGenerator.writeStringField("team", schedule.getTeam().getName());
        jsonGenerator.writeEndObject();
    }
}
