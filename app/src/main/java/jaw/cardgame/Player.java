package jaw.cardgame;

public class Player {



    private String name;
    private int trebelloFirst;
    private int trebelloSecond;
    private int trebelloThird;
    private int trebelloHighscore;
    private int trebelloJumboScore;

    Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
