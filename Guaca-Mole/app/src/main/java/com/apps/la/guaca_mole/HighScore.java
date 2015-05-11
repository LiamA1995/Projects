package com.apps.la.guaca_mole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class HighScore extends Activity {

    //TextView to show the user score
    TextView show_score;

    //Holds the user score passed over from the game activity
    int score;

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //Retrieves the xml TextView for showing the score by ID
        show_score = (TextView)findViewById(R.id.show_score);

        //Receive the intent which started this activity - this holds the Extra containing the score
        //to display
        Intent intent = getIntent();

        //Gets the extra that came with the intent and sets it to the score variable with 0 as the
        //default value
        score = intent.getIntExtra(Activity_Game.SCORE, 0);

        //Call the method to show the score
        showUserScore();
    }

    //Show score to the user
    private void showUserScore()
    {
        //Update the score TextView to show the score to the user
        show_score.setText("You scored: " + score + "!");
    }

    //Method which handles the play again button being clicked
    public void playAgain(View v){
        //Initialise activity to play again
        Intent intent = new Intent(this, Activity_Game.class);

        //Start the game activity
        startActivity(intent);
    }
}
