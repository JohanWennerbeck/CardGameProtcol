package jaw.cardgame;

import android.content.Context;
import android.os.Bundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import jaw.cardgame.util.StorageUtil;
import jaw.cardgame.util.TrebelloGameConverterJson;

class TrebelloGameModel {

    private int playerOneScore, playerTwoScore, playerThreeScore;
    private int playerOneTotalScore, playerTwoTotalScore, playerThreeTotalScore;
    private ArrayList<Integer> playerOneScoreArray;
    private ArrayList<Integer> playerTwoScoreArray;
    private ArrayList<Integer> playerThreeScoreArray;
    private int round;
    private boolean[][] checkedGameOptions = new boolean[3][4];

    private Player playerOne, playerTwo, playerThree;

    private static final String STATE_ROUND = "round";
    private static final String STATE_PERSON_ONE_SCORE = "playerOneScore";
    private static final String STATE_PERSON_TWO_SCORE = "playerTwoScore";
    private static final String STATE_PERSON_THREE_SCORE = "playerThreeScore";
    private static final String STATE_PERSON_ONE_SCORE_ARRAY = "playerOneScoreArray";
    private static final String STATE_PERSON_TWO_SCORE_ARRAY = "playerTwoScoreArray";
    private static final String STATE_PERSON_THREE_SCORE_ARRAY = "playerThreeScoreArray";
    private static final String STATE_PERSON_ONE_ROUND_SCORE = "playerOneRoundScore";
    private static final String STATE_PERSON_TWO_ROUND_SCORE = "playerTwoRoundScore";
    private static final String STATE_PERSON_THREE_ROUND_SCORE = "playerThreeRoundScore";
    private static final String STATE_GAME_OPTION_ONE = "gameOptionOne";
    private static final String STATE_GAME_OPTION_TWO = "gameOptionTwo";
    private static final String STATE_GAME_OPTION_THREE = "gameOptionThree";

    TrebelloGameModel(Bundle bundleArguments){
        playerOneScore = 0;
        playerTwoScore = 0;
        playerThreeScore = 0;
        playerOneTotalScore = 0;
        playerTwoTotalScore = 0;
        playerThreeTotalScore = 0;
        round = 1;
        playerOne = new Player(bundleArguments.getStringArrayList("playerNames").get(0));
        playerTwo = new Player(bundleArguments.getStringArrayList("playerNames").get(1));
        playerThree = new Player(bundleArguments.getStringArrayList("playerNames").get(2));
        playerOneScoreArray = new ArrayList<>(Collections.nCopies(0,12));
        playerTwoScoreArray = new ArrayList<>(Collections.nCopies(0,12));
        playerThreeScoreArray = new ArrayList<>(Collections.nCopies(0,12));
    }

    void setSavedInstanceState(Bundle savedInstanceState){
        round = savedInstanceState.getInt(STATE_ROUND);
        playerOneTotalScore = savedInstanceState.getInt(STATE_PERSON_ONE_SCORE);
        playerTwoTotalScore = savedInstanceState.getInt(STATE_PERSON_TWO_SCORE);
        playerThreeTotalScore = savedInstanceState.getInt(STATE_PERSON_THREE_SCORE);
        playerOneScoreArray = savedInstanceState.getIntegerArrayList(STATE_PERSON_ONE_SCORE_ARRAY);
        playerTwoScoreArray = savedInstanceState.getIntegerArrayList(STATE_PERSON_TWO_SCORE_ARRAY);
        playerThreeScoreArray = savedInstanceState.getIntegerArrayList(STATE_PERSON_THREE_SCORE_ARRAY);
        playerOneScore = savedInstanceState.getInt(STATE_PERSON_ONE_ROUND_SCORE);
        playerTwoScore = savedInstanceState.getInt(STATE_PERSON_TWO_ROUND_SCORE);
        playerThreeScore = savedInstanceState.getInt(STATE_PERSON_THREE_ROUND_SCORE);
        checkedGameOptions[0] = savedInstanceState.getBooleanArray(STATE_GAME_OPTION_ONE);
        checkedGameOptions[1] = savedInstanceState.getBooleanArray(STATE_GAME_OPTION_TWO);
        checkedGameOptions[2] = savedInstanceState.getBooleanArray(STATE_GAME_OPTION_THREE);
    }

