package jaw.cardgame.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class TribelloGameConverterJson {

    private final static TribelloGameConverterJson INSTANCE = new TribelloGameConverterJson();

    private TribelloGameConverterJson(){

    }

    public static TribelloGameConverterJson getInstance(){
        return INSTANCE;
    }

    public JsonArray toJson(ArrayList<Integer> list, String name) {
        JsonArray jsonArray = new JsonArray();

        for (Integer integer : list) {
            jsonArray.add(toJson(integer, name));
        }
        return jsonArray;
    }

    public JsonArray toJson(boolean[] booleans, String name) {
        JsonArray jsonArray = new JsonArray();

        for (boolean bool : booleans) {
            jsonArray.add(toJson(bool, name));
        }
        return jsonArray;
    }

    public JsonObject toJson(Integer integer, String name) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(name, integer);

        return jsonObject;
    }

    public JsonObject toJson(boolean bool, String name) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(name, bool);

        return jsonObject;
    }

    public JsonObject toJson(int integer, String name) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(name, integer);

        return jsonObject;
    }

    public JsonObject toJson(String string, String name) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(name, string);

        return jsonObject;
    }

    public ArrayList<Integer> toObject(JsonArray array, String name) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(toObjectInt(object, name));
        }
        return list;
    }

    public boolean[] toObjectBool(JsonArray array, String name) {
        boolean[] booleans = new boolean[4];
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            booleans[i] = toObjectBool(object, name);
        }
        return booleans;
    }

    public boolean toObjectBool(JsonObject object, String name) {
        boolean bool = object.get("GameOption").getAsBoolean();
        return bool;
    }

    public int toObjectInt(JsonObject object, String name){
        int integer = object.get(name).getAsInt();
        return integer;
    }

    public String toObjectString(JsonObject object, String name){
        String string = object.get(name).getAsString();
        return string;
    }
}
