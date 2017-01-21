package ua.challenge.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by d.bakal on 10.12.2016.
 */
public class CustomSerializer implements JsonSerializer<Custom>, JsonDeserializer<Custom> {
    @Override
    public JsonElement serialize(Custom custom, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("date", custom.getDate().getTime());
        object.addProperty("integer", custom.getInteger());
        return object;
    }

    @Override
    public Custom deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Date date = new Date(object.get("date").getAsLong());
        BigInteger integer = new BigInteger(object.get("integer").getAsString());
        return new Custom(date, integer);
    }
}
