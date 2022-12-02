package com.dennis_brink.android.bombsquad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewTime, textViewCountdown, textViewScore;
    ImageView imageViewGrenade1, imageViewGrenade2, imageViewGrenade3,
              imageViewGrenade4, imageViewGrenade5, imageViewGrenade6,
              imageViewGrenade7, imageViewGrenade8, imageViewGrenade9;
    GridLayout grid; // has to be androidx variant

    int score=0;
    boolean state = false;
    Runnable runnable;
    Handler handler;

    ImageView[] explosivesArray;

    MediaPlayer mediaPlayer;

    private static final String TAG = "DENNIS_B";

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

        mediaPlayer = MediaPlayer.create(this, R.raw.explosion_6288);

        explosivesArray = new ImageView[] {imageViewGrenade1, imageViewGrenade2, imageViewGrenade3,
                                           imageViewGrenade4, imageViewGrenade5, imageViewGrenade6,
                                           imageViewGrenade7, imageViewGrenade8, imageViewGrenade9} ;

        grid = findViewById(R.id.grid);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                // do something every interval
                textViewCountdown.setText(""+l/1000);
            }

            @Override
            public void onFinish() {

                explosiveControl();

                // start init game duration (30 secs counting down)
                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long l) {
                        textViewTime.setText("Time remaining: " + l/1000);
                    }

                    @Override
                    public void onFinish() {
                        Intent i = new Intent(MainActivity.this, ResultActivity.class);
                        startActivity(i);
                    }
                }.start();
            }
        }.start();

    }

    public void increaseScore(View view){
        Log.d(TAG, "view " + view.getTag());
        score++;
        textViewScore.setText("Score: " + score);

        Log.d(TAG, ""+Integer.parseInt(view.getTag().toString()));
        ImageView v = explosivesArray[Integer.parseInt(view.getTag().toString()) - 1];
        v.setImageResource(R.drawable.boom3);

        // start audiofile
        if(mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(0); // if sound already playing then set back to 0 (cut off playing sound ad start new one)
        }
        mediaPlayer.start();

    }

    public void explosiveControl(){
        // if done we do...
        textViewCountdown.setVisibility(View.INVISIBLE);
        textViewTime.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);

        handler = new Handler();
        runnable = () -> {
            for(ImageView explosive: explosivesArray){
                explosive.setVisibility(View.INVISIBLE);
                explosive.setImageResource(R.drawable.granade); // set the images back to explosive
            }
            grid.setVisibility(View.VISIBLE);
            Random random = new Random();
            int i = random.nextInt(explosivesArray.length);
            explosivesArray[i].setVisibility(View.VISIBLE);

            handler.postDelayed(runnable, 2000); // this will setup the repeat sequence

        };

        handler.post(runnable); // this will start the runnable and in fact start the game; running in a loop

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemMute){
            // sound is on, state = false
            if(!state){ // sound is on --> turn it off (mute)
                mediaPlayer.setVolume(0, 0);
                item.setIcon(R.drawable.ic_baseline_volume_off_24);
                state = true;
            } else { // sound is off --> turn it on
                mediaPlayer.setVolume(1, 1);
                item.setIcon(R.drawable.ic_baseline_volume_up_24);
                state = false;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}