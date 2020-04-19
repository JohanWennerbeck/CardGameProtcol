package jaw.cardgame.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import jaw.cardgame.Player;


public class PlayerConverterJson {

    private final static PlayerConverterJson INSTANCE = new PlayerConverterJson();

    public static PlayerConverterJson getInstance(){
        return INSTANCE;
    }

    private PlayerConverterJson(){

    }

    public JsonObject toJson(Player player) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("Name", player.getName());
        jsonObject.addProperty("First", player.getTribelloFirst());
        jsonObject.addProperty("Second", player.getTribelloSecond());
        jsonObject.addProperty("Third", player.getTribelloThird());
        jsonObject.addProperty("HighScore", player.getTribelloHighScore());
        jsonObject.addProperty("JumboScore", player.getTribelloJumboScore());

        return jsonObject;
    }

    public Player toObject(JsonObject object) {
        Player player = new Player();

        player.setName(object.get("Name").getAsString());
        player.setTribelloFirst(object.get("First").getAsInt());
        player.setTribelloSecond(object.get("Second").getAsInt());
        player.setTribelloThird(object.get("Third").getAsInt());
        player.setTribelloHighScore(object.get("HighScore").getAsInt());
        player.setTribelloJumboScore(object.get("JumboScore").getAsInt());

        return player;
    }

    public JsonArray toJson(ArrayList<String> list) {
        JsonArray jsonArray = new JsonArray();

        for (String string : list) {
            jsonArray.add(toJson(string));
        }
        return jsonArray;
    }

    private JsonObject toJson(String string) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("Name", string);

        return jsonObject;
    }

    public ArrayList<String> toObjectString(JsonArray array) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(toObjectString(object));
        }
        return list;
    }

    private String toObjectString(JsonObject object) {
        return object.get("Name").getAsString();
    }
}
