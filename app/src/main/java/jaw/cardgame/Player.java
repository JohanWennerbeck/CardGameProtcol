package jaw.cardgame;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;

import jaw.cardgame.util.PlayerConverterJson;
import jaw.cardgame.util.StorageUtil;

public class Player {



    private String name;
    private int trebelloFirst;
    private int trebelloSecond;
    private int trebelloThird;
    private int trebelloHighscore;
    private int trebelloJumboScore;

    public Player() {

    }

    void save(Context context, String id){
        JsonObject object = PlayerConverterJson.getInstance().toJson(this);
        StorageUtil.save(context.getApplicationContext(), id, object);
    }

    void load(Context context, String id){
        JsonElement element = null;
        try {
            element = StorageUtil.load(context.getApplicationContext(), id);
        } catch (IllegalStateException e) {
            StorageUtil.resetData(context.getApplicationContext(), id);

        } catch (FileNotFoundException ignored) {
        }

        if (element == null || !element.isJsonArray()) {
            return;
        }
        JsonObject object = element.getAsJsonObject();

        Player player = PlayerConverterJson.getInstance().toObject(object);
        this.name = player.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
