package com.example.league95.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    boolean pressed = true;
    CountDownTimer ct;
    //Button btn = (Button) findViewById(R.id.button);
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        //Create Media Player
        mediaPlayer = MediaPlayer.create(this,R.raw.airhorn);

        //Create the text View
        textView = (TextView) findViewById(R.id.textView);

        //Create the seekbar
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (seekBar != null && fromUser)
                {
                    updateTimer(progress);

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void updateTimer(int secondsLeft)
    {
        int mins = (int) secondsLeft / 60;
        // Take the number of total time and
        //Simply subtract the minutes
        int seconds = secondsLeft - (mins*60);
        String time = mins+":"+seconds;
        if (seconds < 10)
        {
            time = mins+":0"+seconds;
        }
        if (mins < 10)
        {
            time = "0" + time;
        }
        textView.setText(time);
    }


    public void clickEgg(View view)
    {
        //int pro = seekBar.getProgress();
        if (pressed)
        {
            pressed = false;
            btn.setText("Stop");
            //pro = seekBar.getProgress();
            seekBar.animate().alpha(0).setDuration(10);
            seekBar.setEnabled(false);
            ct = new CountDownTimer(seekBar.getProgress()*1000 + 100, 1000){


                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    rstTime();
                    mediaPlayer.start();
                }
            };
            ct.start();
        } else {
            rstTime();
        }
    }

    public void rstTime()
    {
        ct.cancel();
        btn.setText("GO");
        textView.setText("00:30");
        pressed = true;
        seekBar.setEnabled(true);
        seekBar.setProgress(30);
        seekBar.animate().alpha(1).setDuration(10);
    }


}
