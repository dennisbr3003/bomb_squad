package com.dennis_brink.android.bombsquad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewInfo, textViewScore, textViewHighScore;
    AppCompatButton btnAgain, btnQuit;

    int score = 0;
    int highscore = 0;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = getIntent().getIntExtra("score", 0);

        textViewInfo = findViewById(R.id.textViewInfo);
        textViewScore = findViewById(R.id.textViewMyScore);
        textViewHighScore = findViewById(R.id.textViewMyHighest);

        textViewScore.setText("Your score: " + score);

        sp = this.getSharedPreferences("score", Context.MODE_PRIVATE);
        highscore = sp.getInt("highscore", 0);
        if(highscore < score){
            highscore = score;
            sp.edit().putInt("highscore", highscore).apply();
            textViewInfo.setText("Nice, the new highscore is yours! Go for higher scores and secure that number one position!");
        }
        else {
            if (highscore - score >= 10) {
                textViewInfo.setText("There is no career for you in this. You missed the highscore by " + (highscore - score) + " points...");
            } else {
                if(highscore - score == 0){
                    textViewInfo.setText("What are the odds?. You have the same score as the old highscore. Smells like match fixing");
                } else {
                    textViewInfo.setText("Practice some more. You missed the highscore by " + (highscore - score) + " points...");
                }
            }
        }
        textViewHighScore.setText("High score: " + highscore);

        btnAgain = findViewById(R.id.btnPlayAgain);
        btnQuit = findViewById(R.id.btnQuit);

        btnAgain.setOnClickListener(view -> {
            Intent i = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });

        btnQuit.setOnClickListener(view -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });

    }
}