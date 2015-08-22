package com.crackdeveloperz.tapon;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crackdeveloperz.tapon.utility.Utility;


public class MainActivity extends ActionBarActivity
{

    ///BHATTTE KO CODE
    private RelativeLayout mainRl;
    private static final String HIGH_SCORE_TAG="score";
    private static final int FULL_VALUE=100;
    private int highscore = 0, currentScore = 0, timerCount=FULL_VALUE;
    private int backgroundColor;
    private Button left, right;
    private ImageView restart;
    private TextView restartText;
    private boolean isLeftToBePressed = true;
    private ImageView bg;
    private float log1=(float)(Math.log(100-60)/Math.log(100));
    private MediaPlayer player ;
    private Animation scale;
    private MediaPlayer mp;
    private CircleProgressView mCircleView;
    private VerticalProgressBar timer;
    private SharedPreferences sharedPrefs;
    private static final int SCORE_INC_VAL=100;
    private LinearLayout scoreCard, buttonLl;


     @Override
    protected void onCreate(Bundle savedInstanceState)
     {


         supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         Utility.hideNagivation(this);
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         if (getSupportActionBar() != null) getSupportActionBar().hide();
         init();

     }

    private void init()
    {
        sharedPrefs=getPreferences(Context.MODE_PRIVATE);
        highscore=sharedPrefs.getInt(HIGH_SCORE_TAG, 0);

        player = MediaPlayer.create(MainActivity.this, R.raw.background);
        player.setLooping(true);


        mCircleView = (CircleProgressView) findViewById(R.id.circleView);
        mCircleView.setMaxValue(highscore);
        mCircleView.setValue(0);

        timer=(VerticalProgressBar)findViewById(R.id.timer);
        timer.setProgress(timerCount);

        left = (Button) findViewById(R.id.leftButton);
        right = (Button) findViewById(R.id.rightButton);
        bg = (ImageView) findViewById(R.id.background);
        mp = MediaPlayer.create(this, R.raw.soundforbutton);
        restart = (ImageView) findViewById(R.id.restart);
        restartText=(TextView)findViewById(R.id.restartText);
        Typeface style=Typeface.createFromAsset(getAssets(), "icomoon.ttf");
        restartText.setTypeface(style);
        restartText.setText(getResources().getString(R.string.restart_code2));
        restartText.setTextSize(120f);

        mainRl=(RelativeLayout)findViewById(R.id.mainRl);
        mainRl.setBackgroundColor(getResources().getColor(R.color.pair3_4));
        scoreCard=(LinearLayout)findViewById(R.id.scoreCard);
        buttonLl=(LinearLayout)findViewById(R.id.buttonLL);

        scale = new ScaleAnimation(1, 0.92f, 1, 0.92f);
        scale.setDuration(50);
    }


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
        playMusic();
        Utility.hideNagivation(this);
    }


    @Override
    protected void onPause()
    {
      player.pause();
      super.onPause();
    }


    public void buttonClicked(View v)
    {
        timerCount=timerCount-5;
        timer.setProgress(timerCount);

        v.startAnimation(scale);
        v.setSoundEffectsEnabled(false);
        mp.start();


        switch (v.getId())
        {


            case R.id.leftButton:
            {


                if (isLeftToBePressed)
                {
                    currentScore = currentScore + SCORE_INC_VAL;
                    Log.i("update", "left button pressed");
                    if (currentScore >= highscore)
                    {
                        highscore = currentScore;
                        mCircleView.setBarColor(getResources().getColor(R.color.pair2_1));
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
                    saveHighScore();
                    restart.setVisibility(View.VISIBLE);
                    restartText.setVisibility(View.VISIBLE);
                    buttonLl.setVisibility(View.GONE);

                    mainRl.setBackgroundColor(getResources().getColor(R.color.pair3_3));

                    ((TextView)findViewById(R.id.currentScore)).setText("CURRENT: " + currentScore);
                    ((TextView)findViewById(R.id.highScore)).setText("HIGH   : " + highscore);
                    scoreCard.setVisibility(View.VISIBLE);

                }

                break;
            }


            case R.id.rightButton: {

                if (!isLeftToBePressed)
                {

                    currentScore = currentScore + SCORE_INC_VAL;
                    Log.i("button", "Restart");
                    if (currentScore >= highscore)
                    {
                        mCircleView.setBarWidth(45);
                        highscore = currentScore;
                    }


                mCircleView.setValueAnimated(currentScore, 300);


                    if (shouldToggle())
                    {
                        isLeftToBePressed = true;

                        toggle();
                    }
                }   else
                {
                    saveHighScore();
                    restart.setVisibility(View.VISIBLE);
                    restartText.setVisibility(View.VISIBLE);
                    buttonLl.setVisibility(View.GONE);

                    mainRl.setBackgroundColor(getResources().getColor(R.color.pair3_3));
                    ((TextView)findViewById(R.id.currentScore)).setText("CURRENT: " + currentScore);
                    ((TextView)findViewById(R.id.highScore)).setText("HIGH   : " + highscore);
                    scoreCard.setVisibility(View.VISIBLE);

                }

                break;
            }

            case R.id.restart:
            {

                restartgame();
                restart.setVisibility(View.GONE);
                restartText.setVisibility(View.GONE);
                buttonLl.setVisibility(View.VISIBLE);
                scoreCard.setVisibility(View.GONE);

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
            mainRl.setBackgroundColor(getResources().getColor(R.color.pair3_4));
        }
        else
        {

            backgroundColor =  getResources().getColor(R.color.left_button);
            bg.setImageResource(R.drawable.left_circlee);
            mainRl.setBackgroundColor(getResources().getColor(R.color.pair3_3));

        }
    }

    public boolean shouldToggle()
    {
        int a = (int) (Math.random() * 20);
        return a>10;
    }

    @Override
    public void onBackPressed()
    {
        player.pause();
        super.onBackPressed();
    }

    public void restartgame()
    {
         timer.setProgress(FULL_VALUE);
         mCircleView.setMaxValue(highscore);
         currentScore=0;
         mCircleView.setValueAnimated(currentScore, 500);
    }

    private void saveHighScore()
    {
        SharedPreferences.Editor editor=sharedPrefs.edit();
        editor.putInt(HIGH_SCORE_TAG, highscore);
        editor.apply();
    }

    public void  playMusic()
    {
        player.setVolume(1 - log1, 1-log1);
        player.start();
    }

}

