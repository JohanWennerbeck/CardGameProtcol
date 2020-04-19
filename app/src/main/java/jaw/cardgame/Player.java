package jaw.cardgame;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import jaw.cardgame.util.PlayerConverterJson;
import jaw.cardgame.util.StorageUtil;

public class Player {



    private String name;
    private int tribelloFirst;
    private int tribelloSecond;
    private int tribelloThird;
    private int tribelloHighScore;
    private int tribelloJumboScore;

    Player(String name) {
        this.name = name;
        tribelloFirst = 0;
        tribelloSecond = 0;
        tribelloThird = 0;
        tribelloHighScore = 0;
        tribelloJumboScore = 0;
    }

    public Player() {
        tribelloFirst = 0;
        tribelloSecond = 0;
        tribelloThird = 0;
        tribelloHighScore = 0;
        tribelloJumboScore = 0;
    }

    void save(Context context, String id){
        JsonObject object = PlayerConverterJson.getInstance().toJson(this);
        StorageUtil.save(context.getApplicationContext(), id, object);
    }

    boolean saveNewPlayer(Context context, String id){
        JsonElement element = null;
        try {
            element = StorageUtil.load(context.getApplicationContext(), "All_players");
        } catch (IllegalStateException e){
            StorageUtil.resetData(context.getApplicationContext(), "All_players");
        } catch (FileNotFoundException ignored){
        }

        if(element == null || !element.isJsonArray()){
            //First time one player is added
            ArrayList<String> initAllNames = new ArrayList<>();
            initAllNames.add(id);
            JsonArray newArray = PlayerConverterJson.getInstance().toJson(initAllNames);
            StorageUtil.save(context.getApplicationContext(), "All_players", newArray);
            return true;
        } else {
            JsonArray array = element.getAsJsonArray();
            ArrayList<String> allNames = PlayerConverterJson.getInstance().toObjectString(array);
            if (!allNames.contains(id)) {
                allNames.add(id);
                JsonArray newArray = PlayerConverterJson.getInstance().toJson(allNames);
                StorageUtil.save(context.getApplicationContext(), "All_players", newArray);
            } else {
                //Name already exists
                return false;
            }
        }
        JsonObject object = PlayerConverterJson.getInstance().toJson(this);
        StorageUtil.save(context.getApplicationContext(), id, object);
        return true;
    }
    void load(Context context, String id){
        JsonElement element = null;
        try {
            element = StorageUtil.load(context.getApplicationContext(), id);
        } catch (IllegalStateException e) {
            StorageUtil.resetData(context.getApplicationContext(), id);

        } catch (FileNotFoundException ignored) {
        }

        if (element == null || !element.isJsonObject()) {
            return;
        }
        JsonObject object = element.getAsJsonObject();

        Player player = PlayerConverterJson.getInstance().toObject(object);
        this.name = player.getName();
        this.tribelloFirst = player.getTribelloFirst();
        this.tribelloSecond = player.getTribelloSecond();
        this.tribelloThird = player.getTribelloThird();
        this.tribelloHighScore = player.getTribelloHighScore();
        this.tribelloJumboScore = player.getTribelloJumboScore();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTribelloJumboScore() {
        return tribelloJumboScore;
    }

    public void setTribelloJumboScore(int tribelloJumboScore) {
        this.tribelloJumboScore = tribelloJumboScore;
    }

    public int getTribelloHighScore() {
        return tribelloHighScore;
    }

    public void setTribelloHighScore(int tribelloHighscore) {
        this.tribelloHighScore = tribelloHighscore;
    }

    public int getTribelloThird() {
        return tribelloThird;
    }

    public void setTribelloThird(int tribelloThird) {
        this.tribelloThird = tribelloThird;
    }

    public int getTribelloSecond() {
        return tribelloSecond;
    }

    public void setTribelloSecond(int tribelloSecond) {
        this.tribelloSecond = tribelloSecond;
    }

    public int getTribelloFirst() {
        return tribelloFirst;
    }

    public void setTribelloFirst(int tribelloFirst) {
        this.tribelloFirst = tribelloFirst;
    }
}
