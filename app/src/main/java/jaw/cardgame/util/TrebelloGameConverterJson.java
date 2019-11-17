package jaw.cardgame.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class TrebelloGameConverterJson{

    private final static TrebelloGameConverterJson INSTANCE = new TrebelloGameConverterJson();

    public static TrebelloGameConverterJson getInstance(){
        return INSTANCE;
    }

    private TrebelloGameConverterJson(){

    }

    public JsonArray toJson(ArrayList<Integer> list) {
        JsonArray jsonArray = new JsonArray();

        for (Integer integer : list) {
            jsonArray.add(toJson(integer));
        }
        return jsonArray;
    }

    public JsonArray toJson(boolean[] booleans) {
        JsonArray jsonArray = new JsonArray();

        for (boolean bool : booleans) {
            jsonArray.add(toJson(bool));
        }
        return jsonArray;
    }

    public JsonObject toJson(Integer integer) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("Score", integer);

        return jsonObject;
    }

    public JsonObject toJson(boolean bool) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("GameOption", bool);

        return jsonObject;
    }

    public JsonObject toJson(int integer, String name) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(name, integer);

        return jsonObject;
    }

    public List toObject(JsonArray array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(toObject(object));
        }
        return list;
    }

    public boolean[] toObjectBool(JsonArray array) {
        boolean[] booleans = new boolean[4];
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            booleans[i] = toObjectBool(object);
        }
        return booleans;
    }

    public int toObject(JsonObject object) {
        Integer integer = object.get("Score").getAsInt();
        return integer;
    }

    public boolean toObjectBool(JsonObject object) {
        boolean bool = object.get("GameOption").getAsBoolean();
        return bool;
    }

    public int toObject(JsonObject object, String name){
        int integer = object.get(name).getAsInt();
        return integer;
    }
}
