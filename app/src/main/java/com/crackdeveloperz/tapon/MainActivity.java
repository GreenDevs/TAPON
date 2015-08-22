package com.crackdeveloperz.tapon;


import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

import com.crackdeveloperz.tapon.utility.Utility;


public class MainActivity extends ActionBarActivity
{

    ///BHATTTE KO CODE
    private int highscore = 0, currentScore = 0;
    private int backgroundColor;
    private Button left, right, restart;
    private boolean isLeftToBePressed = true;
    private ImageView bg;
    private String idforHighscore = "high";
    float log1=(float)(Math.log(100-50)/Math.log(100));
    MediaPlayer player ;




    Animation scale;

    MediaPlayer mp;

    //BHATTE ENDS HERE

    private CircleProgressView mCircleView;
    private Switch mSwitchSpin;
    private Switch mSwitchShowUnit;
    private SeekBar mSeekBar;
    private SeekBar mSeekBarSpinnerLength;

     @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Utility.hideNagivation(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       player = MediaPlayer.create(MainActivity.this, R.raw.background);
        player.setLooping(true);

        if(getSupportActionBar()!=null) getSupportActionBar().hide();
        mCircleView = (CircleProgressView) findViewById(R.id.circleView);
        mCircleView.setMaxValue(highscore);
       // mCircleView.setUnit("%");
        mCircleView.setValue(0);


        //MY ADDED CODE
//        (findViewById(R.id.leftButton)).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                score=score+50;
//                mCircleView.setValueAnimated(score, 1000);
//            }
//        });



        /// BHATTE KO CODE HERE

// Animation set to join both scaling and moving


        left = (Button) findViewById(R.id.leftButton);
        right = (Button) findViewById(R.id.rightButton);
        restart=(Button)findViewById(R.id.restart);
        bg=(ImageView)findViewById(R.id.background);
        mp = MediaPlayer.create(this, R.raw.soundforbutton);
        scale = new ScaleAnimation(1, 0.92f, 1, 0.92f);//, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
// 1 second duration
        scale.setDuration(50);
// Moving up


        ////BHHATE KO CODE ENDS HERE

        //Setup Switch
//        mSwitchSpin = (Switch) findViewById(R.id.switch1);
//        mSwitchSpin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//                if (isChecked)
//                {
//                    mCircleView.spin();
//                } else {
//                    mCircleView.stopSpinning();
//                }
//            }
//        });

//        mSwitchShowUnit = (Switch) findViewById(R.id.switch2);
//        mSwitchShowUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//                    mCircleView.setShowUnit(isChecked);
//            }
//        });
//
//        //Setup SeekBar
//        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
//        mSeekBar.setMax(500);
//        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//        {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
//            {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar)
//            {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar)
//            {
//                mCircleView.setValueAnimated(seekBar.getProgress(),1000);
//                mSwitchSpin.setChecked(false);
//            }
//        });

//        mSeekBarSpinnerLength = (SeekBar) findViewById(R.id.seekBar2);
//        mSeekBarSpinnerLength.setMax(360);
//        mSeekBarSpinnerLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//        {
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
//            {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar)
//            {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar)
//            {
//                mCircleView.setSpinningBarLength(seekBar.getProgress());
//            }
//        });


    }


    @Override
    protected void onStart()
    {
        super.onStart();
        Utility.hideNagivation(this);
        mCircleView.setValue(0);
        mCircleView.setValueAnimated(currentScore);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //mBackgroundSound.execute();
       playMusic();
        Utility.hideNagivation(this);
    }


    @Override
    protected void onPause()
    {

        //mBackgroundSound.execute();
      player.pause();

        super.onPause();
    }





    ///BHATTE KO ADDED
    public void buttonClicked(View v)
    {

        v.startAnimation(scale);
        v.setSoundEffectsEnabled(false);
        mp.start();


        switch (v.getId())
        {


            case R.id.leftButton:
            {


                if (isLeftToBePressed)
                {
                    currentScore = currentScore + 5;
                    Log.i("update", "left button pressed");
                    if (currentScore > highscore)
                    {
                        highscore = currentScore;
                    }

                    mCircleView.setValueAnimated(currentScore, 300);
                    if (shouldToggle())
                    {
                        isLeftToBePressed = false;
                        toggle();
                    }
                }
                else
                {
                    restart.setVisibility(View.VISIBLE);
                    left.setClickable(false);
                    right.setClickable(false);
                }

                break;
            }


            case R.id.rightButton: {

                if (!isLeftToBePressed) {

                    currentScore = currentScore + 5;
                    Log.i("button", "Restart");
                    if (currentScore > highscore)
                    {
                        highscore = currentScore;
                    }


                mCircleView.setValueAnimated(currentScore, 300);


                    if (shouldToggle()) {
                        isLeftToBePressed = true;

                        toggle();
                    }
                } else {
                    restart.setVisibility(View.VISIBLE);

                    left.setClickable(false);
                    right.setClickable(false);
                }

                break;
            }

            case R.id.restart:
            {

                restartgame();
                restart.setVisibility(View.GONE);
                left.setClickable(true);
                right.setClickable(true);
            }
        }
    }

    public void toggle()
    {

        if (backgroundColor == getResources().getColor(R.color.left_button))
        {
            backgroundColor =  getResources().getColor(R.color.right_button);
            bg.setImageResource(R.drawable.right_circle);
        }
        else
        {

            backgroundColor =  getResources().getColor(R.color.left_button);
            bg.setImageResource(R.drawable.left_circlee);

        }
    }

    public boolean shouldToggle() {

        boolean should = true;

        int a = (int) (Math.random() * 20);

        if (a > 10) {
            should = true;
        } else should = false;
        return should;
    }




    @Override
    public void onBackPressed() {


        player.pause();
        super.onBackPressed();
    }

    public void restartgame()
    {
         mCircleView.setMaxValue(highscore);
         currentScore=0;
         mCircleView.setValueAnimated(currentScore, 500);
    }






  public void  playMusic() {


        player.setVolume(1 - log1, 1-log1);
        player.start();


    }
    ///BHATTE KO ENDS HERE
}

