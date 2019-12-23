package jaw.cardgame.util;

import com.google.gson.JsonObject;

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
}
