package com.dennis_brink.android.bombsquad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewTime, textViewCountdown, textViewScore;
    ImageView imageViewGrenade1, imageViewGrenade2, imageViewGrenade3,
              imageViewGrenade4, imageViewGrenade5, imageViewGrenade6,
              imageViewGrenade7, imageViewGrenade8, imageViewGrenade9;
    GridLayout grid; // has to be androidx variant
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);
        textViewCountdown = findViewById(R.id.textViewCountdown);

        imageViewGrenade1 = findViewById(R.id.grenade1);
        imageViewGrenade2 = findViewById(R.id.grenade2);
        imageViewGrenade3 = findViewById(R.id.grenade3);
        imageViewGrenade4 = findViewById(R.id.grenade4);
        imageViewGrenade5 = findViewById(R.id.grenade5);
        imageViewGrenade6 = findViewById(R.id.grenade6);
        imageViewGrenade7 = findViewById(R.id.grenade7);
        imageViewGrenade8 = findViewById(R.id.grenade8);
        imageViewGrenade9 = findViewById(R.id.grenade9);

        grid = findViewById(R.id.grid);

    }
}