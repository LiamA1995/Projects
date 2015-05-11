package com.apps.la.guaca_mole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class Activity_Game extends Activity {

    //Array which holds all image buttons in the game
    ImageButton[] buttons;

    //Allows for generation of random numbers, allowing each play of the game to be different
    Random r;

    //User score variable
    int score;

    //Access to the TextViews for the 'score' and 'time remaining' display
    TextView view_score, timer;

    //String value for the score extra passed to the highscore activity
    public final static String SCORE = "com.apps.la.guaca-mole";

    //On Create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        //Init array to 9 buttons
        buttons = new ImageButton[9];

        //Init Random class
        r = new Random();

        //Get ability to access the XML TextViews from code by retrieving them by ID
        view_score = (TextView) findViewById(R.id.view_score);
        timer = (TextView) findViewById(R.id.timer);

        //Call game start method
        start();
    }

    //Game start function - initialises the game and starts play
    private void start(){

        //Assign each element of the array to one of the XML ImageButtons
        buttons[0] = (ImageButton)findViewById(R.id.button1);
        buttons[1] = (ImageButton)findViewById(R.id.button2);
        buttons[2] = (ImageButton)findViewById(R.id.button3);
        buttons[3] = (ImageButton)findViewById(R.id.button4);
        buttons[4] = (ImageButton)findViewById(R.id.button5);
        buttons[5] = (ImageButton)findViewById(R.id.button6);
        buttons[6] = (ImageButton)findViewById(R.id.button7);
        buttons[7] = (ImageButton)findViewById(R.id.button8);
        buttons[8] = (ImageButton)findViewById(R.id.button9);

        //Initialise score to 0
        score = 0;

        //Initialise intent to start the highscore activity, which will be started when the 30 second
        //game timer runs out
        final Intent intent = new Intent(this, HighScore.class);

        //Init an android countdown timer to 30 seconds with decrements of 1 second
        // - the amount of time allowed in one game
        new CountDownTimer(30000, 1000) {

            //Called each time the timer ticks down by one second
            public void onTick(long millisUntilFinished){

                //Update the timer TextView to notify user how much time is left
                timer.setText("Time: " + millisUntilFinished / 1000);

                //Calls the show method which displays teh ImageButtons for the user to
                //try and 'whack' on every tick
                show();
            }

            //Called when the time is up - in this case, the current game will end
            public void onFinish() {

                intent.putExtra(SCORE, score);

                //Start the HighScore activity once the game has finished
                startActivity(intent);
            }

        //Call the timer start function
        }.start();
    }

    //Show method - handles the visibility of buttons and relies on random number
    //generation to make each play of the game different
    public void show()
    {
        //Temporary variable used to store a randomly generated number between 0 and 8 and is used
        //to index the ImageButtons array
        final int number = r.nextInt(9);

        //Set the chosen random ImageButton to visible
        buttons[number].setVisibility(View.VISIBLE);

        //Initialise a 2 second countdown timer - this is the time period in which the user has
        //to press the button
        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished){
            }

            //At the end of the 2 seconds, if the button is still visible (i.e. the user hasn't pressed
            //it), then hide that button
            public void onFinish() {
                if(buttons[number].getVisibility() == View.VISIBLE){
                    buttons[number].setVisibility(View.INVISIBLE);
                }
            }

        //Start the timer
        }.start();
    }

    //Called when the user successfully manages to click one of the visible buttons in time
    public void Clicked(View v)
    {
        //Hide the clicked view so the user cannot press again and get a higher score than intended
        v.setVisibility(View.INVISIBLE);

        //Increase the score by 10 points
        score += 10;

        //Calls the method to display the score
        displayScore();
    }

    //Displays the score to the user
    public void displayScore()
    {
        //Updates the XML TextView displaying the score
        view_score.setText("Score: " + score);
    }


}
