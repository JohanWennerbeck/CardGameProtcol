package jaw.cardgame.util;


import com.google.gson.JsonObject;

public interface IConverterJson<T> {

    JsonObject toJson(T object);

    T toObject(JsonObject object);
}