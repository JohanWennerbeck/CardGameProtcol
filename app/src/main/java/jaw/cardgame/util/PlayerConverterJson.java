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

        return jsonObject;
    }

    public Player toObject(JsonObject object) {
        Player player = new Player();

        player.setName(object.get("Name").getAsString());

        return player;
    }
}
