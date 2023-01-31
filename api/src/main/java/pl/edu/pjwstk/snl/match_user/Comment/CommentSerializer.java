package pl.edu.pjwstk.snl.match_user.Comment;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import pl.edu.pjwstk.snl.five.FivePlayer;

public class CommentSerializer extends StdSerializer<Comment> {
    public CommentSerializer(Class<Comment> t) {
        super(t);
    }

    public CommentSerializer() {
        this(null);
    }
    @Override
    public void serialize(Comment comment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", comment.getId());
        jsonGenerator.writeStringField("author", comment.getMatchUser().getUser().getUsername());
        jsonGenerator.writeStringField("message", comment.getMessage());
        jsonGenerator.writeStringField("createdAt", comment.getCreatedAt().toString());

        jsonGenerator.writeEndObject();
    }
}
