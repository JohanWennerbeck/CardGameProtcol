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
    private int trebelloFirst;
    private int trebelloSecond;
    private int trebelloThird;
    private int trebelloHighScore;
    private int trebelloJumboScore;

    Player(String name) {
        this.name = name;
        trebelloFirst = 0;
        trebelloSecond = 0;
        trebelloThird = 0;
        trebelloHighScore = 0;
        trebelloJumboScore = 0;
    }

    public Player() {
        trebelloFirst = 0;
        trebelloSecond = 0;
        trebelloThird = 0;
        trebelloHighScore = 0;
        trebelloJumboScore = 0;
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
        this.trebelloFirst = player.getTrebelloFirst();
        this.trebelloSecond = player.getTrebelloSecond();
        this.trebelloThird = player.getTrebelloThird();
        this.trebelloHighScore = player.getTrebelloHighScore();
        this.trebelloJumboScore = player.getTrebelloJumboScore();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrebelloJumboScore() {
        return trebelloJumboScore;
    }

    public void setTrebelloJumboScore(int trebelloJumboScore) {
        this.trebelloJumboScore = trebelloJumboScore;
    }

    public int getTrebelloHighScore() {
        return trebelloHighScore;
    }

    public void setTrebelloHighScore(int trebelloHighscore) {
        this.trebelloHighScore = trebelloHighscore;
    }

    public int getTrebelloThird() {
        return trebelloThird;
    }

    public void setTrebelloThird(int trebelloThird) {
        this.trebelloThird = trebelloThird;
    }

    public int getTrebelloSecond() {
        return trebelloSecond;
    }

    public void setTrebelloSecond(int trebelloSecond) {
        this.trebelloSecond = trebelloSecond;
    }

    public int getTrebelloFirst() {
        return trebelloFirst;
    }

    public void setTrebelloFirst(int trebelloFirst) {
        this.trebelloFirst = trebelloFirst;
    }
}
