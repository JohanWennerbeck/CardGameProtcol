package jaw.cardgame;

import android.os.Bundle;

public class TrebelloGameModel {

    private int playerOneScore, playerTwoScore, playerThreeScore;
    private int playerOneTotalScore, playerTwoTotalScore, playerThreeTotalScore;
    private int[] playerOneScoreArray = new int[12];
    private int[] playerTwoScoreArray = new int[12];
    private int[] playerThreeScoreArray = new int[12];
    private int round;
    private boolean[][] checkedGameOptions = new boolean[3][4];

    private Player playerOne, playerTwo, playerThree;

    static final String STATE_ROUND = "round";
    static final String STATE_PERSON_ONE_SCORE = "playerOne";
    static final String STATE_PERSON_TWO_SCORE = "playerTwo";
    static final String STATE_PERSON_THREE_SCORE = "playerThree";
    static final String STATE_PERSON_ONE_SCORE_ARRAY = "playerOneScoreArray";
    static final String STATE_PERSON_TWO_SCORE_ARRAY = "playerTwoScoreArray";
    static final String STATE_PERSON_THREE_SCORE_ARRAY = "playerThreeScoreArray";
    static final String STATE_PERSON_ONE_ROUND_SCORE = "playerOneRoundScore";
    static final String STATE_PERSON_TWO_ROUND_SCORE = "playerTwoRoundScore";
    static final String STATE_PERSON_THREE_ROUND_SCORE = "playerThreeRoundScore";
    static final String STATE_GAME_OPTION_ONE = "gameOptionOne";
    static final String STATE_GAME_OPTION_TWO = "gameOptionTwo";
    static final String STATE_GAME_OPTION_THREE = "gameOptionThree";

    TrebelloGameModel(){
        playerOneScore = 0;
        playerTwoScore = 0;
        playerThreeScore = 0;
        playerOneTotalScore = 0;
        playerTwoTotalScore = 0;
        playerThreeTotalScore = 0;
        round = 1;
        playerOne = new Player("Fredrik");
        playerTwo = new Player("Anders");
        playerThree = new Player("Johan");
    }

    void setSavedInstanceState(Bundle savedInstanceState){
        round = savedInstanceState.getInt(STATE_ROUND);
        playerOneTotalScore = savedInstanceState.getInt(STATE_PERSON_ONE_SCORE);
        playerTwoTotalScore = savedInstanceState.getInt(STATE_PERSON_TWO_SCORE);
        playerThreeTotalScore = savedInstanceState.getInt(STATE_PERSON_THREE_SCORE);
        playerOneScoreArray = savedInstanceState.getIntArray(STATE_PERSON_ONE_SCORE_ARRAY);
        playerTwoScoreArray = savedInstanceState.getIntArray(STATE_PERSON_TWO_SCORE_ARRAY);
        playerThreeScoreArray = savedInstanceState.getIntArray(STATE_PERSON_THREE_SCORE_ARRAY);
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
        savedInstanceState.putIntArray(STATE_PERSON_ONE_SCORE_ARRAY, playerOneScoreArray);
        savedInstanceState.putIntArray(STATE_PERSON_TWO_SCORE_ARRAY, playerTwoScoreArray);
        savedInstanceState.putIntArray(STATE_PERSON_THREE_SCORE_ARRAY, playerThreeScoreArray);
        savedInstanceState.putInt(STATE_PERSON_ONE_ROUND_SCORE, playerOneScore);
        savedInstanceState.putInt(STATE_PERSON_TWO_ROUND_SCORE, playerTwoScore);
        savedInstanceState.putInt(STATE_PERSON_THREE_ROUND_SCORE, playerThreeScore);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_ONE, checkedGameOptions[0]);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_TWO, checkedGameOptions[1]);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_THREE, checkedGameOptions[2]);

        return savedInstanceState;
    }

    void updateScore(){
        playerOneTotalScore += playerOneScore;
        playerTwoTotalScore += playerTwoScore;
        playerThreeTotalScore += playerThreeScore;
         playerOneScoreArray[round - 1] = playerOneTotalScore;
        playerTwoScoreArray[round - 1] = playerTwoTotalScore;
        playerThreeScoreArray[round - 1] = playerThreeTotalScore;
    }

    void nextRound(){
        round++;
        playerOneScore = 0;
        playerTwoScore = 0;
        playerThreeScore = 0;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public int getPlayerThreeScore() {
        return playerThreeScore;
    }

    public void setPlayerThreeScore(int playerThreeScore) {
        this.playerThreeScore = playerThreeScore;
    }

    public int getPlayerOneTotalScore() {
        return playerOneTotalScore;
    }

    public void setPlayerOneTotalScore(int playerOneTotalScore) {
        this.playerOneTotalScore = playerOneTotalScore;
    }

    public int getPlayerTwoTotalScore() {
        return playerTwoTotalScore;
    }

    public void setPlayerTwoTotalScore(int playerTwoTotalScore) {
        this.playerTwoTotalScore = playerTwoTotalScore;
    }

    public int getPlayerThreeTotalScore() {
        return playerThreeTotalScore;
    }

    public void setPlayerThreeTotalScore(int playerThreeTotalScore) {
        this.playerThreeTotalScore = playerThreeTotalScore;
    }

    public int[] getPlayerOneScoreArray() {
        return playerOneScoreArray;
    }

    public void setPlayerOneScoreArray(int[] playerOneScoreArray) {
        this.playerOneScoreArray = playerOneScoreArray;
    }

    public int[] getPlayerTwoScoreArray() {
        return playerTwoScoreArray;
    }

    public void setPlayerTwoScoreArray(int[] playerTwoScoreArray) {
        this.playerTwoScoreArray = playerTwoScoreArray;
    }

    public int[] getPlayerThreeScoreArray() {
        return playerThreeScoreArray;
    }

    public void setPlayerThreeScoreArray(int[] playerThreeScoreArray) {
        this.playerThreeScoreArray = playerThreeScoreArray;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean[][] getCheckedGameOptions() {
        return checkedGameOptions;
    }

    public void setCheckedGameOptions(boolean[][] checkedGameOptions) {
        this.checkedGameOptions = checkedGameOptions;
    }

    public void setCheckedGameOption(int i, int j, boolean bool){
        this.checkedGameOptions[i][j] = bool;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getPlayerThree() {
        return playerThree;
    }
}
