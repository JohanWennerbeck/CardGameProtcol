package jaw.cardgame;

import android.os.Bundle;

public class TrebelloGameModel {

    private int personOneScore, personTwoScore, personThreeScore;
    private int personOneTotalScore, personTwoTotalScore, personThreeTotalScore;
    private int[] personOneScoreArray = new int[12];
    private int[] personTwoScoreArray = new int[12];
    private int[] personThreeScoreArray = new int[12];
    private int round;
    private boolean[][] checkedGameOptions = new boolean[3][4];

    static final String STATE_ROUND = "round";
    static final String STATE_PERSON_ONE_SCORE = "personOne";
    static final String STATE_PERSON_TWO_SCORE = "personTwo";
    static final String STATE_PERSON_THREE_SCORE = "personThree";
    static final String STATE_PERSON_ONE_SCORE_ARRAY = "personOneScoreArray";
    static final String STATE_PERSON_TWO_SCORE_ARRAY = "personTwoScoreArray";
    static final String STATE_PERSON_THREE_SCORE_ARRAY = "personThreeScoreArray";
    static final String STATE_PERSON_ONE_ROUND_SCORE = "personOneRoundScore";
    static final String STATE_PERSON_TWO_ROUND_SCORE = "personTwoRoundScore";
    static final String STATE_PERSON_THREE_ROUND_SCORE = "personThreeRoundScore";
    static final String STATE_GAME_OPTION_ONE = "gameOptionOne";
    static final String STATE_GAME_OPTION_TWO = "gameOptionTwo";
    static final String STATE_GAME_OPTION_THREE = "gameOptionThree";

    TrebelloGameModel(){
        personOneScore = 0;
        personTwoScore = 0;
        personThreeScore = 0;
        personOneTotalScore = 0;
        personTwoTotalScore = 0;
        personThreeTotalScore = 0;
        round = 1;
    }

    void setSavedInstanceState(Bundle savedInstanceState){
        round = savedInstanceState.getInt(STATE_ROUND);
        personOneTotalScore = savedInstanceState.getInt(STATE_PERSON_ONE_SCORE);
        personTwoTotalScore = savedInstanceState.getInt(STATE_PERSON_TWO_SCORE);
        personThreeTotalScore = savedInstanceState.getInt(STATE_PERSON_THREE_SCORE);
        personOneScoreArray = savedInstanceState.getIntArray(STATE_PERSON_ONE_SCORE_ARRAY);
        personTwoScoreArray = savedInstanceState.getIntArray(STATE_PERSON_TWO_SCORE_ARRAY);
        personThreeScoreArray = savedInstanceState.getIntArray(STATE_PERSON_THREE_SCORE_ARRAY);
        personOneScore = savedInstanceState.getInt(STATE_PERSON_ONE_ROUND_SCORE);
        personTwoScore = savedInstanceState.getInt(STATE_PERSON_TWO_ROUND_SCORE);
        personThreeScore = savedInstanceState.getInt(STATE_PERSON_THREE_ROUND_SCORE);
        checkedGameOptions[0] = savedInstanceState.getBooleanArray(STATE_GAME_OPTION_ONE);
        checkedGameOptions[1] = savedInstanceState.getBooleanArray(STATE_GAME_OPTION_TWO);
        checkedGameOptions[2] = savedInstanceState.getBooleanArray(STATE_GAME_OPTION_THREE);
    }

    Bundle onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(STATE_ROUND, round);
        savedInstanceState.putInt(STATE_PERSON_ONE_SCORE, personOneTotalScore);
        savedInstanceState.putInt(STATE_PERSON_TWO_SCORE, personTwoTotalScore);
        savedInstanceState.putInt(STATE_PERSON_THREE_SCORE, personThreeTotalScore);
        savedInstanceState.putIntArray(STATE_PERSON_ONE_SCORE_ARRAY, personOneScoreArray);
        savedInstanceState.putIntArray(STATE_PERSON_TWO_SCORE_ARRAY, personTwoScoreArray);
        savedInstanceState.putIntArray(STATE_PERSON_THREE_SCORE_ARRAY, personThreeScoreArray);
        savedInstanceState.putInt(STATE_PERSON_ONE_ROUND_SCORE, personOneScore);
        savedInstanceState.putInt(STATE_PERSON_TWO_ROUND_SCORE, personTwoScore);
        savedInstanceState.putInt(STATE_PERSON_THREE_ROUND_SCORE, personThreeScore);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_ONE, checkedGameOptions[0]);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_TWO, checkedGameOptions[1]);
        savedInstanceState.putBooleanArray(STATE_GAME_OPTION_THREE, checkedGameOptions[2]);

        return savedInstanceState;
    }

    void updateScore(){
        personOneTotalScore += personOneScore;
        personTwoTotalScore += personTwoScore;
        personThreeTotalScore += personThreeScore;
         personOneScoreArray[round - 1] = personOneTotalScore;
        personTwoScoreArray[round - 1] = personTwoTotalScore;
        personThreeScoreArray[round - 1] = personThreeTotalScore;
    }

    void nextRound(){
        round++;
        personOneScore = 0;
        personTwoScore = 0;
        personThreeScore = 0;
    }

    public int getPersonOneScore() {
        return personOneScore;
    }

    public void setPersonOneScore(int personOneScore) {
        this.personOneScore = personOneScore;
    }

    public int getPersonTwoScore() {
        return personTwoScore;
    }

    public void setPersonTwoScore(int personTwoScore) {
        this.personTwoScore = personTwoScore;
    }

    public int getPersonThreeScore() {
        return personThreeScore;
    }

    public void setPersonThreeScore(int personThreeScore) {
        this.personThreeScore = personThreeScore;
    }

    public int getPersonOneTotalScore() {
        return personOneTotalScore;
    }

    public void setPersonOneTotalScore(int personOneTotalScore) {
        this.personOneTotalScore = personOneTotalScore;
    }

    public int getPersonTwoTotalScore() {
        return personTwoTotalScore;
    }

    public void setPersonTwoTotalScore(int personTwoTotalScore) {
        this.personTwoTotalScore = personTwoTotalScore;
    }

    public int getPersonThreeTotalScore() {
        return personThreeTotalScore;
    }

    public void setPersonThreeTotalScore(int personThreeTotalScore) {
        this.personThreeTotalScore = personThreeTotalScore;
    }

    public int[] getPersonOneScoreArray() {
        return personOneScoreArray;
    }

    public void setPersonOneScoreArray(int[] personOneScoreArray) {
        this.personOneScoreArray = personOneScoreArray;
    }

    public int[] getPersonTwoScoreArray() {
        return personTwoScoreArray;
    }

    public void setPersonTwoScoreArray(int[] personTwoScoreArray) {
        this.personTwoScoreArray = personTwoScoreArray;
    }

    public int[] getPersonThreeScoreArray() {
        return personThreeScoreArray;
    }

    public void setPersonThreeScoreArray(int[] personThreeScoreArray) {
        this.personThreeScoreArray = personThreeScoreArray;
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

}
