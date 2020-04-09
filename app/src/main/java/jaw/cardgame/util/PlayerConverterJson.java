package jaw.cardgame.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

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
        jsonObject.addProperty("First", player.getTrebelloFirst());
        jsonObject.addProperty("Second", player.getTrebelloSecond());
        jsonObject.addProperty("Third", player.getTrebelloThird());
        jsonObject.addProperty("HighScore", player.getTrebelloHighScore());
        jsonObject.addProperty("JumboScore", player.getTrebelloJumboScore());

        return jsonObject;
    }

    public Player toObject(JsonObject object) {
        Player player = new Player();

        player.setName(object.get("Name").getAsString());
        player.setTrebelloFirst(object.get("First").getAsInt());
        player.setTrebelloSecond(object.get("Second").getAsInt());
        player.setTrebelloThird(object.get("Third").getAsInt());
        player.setTrebelloHighScore(object.get("HighScore").getAsInt());
        player.setTrebelloJumboScore(object.get("JumboScore").getAsInt());

        return player;
    }

    public JsonArray toJson(ArrayList<String> list) {
        JsonArray jsonArray = new JsonArray();

        for (String string : list) {
            jsonArray.add(toJson(string));
        }
        return jsonArray;
    }

    public JsonObject toJson(String string) {
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

    public String toObjectString(JsonObject object) {
        String string = object.get("Name").getAsString();
        return string;
    }
}
