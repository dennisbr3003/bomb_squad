package com.dennis_brink.android.bombsquad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewInfo, textViewScore, textViewHighScore;
    AppCompatButton btnAgain, btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewInfo = findViewById(R.id.textViewInfo);
        textViewScore = findViewById(R.id.textViewMyScore);
        textViewHighScore = findViewById(R.id.textViewMyScore);

        btnAgain = findViewById(R.id.btnPlayAgain);
        btnQuit = findViewById(R.id.btnQuit);

        btnAgain.setOnClickListener(view -> {

        });

        btnQuit.setOnClickListener(view -> {

        });

    }
}