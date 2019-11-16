package jaw.cardgame;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreFragment extends Fragment implements View.OnClickListener {

    TrebelloGameModel model;

    TextView scorePersonOneTextView, scorePersonTwoTextView, scorePersonThreeTextView;
    TextView finalScorePersonOneTextView, finalScorePersonTwoTextView, finalScorePersonThreeTextView;
    TextView scoreTextViews[][] = new TextView[3][12];
    ImageButton decrementPersonOneButton, incrementPersonOneButton,
            decrementPersonTwoButton, incrementPersonTwoButton,
            decrementPersonThreeButton, incrementPersonThreeButton;
    Button registerScore;
    ImageButton gameOptions[][] = new ImageButton[3][4];

    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreFragment newInstance() {
        ScoreFragment fragment = new ScoreFragment();
        return fragment;
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
        initView(v, savedInstanceState);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        Bundle savedInstanceStateNew = model.onSaveInstanceState(savedInstanceState);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceStateNew);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initView(View v, Bundle savedInstanceState) {
        model = new TrebelloGameModel();

        scorePersonOneTextView = v.findViewById(R.id.scoreCount1);
        scorePersonTwoTextView = v.findViewById(R.id.scoreCount2);
        scorePersonThreeTextView = v.findViewById(R.id.scoreCount3);

        initCounterButtons(v);

        //Init register score
        registerScore = v.findViewById(R.id.register);
        registerScore.setOnClickListener(this);

        initScoreViews(v);
        initGameOptions(v);
        initGameOptionsBooleans();
        if (savedInstanceState != null) {
            model.setSavedInstanceState(savedInstanceState);
            setSavedUI();
        }

    }

    private void setSavedUI() {
        for (int j = 0; j < model.getRound() - 1; j++) {
            scoreTextViews[0][j].setText(String.valueOf(model.getPersonOneScoreArray()[j]));
            scoreTextViews[1][j].setText(String.valueOf(model.getPersonTwoScoreArray()[j]));
            scoreTextViews[2][j].setText(String.valueOf(model.getPersonThreeScoreArray()[j]));
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
            scorePersonOneTextView.setText(String.valueOf(model.getPersonOneScore()));
            scorePersonTwoTextView.setText(String.valueOf(model.getPersonTwoScore()));
            scorePersonThreeTextView.setText(String.valueOf(model.getPersonThreeScore()));
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
        finalScorePersonOneTextView = v.findViewById(R.id.finalScore1);
        finalScorePersonTwoTextView = v.findViewById(R.id.finalScore2);
        finalScorePersonThreeTextView = v.findViewById(R.id.finalScore3);
    }

    private void initCounterButtons(View v) {

        //Init plus and minus ImageButtons
        decrementPersonOneButton = v.findViewById(R.id.count1);
        decrementPersonOneButton.setOnClickListener(this);
        incrementPersonOneButton = v.findViewById(R.id.count2);
        incrementPersonOneButton.setOnClickListener(this);
        decrementPersonTwoButton = v.findViewById(R.id.count3);
        decrementPersonTwoButton.setOnClickListener(this);
        incrementPersonTwoButton = v.findViewById(R.id.count4);
        incrementPersonTwoButton.setOnClickListener(this);
        decrementPersonThreeButton = v.findViewById(R.id.count5);
        decrementPersonThreeButton.setOnClickListener(this);
        incrementPersonThreeButton = v.findViewById(R.id.count6);
        incrementPersonThreeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.count1: {
                model.setPersonOneScore(model.getPersonOneScore()-1);
                scorePersonOneTextView.setText(String.valueOf(model.getPersonOneScore()));
                break;
            }
            case R.id.count2: {
                model.setPersonOneScore(model.getPersonOneScore()+1);
                scorePersonOneTextView.setText(String.valueOf(model.getPersonOneScore()));
                break;
            }
            case R.id.count3: {
                model.setPersonTwoScore(model.getPersonTwoScore()-1);
                scorePersonTwoTextView.setText(String.valueOf(model.getPersonTwoScore()));
                break;
            }
            case R.id.count4: {
                model.setPersonTwoScore(model.getPersonTwoScore()+1);
                scorePersonTwoTextView.setText(String.valueOf(model.getPersonTwoScore()));
                break;
            }
            case R.id.count5: {
                model.setPersonThreeScore(model.getPersonThreeScore()-1);
                scorePersonThreeTextView.setText(String.valueOf(model.getPersonThreeScore()));
                break;
            }
            case R.id.count6: {
                model.setPersonThreeScore(model.getPersonThreeScore()+1);
                scorePersonThreeTextView.setText(String.valueOf(model.getPersonThreeScore()));
                break;
            }
            case R.id.register: {
                updateScore();
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
        model.updateScore();

        scoreTextViews[0][model.getRound() - 1].setText(String.valueOf(model.getPersonOneTotalScore()));
        scoreTextViews[1][model.getRound() - 1].setText(String.valueOf(model.getPersonTwoTotalScore()));
        scoreTextViews[2][model.getRound() - 1].setText(String.valueOf(model.getPersonThreeTotalScore()));

        model.nextRound();

        scorePersonOneTextView.setText("0");
        scorePersonTwoTextView.setText("0");
        scorePersonThreeTextView.setText("0");
        if (model.getRound() == 13) {
            endGame();
        }
    }

    void endGame(){
        registerScore.setEnabled(false);
        scorePersonOneTextView.setTextColor(scorePersonOneTextView.getTextColors().withAlpha(0));
        scorePersonTwoTextView.setTextColor(scorePersonTwoTextView.getTextColors().withAlpha(0));
        scorePersonThreeTextView.setTextColor(scorePersonThreeTextView.getTextColors().withAlpha(0));
        decrementPersonOneButton.setEnabled(false);
        incrementPersonOneButton.setEnabled(false);
        decrementPersonTwoButton.setEnabled(false);
        incrementPersonTwoButton.setEnabled(false);
        decrementPersonThreeButton.setEnabled(false);
        incrementPersonThreeButton.setEnabled(false);
        finalScorePersonOneTextView.setText(String.valueOf(model.getPersonOneTotalScore()));
        finalScorePersonTwoTextView.setText(String.valueOf(model.getPersonTwoTotalScore()));
        finalScorePersonThreeTextView.setText(String.valueOf(model.getPersonThreeTotalScore()));
    }

}
