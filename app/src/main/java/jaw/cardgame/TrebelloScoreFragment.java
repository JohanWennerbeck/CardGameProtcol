package jaw.cardgame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TrebelloScoreFragment extends Fragment implements View.OnClickListener {

    TrebelloGameModel model;
    private Context mContext;

    TextView scorePlayerOneTextView, scorePlayerTwoTextView, scorePlayerThreeTextView;
    TextView finalScorePlayerOneTextView, finalScorePlayerTwoTextView, finalScorePlayerThreeTextView;
    TextView[][] scoreTextViews = new TextView[3][12];
    ImageButton decrementPlayerOneButton, incrementPlayerOneButton,
            decrementPlayerTwoButton, incrementPlayerTwoButton,
            decrementPlayerThreeButton, incrementPlayerThreeButton;
    Button registerScore, newGame;
    ImageButton[][] gameOptions = new ImageButton[3][4];
    TextView playerOneName, playerTwoName, playerThreeName;
    TextView playerOneScoreName, playerTwoScoreName, playerThreeScoreName;


    public TrebelloScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_score, container, false);
        Bundle bundleArguments = getArguments();
        initView(v, savedInstanceState, Objects.requireNonNull(bundleArguments));
        return v;
    }

    @Override
    public void onResume() {
        model.load(mContext);
        setSavedUI();
        super.onResume();
    }

    @Override
    public void onPause() {
        model.save(mContext);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        // Save the user's current game state
        Bundle savedInstanceStateNew = model.onSaveInstanceState(savedInstanceState);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceStateNew);
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initView(View v, Bundle savedInstanceState, Bundle bundleArguments) {
        model = new TrebelloGameModel(bundleArguments);

        scorePlayerOneTextView = v.findViewById(R.id.scoreCount1);
        scorePlayerTwoTextView = v.findViewById(R.id.scoreCount2);
        scorePlayerThreeTextView = v.findViewById(R.id.scoreCount3);

        initPlayerNames(v);
        initCounterButtons(v);

        registerScore = v.findViewById(R.id.register);
        registerScore.setOnClickListener(this);

        newGame = v.findViewById(R.id.new_game);
        newGame.setOnClickListener(this);

        initScoreViews(v);
        initGameOptions(v);
        initGameOptionsBooleans();
        if(bundleArguments.getBoolean("newGame")){
            newGame();
            model.newGame(mContext);
        } else {
            if (savedInstanceState != null) {
                model.setSavedInstanceState(savedInstanceState);
                setSavedUI();
            }
        }



    }

    private void initPlayerNames(View v) {
        playerOneName = v.findViewById(R.id.playerName1);
        playerTwoName = v.findViewById(R.id.playerName2);
        playerThreeName = v.findViewById(R.id.playerName3);
        playerOneScoreName = v.findViewById(R.id.playerScoreName1);
        playerTwoScoreName = v.findViewById(R.id.playerScoreName2);
        playerThreeScoreName = v.findViewById(R.id.playerScoreName3);

        playerOneName.setText(model.getPlayerOne().getName());
        playerTwoName.setText(model.getPlayerTwo().getName());
        playerThreeName.setText(model.getPlayerThree().getName());
        playerOneScoreName.setText(model.getPlayerOne().getName());
        playerTwoScoreName.setText(model.getPlayerTwo().getName());
        playerThreeScoreName.setText(model.getPlayerThree().getName());
    }

    private void setSavedUI() {
        for (int j = 0; j < model.getRound() - 1; j++) {
            scoreTextViews[0][j].setText(String.valueOf(model.getPlayerOneScoreArray().get(j)));
            scoreTextViews[1][j].setText(String.valueOf(model.getPlayerTwoScoreArray().get(j)));
            scoreTextViews[2][j].setText(String.valueOf(model.getPlayerThreeScoreArray().get(j)));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (model.getCheckedGameOptions()[i][j]) {
                    gameOptions[i][j].setImageResource(R.drawable.ic_check_black_24dp);
                }
            }
        }
        if(model.getRound() == 13){
            endGame();
        } else {
            scorePlayerOneTextView.setText(String.valueOf(model.getPlayerOneScore()));
            scorePlayerTwoTextView.setText(String.valueOf(model.getPlayerTwoScore()));
            scorePlayerThreeTextView.setText(String.valueOf(model.getPlayerThreeScore()));
        }
    }

    private void initGameOptionsBooleans() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                model.getCheckedGameOptions()[i][j] = false;
            }
        }
    }

    private void initGameOptions(View v) {
        gameOptions[0][0] = v.findViewById(R.id.ImageButton1);
        gameOptions[1][0] = v.findViewById(R.id.ImageButton2);
        gameOptions[2][0] = v.findViewById(R.id.ImageButton3);
        gameOptions[0][1] = v.findViewById(R.id.ImageButton4);
        gameOptions[1][1] = v.findViewById(R.id.ImageButton5);
        gameOptions[2][1] = v.findViewById(R.id.ImageButton6);
        gameOptions[0][2] = v.findViewById(R.id.ImageButton7);
        gameOptions[1][2] = v.findViewById(R.id.ImageButton8);
        gameOptions[2][2] = v.findViewById(R.id.ImageButton9);
        gameOptions[0][3] = v.findViewById(R.id.ImageButton10);
        gameOptions[1][3] = v.findViewById(R.id.ImageButton11);
        gameOptions[2][3] = v.findViewById(R.id.ImageButton12);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gameOptions[i][j].setOnClickListener(this);
            }
        }
    }

    private void initScoreViews(View v) {
        scoreTextViews[0][0] = v.findViewById(R.id.score1);
        scoreTextViews[1][0] = v.findViewById(R.id.score2);
        scoreTextViews[2][0] = v.findViewById(R.id.score3);
        scoreTextViews[0][1] = v.findViewById(R.id.score4);
        scoreTextViews[1][1] = v.findViewById(R.id.score5);
        scoreTextViews[2][1] = v.findViewById(R.id.score6);
        scoreTextViews[0][2] = v.findViewById(R.id.score7);
        scoreTextViews[1][2] = v.findViewById(R.id.score8);
        scoreTextViews[2][2] = v.findViewById(R.id.score9);
        scoreTextViews[0][3] = v.findViewById(R.id.score10);
        scoreTextViews[1][3] = v.findViewById(R.id.score11);
        scoreTextViews[2][3] = v.findViewById(R.id.score12);
        scoreTextViews[0][4] = v.findViewById(R.id.score13);
        scoreTextViews[1][4] = v.findViewById(R.id.score14);
        scoreTextViews[2][4] = v.findViewById(R.id.score15);
        scoreTextViews[0][5] = v.findViewById(R.id.score16);
        scoreTextViews[1][5] = v.findViewById(R.id.score17);
        scoreTextViews[2][5] = v.findViewById(R.id.score18);
        scoreTextViews[0][6] = v.findViewById(R.id.score19);
        scoreTextViews[1][6] = v.findViewById(R.id.score20);
        scoreTextViews[2][6] = v.findViewById(R.id.score21);
        scoreTextViews[0][7] = v.findViewById(R.id.score22);
        scoreTextViews[1][7] = v.findViewById(R.id.score23);
        scoreTextViews[2][7] = v.findViewById(R.id.score24);
        scoreTextViews[0][8] = v.findViewById(R.id.score25);
        scoreTextViews[1][8] = v.findViewById(R.id.score26);
        scoreTextViews[2][8] = v.findViewById(R.id.score27);
        scoreTextViews[0][9] = v.findViewById(R.id.score28);
        scoreTextViews[1][9] = v.findViewById(R.id.score29);
        scoreTextViews[2][9] = v.findViewById(R.id.score30);
        scoreTextViews[0][10] = v.findViewById(R.id.score31);
        scoreTextViews[1][10] = v.findViewById(R.id.score32);
        scoreTextViews[2][10] = v.findViewById(R.id.score33);
        scoreTextViews[0][11] = v.findViewById(R.id.score34);
        scoreTextViews[1][11] = v.findViewById(R.id.score35);
        scoreTextViews[2][11] = v.findViewById(R.id.score36);
        finalScorePlayerOneTextView = v.findViewById(R.id.finalScore1);
        finalScorePlayerTwoTextView = v.findViewById(R.id.finalScore2);
        finalScorePlayerThreeTextView = v.findViewById(R.id.finalScore3);
    }

    private void initCounterButtons(View v) {

        //Init plus and minus ImageButtons
        decrementPlayerOneButton = v.findViewById(R.id.count1);
        decrementPlayerOneButton.setOnClickListener(this);
        incrementPlayerOneButton = v.findViewById(R.id.count2);
        incrementPlayerOneButton.setOnClickListener(this);
        decrementPlayerTwoButton = v.findViewById(R.id.count3);
        decrementPlayerTwoButton.setOnClickListener(this);
        incrementPlayerTwoButton = v.findViewById(R.id.count4);
        incrementPlayerTwoButton.setOnClickListener(this);
        decrementPlayerThreeButton = v.findViewById(R.id.count5);
        decrementPlayerThreeButton.setOnClickListener(this);
        incrementPlayerThreeButton = v.findViewById(R.id.count6);
        incrementPlayerThreeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.count1: {
                model.setPlayerOneScore(model.getPlayerOneScore()-1);
                scorePlayerOneTextView.setText(String.valueOf(model.getPlayerOneScore()));
                break;
            }
            case R.id.count2: {
                model.setPlayerOneScore(model.getPlayerOneScore()+1);
                scorePlayerOneTextView.setText(String.valueOf(model.getPlayerOneScore()));
                break;
            }
            case R.id.count3: {
                model.setPlayerTwoScore(model.getPlayerTwoScore()-1);
                scorePlayerTwoTextView.setText(String.valueOf(model.getPlayerTwoScore()));
                break;
            }
            case R.id.count4: {
                model.setPlayerTwoScore(model.getPlayerTwoScore()+1);
                scorePlayerTwoTextView.setText(String.valueOf(model.getPlayerTwoScore()));
                break;
            }
            case R.id.count5: {
                model.setPlayerThreeScore(model.getPlayerThreeScore()-1);
                scorePlayerThreeTextView.setText(String.valueOf(model.getPlayerThreeScore()));
                break;
            }
            case R.id.count6: {
                model.setPlayerThreeScore(model.getPlayerThreeScore()+1);
                scorePlayerThreeTextView.setText(String.valueOf(model.getPlayerThreeScore()));
                break;
            }
            case R.id.register: {
                updateScore();
                break;
            }
            case R.id.new_game: {
                model.newGame(mContext);
                newGame();
                break;
            }
            case R.id.ImageButton1: {
                toggleGameOption(0, 0);
                break;
            }
            case R.id.ImageButton2: {
                toggleGameOption(1, 0);
                break;
            }
            case R.id.ImageButton3: {
                toggleGameOption(2, 0);
                break;
            }
            case R.id.ImageButton4: {
                toggleGameOption(0, 1);
                break;
            }
            case R.id.ImageButton5: {
                toggleGameOption(1, 1);
                break;
            }
            case R.id.ImageButton6: {
                toggleGameOption(2, 1);
                break;
            }
            case R.id.ImageButton7: {
                toggleGameOption(0, 2);
                break;
            }
            case R.id.ImageButton8: {
                toggleGameOption(1, 2);
                break;
            }
            case R.id.ImageButton9: {
                toggleGameOption(2, 2);
                break;
            }
            case R.id.ImageButton10: {
                toggleGameOption(0, 3);
                break;
            }
            case R.id.ImageButton11: {
                toggleGameOption(1, 3);
                break;
            }
            case R.id.ImageButton12: {
                toggleGameOption(2, 3);
                break;
            }

        }
    }

    private void toggleGameOption(int i, int j) {
        if (model.getCheckedGameOptions()[i][j]) {
            gameOptions[i][j].setImageResource(android.R.color.transparent);
            model.setCheckedGameOption(i, j, false);
        } else {
            gameOptions[i][j].setImageResource(R.drawable.ic_check_black_24dp);
            model.setCheckedGameOption(i, j, true);
        }
    }

    private void updateScore() {
        if (model.getPlayerOneScore() + model.getPlayerTwoScore() + model.getPlayerThreeScore() == 0) {
            model.updateScore();

            scoreTextViews[0][model.getRound() - 1].setText(String.valueOf(model.getPlayerOneTotalScore()));
            scoreTextViews[1][model.getRound() - 1].setText(String.valueOf(model.getPlayerTwoTotalScore()));
            scoreTextViews[2][model.getRound() - 1].setText(String.valueOf(model.getPlayerThreeTotalScore()));

            model.nextRound();

            scorePlayerOneTextView.setText("0");
            scorePlayerTwoTextView.setText("0");
            scorePlayerThreeTextView.setText("0");
            if (model.getRound() == 13) {
                endGame();
                model.addStatistics(mContext);
            }
        } else {
            CharSequence text = "Wrong score input!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(mContext, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    void endGame(){
        registerScore.setEnabled(false);
        registerScore.setVisibility(View.INVISIBLE);
        newGame.setVisibility(View.VISIBLE);
        scorePlayerOneTextView.setTextColor(scorePlayerOneTextView.getTextColors().withAlpha(0));
        scorePlayerTwoTextView.setTextColor(scorePlayerTwoTextView.getTextColors().withAlpha(0));
        scorePlayerThreeTextView.setTextColor(scorePlayerThreeTextView.getTextColors().withAlpha(0));
        decrementPlayerOneButton.setEnabled(false);
        incrementPlayerOneButton.setEnabled(false);
        decrementPlayerTwoButton.setEnabled(false);
        incrementPlayerTwoButton.setEnabled(false);
        decrementPlayerThreeButton.setEnabled(false);
        incrementPlayerThreeButton.setEnabled(false);
        finalScorePlayerOneTextView.setText(String.valueOf(model.getPlayerOneTotalScore()));
        finalScorePlayerTwoTextView.setText(String.valueOf(model.getPlayerTwoTotalScore()));
        finalScorePlayerThreeTextView.setText(String.valueOf(model.getPlayerThreeTotalScore()));

    }

    void newGame(){
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                scoreTextViews[j][i].setText("");
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gameOptions[i][j].setImageResource(android.R.color.transparent);
                model.setCheckedGameOption(i, j, false);
            }

        }
        scorePlayerOneTextView.setText("0");
        scorePlayerTwoTextView.setText("0");
        scorePlayerThreeTextView.setText("0");
        finalScorePlayerOneTextView.setText("");
        finalScorePlayerTwoTextView.setText("");
        finalScorePlayerThreeTextView.setText("");
        newGame.setVisibility(View.GONE);
        registerScore.setVisibility(View.VISIBLE);

        scorePlayerOneTextView.setTextColor(scorePlayerOneTextView.getTextColors().withAlpha(255));
        scorePlayerTwoTextView.setTextColor(scorePlayerTwoTextView.getTextColors().withAlpha(255));
        scorePlayerThreeTextView.setTextColor(scorePlayerThreeTextView.getTextColors().withAlpha(255));
        decrementPlayerOneButton.setEnabled(true);
        incrementPlayerOneButton.setEnabled(true);
        decrementPlayerTwoButton.setEnabled(true);
        incrementPlayerTwoButton.setEnabled(true);
        decrementPlayerThreeButton.setEnabled(true);
        incrementPlayerThreeButton.setEnabled(true);
        registerScore.setEnabled(true);


    }

}