    Bundle onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(STATE_ROUND, round);
        savedInstanceState.putInt(STATE_PERSON_ONE_SCORE, playerOneTotalScore);
        savedInstanceState.putInt(STATE_PERSON_TWO_SCORE, playerTwoTotalScore);
        savedInstanceState.putInt(STATE_PERSON_THREE_SCORE, playerThreeTotalScore);
        savedInstanceState.putIntegerArrayList(STATE_PERSON_ONE_SCORE_ARRAY, playerOneScoreArray);
        savedInstanceState.putIntegerArrayList(STATE_PERSON_TWO_SCORE_ARRAY, playerTwoScoreArray);
        savedInstanceState.putIntegerArrayList(STATE_PERSON_THREE_SCORE_ARRAY, playerThreeScoreArray);
        savedInstanceState.putInt(STATE_PERSON_ONE_ROUND_SCORE, playerOneScore);
        savedInstanceState.putInt(STATE_PERSON_TWO_ROUND_SCORE, playerTwoScore);
        savedInstanceState.putInt(STATE_PERSON_THREE_ROUND_SCORE, playerThreeScore);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_ONE, checkedGameOptions[0]);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_TWO, checkedGameOptions[1]);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_THREE, checkedGameOptions[2]);

        return savedInstanceState;
    }

    void save(Context context){
        savePlayers(context);
        JsonArray array1 = TrebelloGameConverterJson.getInstance().toJson(playerOneScoreArray, "Score");
        JsonArray array2 = TrebelloGameConverterJson.getInstance().toJson(playerTwoScoreArray, "Score");
        JsonArray array3 = TrebelloGameConverterJson.getInstance().toJson(playerThreeScoreArray, "Score");
        JsonArray array4 = TrebelloGameConverterJson.getInstance().toJson(checkedGameOptions[0], "GameOption");
        JsonArray array5 = TrebelloGameConverterJson.getInstance().toJson(checkedGameOptions[1], "GameOption");
        JsonArray array6 = TrebelloGameConverterJson.getInstance().toJson(checkedGameOptions[2], "GameOption");
        JsonObject object1 = TrebelloGameConverterJson.getInstance().toJson(round, "Round");
        JsonObject object2 = TrebelloGameConverterJson.getInstance().toJson(playerOneTotalScore, "PlayerOneScore");
        JsonObject object3 = TrebelloGameConverterJson.getInstance().toJson(playerTwoTotalScore, "PlayerTwoScore");
        JsonObject object4 = TrebelloGameConverterJson.getInstance().toJson(playerThreeTotalScore, "PlayerThreeScore");

        StorageUtil.save(context.getApplicationContext(), "PlayerOneScoreArray" ,array1);
        StorageUtil.save(context.getApplicationContext(), "PlayerTwoScoreArray" ,array2);
        StorageUtil.save(context.getApplicationContext(), "PlayerThreeScoreArray" ,array3);
        StorageUtil.save(context.getApplicationContext(), "GameOption0" ,array4);
        StorageUtil.save(context.getApplicationContext(), "GameOption1" ,array5);
        StorageUtil.save(context.getApplicationContext(), "GameOption2" ,array6);
        StorageUtil.save(context.getApplicationContext(), "Round", object1);
        StorageUtil.save(context.getApplicationContext(), "PlayerOneScore", object2);
        StorageUtil.save(context.getApplicationContext(), "PlayerTwoScore", object3);
        StorageUtil.save(context.getApplicationContext(), "PlayerThreeScore", object4);
    }

    private void savePlayers(Context context){
        playerOne.save(context, playerOne.getName());
        playerTwo.save(context, playerTwo.getName());
        playerThree.save(context, playerThree.getName());
    }

    void load(Context context){
        loadPlayers(context);

        JsonElement element1 = null;
        JsonElement element2 = null;
        JsonElement element3 = null;
        JsonElement element4 = null;
        JsonElement element5 = null;
        JsonElement element6 = null;
        JsonElement element7 = null;
        JsonElement element8 = null;
        JsonElement element9 = null;
        JsonElement element10 = null;
        try {
            element1 = StorageUtil.load(context.getApplicationContext(), "PlayerOneScoreArray");
            element2 = StorageUtil.load(context.getApplicationContext(), "PlayerTwoScoreArray");
            element3 = StorageUtil.load(context.getApplicationContext(), "PlayerThreeScoreArray");
            element4 = StorageUtil.load(context.getApplicationContext(), "Round");
            element5 = StorageUtil.load(context.getApplicationContext(), "PlayerOneScore");
            element6 = StorageUtil.load(context.getApplicationContext(), "PlayerTwoScore");
            element7 = StorageUtil.load(context.getApplicationContext(), "PlayerThreeScore");
            element8 = StorageUtil.load(context.getApplicationContext(), "GameOption0");
            element9 = StorageUtil.load(context.getApplicationContext(), "GameOption1");
            element10 = StorageUtil.load(context.getApplicationContext(), "GameOption2");
        } catch (IllegalStateException e) {
            StorageUtil.resetData(context.getApplicationContext(), "PlayerOneArray");
            StorageUtil.resetData(context.getApplicationContext(), "PlayerTwoArray");
            StorageUtil.resetData(context.getApplicationContext(), "PlayerThreeArray");
            StorageUtil.resetData(context.getApplicationContext(), "GameOption0");
            StorageUtil.resetData(context.getApplicationContext(), "GameOption1");
            StorageUtil.resetData(context.getApplicationContext(), "GameOption2");
            StorageUtil.resetData(context.getApplicationContext(), "Round");
            StorageUtil.resetData(context.getApplicationContext(), "PlayerOneScore");
            StorageUtil.resetData(context.getApplicationContext(), "PlayerTwoScore");
            StorageUtil.resetData(context.getApplicationContext(), "PlayerThreeScore");
        } catch (FileNotFoundException ignored) {
        }

        if ((element1 == null) || !element1.isJsonArray()
                || (element2 == null) || !element2.isJsonArray()
                || (element3 == null) || !element3.isJsonArray()
                || (element4 == null) || !element4.isJsonObject()
                || (element5 == null) || !element5.isJsonObject()
                || (element6 == null) || !element6.isJsonObject()
                || (element7 == null) || !element7.isJsonObject()
                || (element8 == null) || !element8.isJsonArray()
                || (element9 == null) || !element9.isJsonArray()
                || (element10 == null) || !element10.isJsonArray()) {
            return;
        }
        JsonArray array1 = element1.getAsJsonArray();
        JsonArray array2 = element2.getAsJsonArray();
        JsonArray array3 = element3.getAsJsonArray();
        JsonObject object1 = element4.getAsJsonObject();
        JsonObject object2 = element5.getAsJsonObject();
        JsonObject object3 = element6.getAsJsonObject();
        JsonObject object4 = element7.getAsJsonObject();
        JsonArray array4 = element8.getAsJsonArray();
        JsonArray array5 = element9.getAsJsonArray();
        JsonArray array6 = element10.getAsJsonArray();

        playerOneScoreArray = TrebelloGameConverterJson.getInstance().toObject(array1, "Score");
        playerTwoScoreArray = TrebelloGameConverterJson.getInstance().toObject(array2, "Score");
        playerThreeScoreArray = TrebelloGameConverterJson.getInstance().toObject(array3, "Score");
        round = TrebelloGameConverterJson.getInstance().toObject(object1, "Round");
        playerOneTotalScore = TrebelloGameConverterJson.getInstance().toObject(object2, "PlayerOneScore");
        playerTwoTotalScore = TrebelloGameConverterJson.getInstance().toObject(object3, "PlayerTwoScore");
        playerThreeTotalScore = TrebelloGameConverterJson.getInstance().toObject(object4, "PlayerThreeScore");
        checkedGameOptions[0] = TrebelloGameConverterJson.getInstance().toObjectBool(array4, "GameOption");
        checkedGameOptions[1] = TrebelloGameConverterJson.getInstance().toObjectBool(array5, "GameOption");
        checkedGameOptions[2] = TrebelloGameConverterJson.getInstance().toObjectBool(array6, "GameOption");

    }

    private void loadPlayers(Context context){
        playerOne.load(context, playerOne.getName());
        playerTwo.load(context, playerTwo.getName());
        playerThree.load(context, playerThree.getName());
    }

    void updateScore(){
        playerOneTotalScore += playerOneScore;
        playerTwoTotalScore += playerTwoScore;
        playerThreeTotalScore += playerThreeScore;
        playerOneScoreArray.add(playerOneTotalScore);
        playerTwoScoreArray.add(playerTwoTotalScore);
        playerThreeScoreArray.add(playerThreeTotalScore);
    }

    void nextRound(){
        round++;
        playerOneScore = 0;
        playerTwoScore = 0;
        playerThreeScore = 0;
    }

    void newGame(Context context){
        StorageUtil.resetData(context.getApplicationContext(), "PlayerOneArray");
        StorageUtil.resetData(context.getApplicationContext(), "PlayerTwoArray");
        StorageUtil.resetData(context.getApplicationContext(), "PlayerThreeArray");
        StorageUtil.resetData(context.getApplicationContext(), "GameOption0");
        StorageUtil.resetData(context.getApplicationContext(), "GameOption1");
        StorageUtil.resetData(context.getApplicationContext(), "GameOption2");
        StorageUtil.resetData(context.getApplicationContext(), "Round");
        StorageUtil.resetData(context.getApplicationContext(), "PlayerOneScore");
        StorageUtil.resetData(context.getApplicationContext(), "PlayerTwoScore");
        StorageUtil.resetData(context.getApplicationContext(), "PlayerThreeScore");
        playerOneScore = 0;
        playerTwoScore = 0;
        playerThreeScore = 0;
        playerOneTotalScore = 0;
        playerTwoTotalScore = 0;
        playerThreeTotalScore = 0;
        round = 1;
        playerOneScoreArray = new ArrayList<>(Collections.nCopies(0,12));
        playerTwoScoreArray = new ArrayList<>(Collections.nCopies(0,12));
        playerThreeScoreArray = new ArrayList<>(Collections.nCopies(0,12));
    }

    void addStatistics(Context context) {
        if(playerOneTotalScore > playerTwoTotalScore ){
            if (playerTwoTotalScore > playerThreeTotalScore) {
                playerOne.setTrebelloFirst(playerOne.getTrebelloFirst()+1);
                playerTwo.setTrebelloSecond(playerTwo.getTrebelloSecond()+1);
                playerThree.setTrebelloThird(playerThree.getTrebelloThird()+1);
            } else if (playerTwoTotalScore < playerThreeTotalScore) {
                if (playerOneTotalScore > playerThreeTotalScore) {
                    playerOne.setTrebelloFirst(playerOne.getTrebelloFirst()+1);
                    playerTwo.setTrebelloThird(playerTwo.getTrebelloThird()+1);
                    playerThree.setTrebelloSecond(playerThree.getTrebelloSecond()+1);
                } else if ( playerOneTotalScore < playerThreeTotalScore){
                    playerOne.setTrebelloSecond(playerOne.getTrebelloSecond()+1);
                    playerTwo.setTrebelloThird(playerTwo.getTrebelloThird()+1);
                    playerThree.setTrebelloFirst(playerThree.getTrebelloFirst()+1);
                } else {
                    playerOne.setTrebelloFirst(playerOne.getTrebelloFirst()+1);
                    playerTwo.setTrebelloThird(playerTwo.getTrebelloThird()+1);
                    playerThree.setTrebelloFirst(playerThree.getTrebelloFirst()+1);
                }
            } else {
                playerOne.setTrebelloFirst(playerOne.getTrebelloFirst()+1);
                playerTwo.setTrebelloSecond(playerTwo.getTrebelloSecond()+1);
                playerThree.setTrebelloSecond(playerThree.getTrebelloSecond()+1);
            }
        } else if (playerOneTotalScore < playerTwoTotalScore) {
            if (playerOneTotalScore > playerThreeTotalScore) {
                playerOne.setTrebelloSecond(playerOne.getTrebelloSecond()+1);
                playerTwo.setTrebelloFirst(playerTwo.getTrebelloFirst()+1);
                playerThree.setTrebelloThird(playerThree.getTrebelloThird()+1);
            } else if (playerOneTotalScore < playerThreeTotalScore) {
                if (playerTwoTotalScore > playerThreeTotalScore) {
                    playerOne.setTrebelloThird(playerOne.getTrebelloThird()+1);
                    playerTwo.setTrebelloFirst(playerTwo.getTrebelloFirst()+1);
                    playerThree.setTrebelloSecond(playerThree.getTrebelloSecond()+1);
                } else if (playerTwoTotalScore < playerThreeTotalScore) {
                    playerOne.setTrebelloThird(playerOne.getTrebelloThird()+1);
                    playerTwo.setTrebelloSecond(playerTwo.getTrebelloSecond()+1);
                    playerThree.setTrebelloFirst(playerThree.getTrebelloFirst()+1);
                } else {
                    playerOne.setTrebelloThird(playerOne.getTrebelloThird()+1);
                    playerTwo.setTrebelloFirst(playerTwo.getTrebelloFirst()+1);
                    playerThree.setTrebelloFirst(playerThree.getTrebelloFirst()+1);
                }
            } else {
                playerOne.setTrebelloSecond(playerOne.getTrebelloSecond()+1);
                playerTwo.setTrebelloFirst(playerTwo.getTrebelloFirst()+1);
                playerThree.setTrebelloSecond(playerThree.getTrebelloSecond()+1);
            }
        } else {
            if (playerOneTotalScore > playerThreeTotalScore) {
                playerOne.setTrebelloFirst(playerOne.getTrebelloFirst()+1);
                playerTwo.setTrebelloFirst(playerTwo.getTrebelloFirst()+1);
                playerThree.setTrebelloThird(playerThree.getTrebelloThird()+1);
            } else if (playerOneTotalScore < playerThreeTotalScore) {
                playerOne.setTrebelloSecond(playerOne.getTrebelloSecond()+1);
                playerTwo.setTrebelloSecond(playerTwo.getTrebelloSecond()+1);
                playerThree.setTrebelloFirst(playerThree.getTrebelloFirst()+1);
            } else {
                playerOne.setTrebelloFirst(playerOne.getTrebelloFirst()+1);
                playerTwo.setTrebelloFirst(playerTwo.getTrebelloFirst()+1);
                playerThree.setTrebelloFirst(playerThree.getTrebelloFirst()+1);
            }
        }

        if (playerOneTotalScore > playerOne.getTrebelloHighScore()){
            playerOne.setTrebelloHighScore(playerOneTotalScore);
        }
        if (playerOneTotalScore < playerOne.getTrebelloJumboScore()){
            playerOne.setTrebelloJumboScore(playerOneTotalScore);
        }
        if (playerTwoTotalScore > playerTwo.getTrebelloHighScore()){
            playerTwo.setTrebelloHighScore(playerTwoTotalScore);
        }
        if (playerTwoTotalScore < playerTwo.getTrebelloJumboScore()){
            playerTwo.setTrebelloJumboScore(playerTwoTotalScore);
        }
        if (playerThreeTotalScore > playerThree.getTrebelloHighScore()){
            playerThree.setTrebelloHighScore(playerThreeTotalScore);
        }
        if (playerThreeTotalScore < playerThree.getTrebelloJumboScore()){
            playerThree.setTrebelloJumboScore(playerThreeTotalScore);
        }
        savePlayers(context);
    }

    int getPlayerOneScore() {
        return playerOneScore;
    }

    void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    int getPlayerTwoScore() {
        return playerTwoScore;
    }

    void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    int getPlayerThreeScore() {
        return playerThreeScore;
    }

    void setPlayerThreeScore(int playerThreeScore) {
        this.playerThreeScore = playerThreeScore;
    }

    int getPlayerOneTotalScore() {
        return playerOneTotalScore;
    }

    int getPlayerTwoTotalScore() {
        return playerTwoTotalScore;
    }

    int getPlayerThreeTotalScore() {
        return playerThreeTotalScore;
    }

    ArrayList<Integer> getPlayerOneScoreArray() {
        return playerOneScoreArray;
    }

    ArrayList<Integer> getPlayerTwoScoreArray() {
        return playerTwoScoreArray;
    }

    ArrayList<Integer> getPlayerThreeScoreArray() {
        return playerThreeScoreArray;
    }

    int getRound() {
        return round;
    }

    boolean[][] getCheckedGameOptions() {
        return checkedGameOptions;
    }

    void setCheckedGameOption(int i, int j, boolean bool){
        this.checkedGameOptions[i][j] = bool;
    }

    Player getPlayerOne() {
        return playerOne;
    }

    Player getPlayerTwo() {
        return playerTwo;
    }

    Player getPlayerThree() {
        return playerThree;
    }
}
