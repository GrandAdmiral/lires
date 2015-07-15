package com.lires.jimmy;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.sql.Date;
import java.util.Locale;
import java.util.Random;

import android.provider.Settings.Secure;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;


public class Game extends ActionBarActivity {


    public ArrayList<String> help = new ArrayList<String>(Arrays.asList("pub", "tel", "50"));
    public Boolean right;
    public float x1, x2, x3, x4 = 0;
    public int y1, y2, y3, y4 = 0;
    CountDownTimer a, timer2, timer3;
    final Session game = new Session();
    int bflag, aflag, pauseflag, sleepflag, musicflag;
    public Dialog dialog, pub;
    long secs;
    private SharedPreference sharedPreference;
    ProgressBar ab;
    private Handler mHandler = new Handler();
    public ArrayList<Question> Questions;
    private TextView voith, questtext, textTimer, tel, anstel, publ, firstbar, firstwords, secondbar, secondwords, thirdbar, thirdwords, fourthbar, fourthwords;
    private TextView firstscore, secondscore, thirdscore, fourthscore;
    private TextView red_sofar, green_sofar, blue_sofar, purple_sofar, orange_sofar, green_500, trial, trial2;
    private TextView green100, green200, green300, green500, orange1000, orange2000, orange3000, orange5000, red10000, red20000, red30000, red50000, purple100000, purple200000, purple300000;
    private TextView blue500, blue580, blue1000;
    private TextSwitcher questtext2;
    private Button a1, a2, a3, a4, a11, a22, a33, a44, exittel, exitpub, gamw;
    private ImageButton gh, rh, bh;
    private DonutProgress donutProgress;
    private RelativeLayout hiddenPanel;
    private RelativeLayout rel1;
    private FrameLayout framework;
    private LinearLayout scores_sofar, layoutcontainer;
    private boolean isPanelShown, musicon;
    private int flabutton1, flagbutton2, flagbutton3, flagbutton4;
    private boolean answersready, boardsetting;
    private int flaghelp1, flaghelp2, flaghelp3, stopflag, restartflag, flag501, flag502, flag503, flag504;
    public String[] answers = {"1", "2", "3", "4"};
    public MediaPlayer answertapped, correct, gamemusic, startmusic, wrongmusic;
    private int timer1flag, timer2flag, timer3flag, timerlastflag;
    private View v1;
    private String answ234;
    InterstitialAd mInterstitialAd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        SharedPreference sharedP = new SharedPreference();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6172971984485632/1248807509");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(Game.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (sharedP.getValue(getApplicationContext(), "OP_PREFS", "musicon").equals("1")) {
            musicon = true;
        } else {
            musicon = false;
        }
        musicflag = 0;
        timer1flag = 0;
        timer2flag = 0;
        timer3flag = 0;
        boardsetting = false;
        answersready = false;
        framework = (FrameLayout) findViewById(R.id.framework);
        layoutcontainer = (LinearLayout) findViewById(R.id.layoutContainer);
        rel1 = (RelativeLayout) findViewById(R.id.rel1);
        red_sofar = (TextView) findViewById(R.id.red_sofar);
        blue_sofar = (TextView) findViewById(R.id.blue_sofar);
        green_sofar = (TextView) findViewById(R.id.green_sofar);
        orange_sofar = (TextView) findViewById(R.id.orange_sofar);
        purple_sofar = (TextView) findViewById(R.id.purple_sofar);
        scores_sofar = (LinearLayout) findViewById(R.id.scores_sofar);
        hiddenPanel = (RelativeLayout) findViewById(R.id.hidden_panel);
        hiddenPanel.setVisibility(View.INVISIBLE);
        isPanelShown = false;
        green100 = (TextView) findViewById(R.id.green_100);
        green200 = (TextView) findViewById(R.id.green_200);
        green300 = (TextView) findViewById(R.id.green_300);
        green500 = (TextView) findViewById(R.id.green_500);
        orange1000 = (TextView) findViewById(R.id.orange_1000);
        orange2000 = (TextView) findViewById(R.id.orange_2000);
        orange3000 = (TextView) findViewById(R.id.orange_3000);
        orange5000 = (TextView) findViewById(R.id.orange_5000);
        red10000 = (TextView) findViewById(R.id.red_10000);
        red20000 = (TextView) findViewById(R.id.red_20000);
        red30000 = (TextView) findViewById(R.id.red_30000);
        red50000 = (TextView) findViewById(R.id.red_50000);
        purple100000 = (TextView) findViewById(R.id.purple_100000);
        purple200000 = (TextView) findViewById(R.id.purple_200000);
        purple300000 = (TextView) findViewById(R.id.purple_300000);
        blue500 = (TextView) findViewById(R.id.blue_500000);
        blue580 = (TextView) findViewById(R.id.blue_586509);
        blue1000 = (TextView) findViewById(R.id.blue_1000000);
        gamw = (Button) findViewById(R.id.gamwta);
        a11 = (Button) findViewById(R.id.ans11);
        a22 = (Button) findViewById(R.id.ans22);
        a33 = (Button) findViewById(R.id.ans33);
        a44 = (Button) findViewById(R.id.ans44);


        flabutton1 = 1;
        flagbutton2 = 1;
        flagbutton3 = 1;
        flagbutton4 = 1;
        flaghelp1 = 0;
        flaghelp2 = 0;
        flaghelp3 = 0;
        stopflag = 0;
        restartflag = 0;
        flag501 = 0;
        flag502 = 0;
        flag503 = 0;
        flag504 = 0;

        scores_sofar.post(new Runnable() {

            @Override
            public void run() {

                int width = scores_sofar.getWidth(); // will be non-zero
                int height = scores_sofar.getHeight(); // will be non-zero
                ViewGroup.LayoutParams paroum = hiddenPanel.getLayoutParams();
                System.out.println("Width= " + width);
                paroum.width = width;
                hiddenPanel.setLayoutParams(paroum);
                green_sofar.setVisibility(View.INVISIBLE);
                red_sofar.setVisibility(View.INVISIBLE);
                orange_sofar.setVisibility(View.INVISIBLE);
                purple_sofar.setVisibility(View.INVISIBLE);
                blue_sofar.setVisibility(View.INVISIBLE);
            }
        });

        setFont(framework, font);


        sleepflag = 0;
        bflag = 1;
        pauseflag = 0;


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        a1 = (Button) findViewById(R.id.ans1);
        a1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if ((bflag == 0) && (flabutton1 == 0)) {
                    bflag = 1;
                    resetButtonFlags();
                    if (musicon) gamemusic.pause();
                    if (musicon) answertapped.start();
                    pauseflag = 1;
                    a1.setBackgroundResource(R.drawable.mybuttonanswerblue);
                    right = (game.ca.equals(answers[0]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                    } else {
                        wrong = null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a1, answers[0], right, wrong, v);
                        }
                    }, game.getDelay() * 1000);

                }
            }
        });
        a2 = (Button) findViewById(R.id.ans2);
        a2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if ((bflag == 0) && (flagbutton2 == 0)) {
                    bflag = 1;
                    resetButtonFlags();
                    if (musicon) gamemusic.pause();
                    if (musicon) answertapped.start();
                    pauseflag = 1;
                    a2.setBackgroundResource(R.drawable.mybuttonanswerblue);
                    right = (game.ca.equals(answers[1]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                    } else {
                        wrong = null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a2, answers[1], right, wrong, v);
                        }
                    }, game.getDelay() * 1000);

                }
            }
        });
        a3 = (Button) findViewById(R.id.ans3);
        a3.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if ((bflag == 0) && (flagbutton3 == 0)) {
                    bflag = 1;
                    resetButtonFlags();
                    if (musicon) gamemusic.pause();
                    if (musicon) answertapped.start();
                    pauseflag = 1;
                    a3.setBackgroundResource(R.drawable.mybuttonanswerblue);
                    right = (game.ca.equals(answers[2]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                    } else {
                        wrong = null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a3, answers[2], right, wrong, v);
                        }
                    }, game.getDelay() * 1000);
                }
            }
        });


        a4 = (Button) findViewById(R.id.ans4);
        a4.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if ((bflag == 0) && (flagbutton4 == 0)) {
                    bflag = 1;
                    resetButtonFlags();
                    if (musicon) gamemusic.pause();
                    if (musicon) answertapped.start();
                    pauseflag = 1;
                    a4.setBackgroundResource(R.drawable.mybuttonanswerblue);
                    right = (game.ca.equals(answers[3]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                    } else {
                        wrong = null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a4, answers[3], right, wrong, v);
                        }
                    }, game.getDelay() * 1000);

                }
            }
        });


        gh = (ImageButton) findViewById(R.id.greenhelp);
        gh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((bflag == 0) && (answersready))
                    boitheia("green", "tel");
            }
        });
        rh = (ImageButton) findViewById(R.id.redhelp);
        rh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((bflag == 0) && (answersready))
                    boitheia("red", "50");
            }
        });
        bh = (ImageButton) findViewById(R.id.bluehelp);
        bh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //System.out.println("Button setting pauseflag 1");
                if ((bflag == 0) && (answersready))
                    boitheia("blue", "pub");
            }
        });


        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setMax(45);
        donutProgress.setProgress(45);
        donutProgress.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()));
        voith = (TextView) findViewById(R.id.voith);
        voith.setText(Html.fromHtml(getString(R.string.voith_html)));


        //textTimer = (TextView) findViewById(R.id.textViewToday);

        Intent intent = this.getIntent();
        sharedPreference = new SharedPreference();
        Context context = getApplicationContext();
        String user_json = sharedPreference.getValue(context, "OP_PREFS", "questionsdownloaded");
        String username = sharedPreference.getValue(context, "OP_PREFS", "username");
        System.out.println(username);
        Gson gson = new Gson();
        System.out.println(user_json);
        Questions = gson.fromJson(user_json, new TypeToken<ArrayList<Question>>() {
        }.getType());
        questtext = (TextView) findViewById(R.id.questtext);
        /*questtext2 = (TextSwitcher) findViewById(R.id.quest2);
        final Animation out = new AlphaAnimation(0.0f, 1.0f);
        out.setDuration(3000);
        AnimationSet as = new AnimationSet(true);
        as.addAnimation(out);
        questtext2.setInAnimation(as);
        questtext2.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                // TODO Auto-generated method stub
                // create new textView and set the properties like clolr, size etc
                TextView myText = new TextView(Game.this);
                myText.setGravity(Gravity.CENTER);
                myText.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //int pixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());
                myText.setTextSize(14);
                myText.setTextColor(Color.WHITE);
                return myText;
            }
        });*/

        String android_id = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        game.setPlayerid(android_id);
        game.setQuestions(Questions);
        System.out.println("Questions =" + game.getQuestions());
        game.help = new ArrayList<String>(Arrays.asList("pub", "tel", "50"));
        answertapped = MediaPlayer.create(context, R.raw.answertapped);
        correct = MediaPlayer.create(context, R.raw.correct);
        gamemusic = MediaPlayer.create(context, R.raw.game);
        wrongmusic = MediaPlayer.create(context, R.raw.wrong);
        game.start();
        aflag = 0;
        randomizeAnswers(answers);
        controller(game, "0");


    }

    public void controller(final Session game, String answer) {
        System.out.println("controller(" + answer);
        System.out.println("Current Question=" + game.getCurrquest() + "   Score=" + game.getScore());
        if (answer.equals("0")) {
            //System.out.println("Answer equals 0");
            if (game.getFlag() < 1) {

                System.out.println("Answer equals set the board");
                flag501 = 0;
                flag502 = 0;
                flag503 = 0;
                flag504 = 0;
                answersready = false;
                randomizeAnswers(answers);
                setBoard(game);
                bflag = 0;
                donutProgress.setMax(45);
                donutProgress.setProgress(45);
                pauseflag = 0;
                startTimer(45000, 1000);
                System.out.println("Pause flag (controller)= " + pauseflag);

            } else {

                pauseflag = 1;
                Context context = getApplicationContext();
                String score = sharedPreference.getValue(context, "OP_PREFS", "score");
                String scoresofar = sharedPreference.getValue(context, "OP_PREFS", "scoresofar");
                int newscore = Integer.parseInt(score) + game.getScore();
                System.out.println("Scoresofar:" + scoresofar);
                String newscoresofar;
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month1 = cal.get(Calendar.MONTH) + 1;
                String month;
                if (month1 < 10) {
                    month = "0" + String.valueOf(month1);
                } else {
                    month = String.valueOf(month1);
                }
                int day1 = cal.get(Calendar.DAY_OF_MONTH);
                String day;
                if (day1 < 10) {
                    day = "0" + String.valueOf(day1);
                } else {
                    day = String.valueOf(day1);
                }
                int gamequest = game.getCurrquest() - 1;
                if (gamequest == -1) gamequest = 0;
                if (game.getFlag() == 2) {
                    gamequest = 18;
                }
                if ((scoresofar != null) && (scoresofar != "")) {
                    newscoresofar = scoresofar + "0;0;" + year + month + day + ";####0;1;" + year + month + day + ";####" + game.getScore() + ";" + gamequest + ";" + year + month + day + ";####";
                } else {
                    newscoresofar = "0;0;" + year + month + day + ";####0;1;" + year + month + day + ";####" + game.getScore() + ";" + gamequest + ";" + year + month + day + ";####";
                }
                sharedPreference.save(context, Integer.toString(newscore), "OP_PREFS", "score");
                System.out.println("New Scoresofar=" + newscoresofar);
                sharedPreference.save(context, newscoresofar, "OP_PREFS", "scoresofar");
                System.out.println("Saving Score as:" + newscore);
                System.out.println("Saved:" + sharedPreference.getValue(context, "OP_PREFS", "score"));
                dialog = new Dialog(Game.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                // Include dialog.xml file

                dialog.setContentView(R.layout.finishscore);


                /*WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                lp.width = (int) (width * 0.6);
                lp.height = (int) (height * 0.6);
                dialog.getWindow().setAttributes(lp);*/

                TextView kerdisate = (TextView) dialog.findViewById(R.id.kerdisate);
                kerdisate.setText(Html.fromHtml(getString(R.string.kerdisate_html)));
                TextView lires12 = (TextView) dialog.findViewById(R.id.lires12);
                lires12.setText(Html.fromHtml(getString(R.string.lires_html)));
                TextView finalscore = (TextView) dialog.findViewById(R.id.finalscore);
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
                symbols.setGroupingSeparator(',');
                finalscore.setText(String.valueOf(formatter.format(game.getScore())));
                Button pameksana = (Button) dialog.findViewById(R.id.goagain);
                Button pisw = (Button) dialog.findViewById(R.id.goback);
                pameksana.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Button pressed?");
                        dialog.dismiss();

                    }
                });
                pisw.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Intent intent = new Intent(Game.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(final DialogInterface arg0) {
                        //ADDSCORE
                        reset();


                    }
                });
                try {
                    dialog.show();
                } catch (Exception e) {
                }

            }
        } else {
            //changeColor(answer);
            a.cancel();
            game.advance(answer);
            framework.postDelayed(new Runnable() {

                @Override
                public void run() {
                    sleepflag = 0;

                }
            }, 3000);

            //timer3.cancel();
            if ((!right) && (game.getCurrquest() > 1)) timer3.cancel();
            System.out.println("About to call controller from controller");
            controller(game, "0");
        }
    }


    public void setBoard(final Session game1) {
        boardsetting = true;
        questtext.setText(game1.questt);
        resetButtonFlags();
        a11.setText("");
        a1.setText("");
        a2.setText("");
        a22.setText("");
        a3.setText("");
        a33.setText("");
        a4.setText("");
        a44.setText("");
        Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            public void run() {
                String first = "<font color='#000000'>A. </font>";
                String next = "<font color='#ffffff'>" + getAns(game1, answers[0]) + "</font>";
                if (flag501 == 0) {
                    a1.setText(Html.fromHtml(first + next));
                    flabutton1 = 0;
                }
            }
        }, 1200);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                String first = "<font color='#000000'>B. </font>";
                String next = "<font color='#ffffff'>" + getAns(game1, answers[1]) + "</font>";
                if (flag502 == 0) {
                    a2.setText(Html.fromHtml(first + next));
                    flagbutton2 = 0;
                }
            }
        }, 2400);
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                String first = "<font color='#000000'>Γ. </font>";
                String next = "<font color='#ffffff'>" + getAns(game1, answers[2]) + "</font>";
                if (flag503 == 0) {
                    a3.setText(Html.fromHtml(first + next));
                    flagbutton3 = 0;
                }
            }
        }, 3600);
        Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            public void run() {
                String first = "<font color='#000000'>Δ. </font>";
                String next = "<font color='#ffffff'>" + getAns(game1, answers[3]) + "</font>";
                if (flag504 == 0) {
                    a4.setText(Html.fromHtml(first + next));
                    flagbutton4 = 0;
                    answersready = true;
                }
            }
        }, 4800);
        a1.setBackgroundResource(R.drawable.mybuttonanswer);
        a11.setBackgroundResource(R.drawable.mybuttonanswer);
        a2.setBackgroundResource(R.drawable.mybuttonanswer);
        a22.setBackgroundResource(R.drawable.mybuttonanswer);
        a33.setBackgroundResource(R.drawable.mybuttonanswer);
        a3.setBackgroundResource(R.drawable.mybuttonanswer);
        a4.setBackgroundResource(R.drawable.mybuttonanswer);
        a44.setBackgroundResource(R.drawable.mybuttonanswer);
        AlphaAnimation transparency = new AlphaAnimation(0, 1);
        transparency.setDuration(1);
        transparency.setFillAfter(true);
        a1.startAnimation(transparency);
        a2.startAnimation(transparency);
        a3.startAnimation(transparency);
        a4.startAnimation(transparency);
    }


    private void startTimer(int duration, int interval) {
        System.out.println("Creating timer1");
        a = new CountDownTimer(duration, interval)

        {

            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                //System.out.println("Time left="+safeLongToInt(seconds));
                final int barVal = (100) - ((int) (seconds / 60 * 100) + (int) (seconds % 60));
                donutProgress.setProgress(donutProgress.getProgress() - 1);
                if (!(gamemusic.isPlaying()) && !(bflag == 1) && (musicflag == 0)) {
                        /*
                        gamemusic.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            public void onPrepared(MediaPlayer player) {
                          */
                    if (musicon) gamemusic.start();
                            /*}
                        });
                        Context context =getApplicationContext();
                        //gamemusic=MediaPlayer.create(context,R.raw.game);
                        //gamemusic.reset();
                        gamemusic.release();
                        gamemusic.reset();
                        gamemusic.prepareAsync();*/
                }
                if (pauseflag == 1) {
                    secs = seconds;
                    System.out.println("Cancelled because of Pause Flag");
                    a.cancel();
                }
                /** mHandler.post(new Runnable() {
                 public void run() {
                 ab.setProgress(barVal);
                 ab.setSecondaryProgress(0);
                 }
                 });
                 **/
                //System.out.println("Set Progress:"+barVal);
                //textTimer.setText(String.format("%02d", seconds/60) + ":" + String.format("%02d", seconds%60));
                // format the textview to show the easily readable format

            }

            @Override
            public void onFinish() {
                System.out.println("Timer1 cancelled interrupted");
                donutProgress.setProgress(0);
                if (musicon) gamemusic.pause();
                if (pauseflag != 1) {
                    System.out.println("Time finished");
                    System.out.println("About to call controller from Timer onFinish");
                    right = false;
                    if (stopflag != 1) {
                        controller(game, "5");
                        pauseflag = 0;
                    } else {
                        restartflag = 1;
                    }
                }
            }
        }.start();
        if (musicon) gamemusic.start();
        System.out.println("Starting timer1");

    }


    public void boitheia(final String color, final String h) {
        Random randomGenerator = new Random();
        System.out.println("Help=" + game.help);
        int index = randomGenerator.nextInt(game.help.size());
        if (h.equals("tel")) {
            pauseflag = 1;
            dialog = new Dialog(Game.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            // Include dialog.xml file
            dialog.setContentView(R.layout.dialog);
            tel = (TextView) dialog.findViewById(R.id.tel);
            tel.setText(Html.fromHtml(getString(R.string.tel_html)));
            anstel = (TextView) dialog.findViewById(R.id.anstel);
            anstel.setText("Σίγουρα η απάντηση είναι " + game.getAnswer(Integer.parseInt(game.getCa())));
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(final DialogInterface arg0) {
                    donutProgress.setProgress(safeLongToInt(secs));
                    System.out.println("EEEEE?");
                    pauseflag = 0;
                    //System.out.println("Start "+safeLongToInt(secs)+"secs");
                    game.help.remove("tel");
                    startTimer(safeLongToInt(secs) * 1000, 1000);
                    if (color.equals("green")) {
                        gh.setVisibility(View.INVISIBLE);
                    }
                    if (color.equals("red")) {
                        rh.setVisibility(View.INVISIBLE);
                    }
                    if (color.equals("blue")) {
                        bh.setVisibility(View.INVISIBLE);
                    }

                }
            });


            // set values for custom dialog components - text, image and button
               /* TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Custom dialog Android example.");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                image.setImageResource(R.drawable.image0);
            */
            exittel = (Button) dialog.findViewById(R.id.exittel);
            exittel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });

            dialog.show();

        }
        if (h.equals("50")) {
            int n = find50(game, answers)[0] + 1;
            int n1 = find50(game, answers)[1] + 1;
            System.out.println("N=" + n + "  ,N1=" + n1);
            if ((n != 1) && (n1 != 1)) {
                flag501 = 1;
                a1.setText("               ");
                flabutton1 = 1;
            }
            if ((n != 2) && (n1 != 2)) {
                flag502 = 1;
                a2.setText("               ");
                flagbutton2 = 1;
            }
            if ((n != 3) && (n1 != 3)) {
                flag503 = 1;
                a3.setText("               ");
                flagbutton3 = 1;
            }
            if ((n != 4) && (n1 != 4)) {
                flag504 = 1;
                a4.setText("               ");
                flagbutton4 = 1;
            }
            game.help.remove("50");
            if (color.equals("green")) {
                gh.setVisibility(View.INVISIBLE);
            }
            if (color.equals("red")) {
                rh.setVisibility(View.INVISIBLE);
            }
            if (color.equals("blue")) {
                bh.setVisibility(View.INVISIBLE);
            }
        }
        if (h.equals("pub")) {

            pauseflag = 1;
            pub = new Dialog(Game.this);
            pub.getWindow();
            pub.requestWindowFeature(Window.FEATURE_NO_TITLE);

            // Include dialog.xml file
            pub.setContentView(R.layout.pub);

            publ = (TextView) pub.findViewById(R.id.pub);
            publ.setText(Html.fromHtml(getString(R.string.pub_html)));
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp = game.getPercentages();
            firstbar = (TextView) pub.findViewById(R.id.firstbar);
            firstwords = (TextView) pub.findViewById(R.id.firstwords);
            firstscore = (TextView) pub.findViewById(R.id.firstscore);
            secondbar = (TextView) pub.findViewById(R.id.secondbar);
            secondwords = (TextView) pub.findViewById(R.id.secondwords);
            secondscore = (TextView) pub.findViewById(R.id.secondscore);
            thirdbar = (TextView) pub.findViewById(R.id.thirdbar);
            thirdscore = (TextView) pub.findViewById(R.id.thirdscore);
            fourthwords = (TextView) pub.findViewById(R.id.fourthwords);
            fourthbar = (TextView) pub.findViewById(R.id.fourthbar);
            fourthscore = (TextView) pub.findViewById(R.id.fourthscore);
            final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            ArrayList<Integer> finaltemp = new ArrayList<Integer>();
            System.out.println("Findpub: " + findpub(game, answers).toString());
            for (int q = 0; q < 4; q++) {
                System.out.println("q=" + q);
                System.out.println("Index of findpub for current q: " + findpub(game, answers).indexOf(q));
                finaltemp.add(temp.get(findpub(game, answers).indexOf(q)));
                System.out.println("Done for q=" + q);
            }
            System.out.println("Temp=" + temp.toString());
            float[] goal = new float[]{(finaltemp.get(0) * scale + 0.5f), (finaltemp.get(1) * scale + 0.5f), (finaltemp.get(2) * scale + 0.5f), (finaltemp.get(3) * scale + 0.5f)};
            System.out.println("Goal1= " + temp.get(0) + " , Goal2= " + temp.get(1) + " , Goal3= " + temp.get(2) + " , Goal4= " + temp.get(3));
            System.out.println("Finaltemp1=" + finaltemp.get(0) + "  Finaltemp2=" + finaltemp.get(1) + "  Finaltemp3=" + finaltemp.get(2) + "  Finaltemp4=" + finaltemp.get(3));
            System.out.println("Goal1= " + goal[0] + " , Goal2= " + goal[1] + " , Goal3= " + goal[2] + " , Goal4= " + goal[3]);
            System.out.println("100dp= " + 100 * scale + 0.5f);
            ViewGroup.LayoutParams params = firstbar.getLayoutParams();
            params.height = 0;
            firstbar.setLayoutParams(params);
            params = secondbar.getLayoutParams();
            params.height = 0;
            secondbar.setLayoutParams(params);
            params = thirdbar.getLayoutParams();
            params.height = 0;
            thirdbar.setLayoutParams(params);
            params = fourthbar.getLayoutParams();
            params.height = 0;
            fourthbar.setLayoutParams(params);
            startTimer2(3000, 50, goal, finaltemp);
            //<TextView android:text="First line\r\nNext line"
            pub.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(final DialogInterface arg0) {
                    timer2.cancel();
                    y1 = 0;
                    y2 = 0;
                    y3 = 0;
                    y4 = 0;
                    x1 = 0;
                    x2 = 0;
                    x3 = 0;
                    x4 = 0;
                    ViewGroup.LayoutParams params1 = firstbar.getLayoutParams();
                    params1.height = 0;
                    firstbar.setLayoutParams(params1);
                    ViewGroup.LayoutParams params2 = secondbar.getLayoutParams();
                    params2.height = 0;
                    secondbar.setLayoutParams(params2);
                    ViewGroup.LayoutParams params3 = thirdbar.getLayoutParams();
                    params3.height = 0;
                    thirdbar.setLayoutParams(params3);
                    ViewGroup.LayoutParams params4 = fourthbar.getLayoutParams();
                    params4.height = 0;
                    fourthbar.setLayoutParams(params4);
                    donutProgress.setProgress(safeLongToInt(secs));
                    System.out.println("EEEEE?");
                    pauseflag = 0;
                    //System.out.println("Start "+safeLongToInt(secs)+"secs");
                    game.help.remove("pub");
                    startTimer(safeLongToInt(secs) * 1000, 1000);
                    if (color.equals("green")) {
                        gh.setVisibility(View.INVISIBLE);
                    }
                    if (color.equals("red")) {
                        rh.setVisibility(View.INVISIBLE);
                    }
                    if (color.equals("blue")) {
                        bh.setVisibility(View.INVISIBLE);
                    }

                }
            });


            // set values for custom dialog components - text, image and button
               /* TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Custom dialog Android example.");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                image.setImageResource(R.drawable.image0);
            */
            exitpub = (Button) pub.findViewById(R.id.exitpub);
            exitpub.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    pub.dismiss();

                }
            });

            pub.show();
        }
    }


    private void startTimer2(int duration, int interval, final float[] goal, final ArrayList<Integer> finaltemp) {
        timer2 = new CountDownTimer(duration + 1000, interval)

        {

            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {

                //System.out.println("Timer left in milliseconds= " + leftTimeInMilliseconds);
                ViewGroup.LayoutParams params1 = firstbar.getLayoutParams();
                params1.height = Math.round((x1 + (goal[0] / 60)));
                x1 = x1 + (goal[0] / 60);
                firstbar.setLayoutParams(params1);
                if (y1 <= finaltemp.get(0)) {
                    y1++;
                    firstscore.setText(Integer.toString(y1) + "%");
                }

                //System.out.println("Height for first bar: "+ params.height);

                ViewGroup.LayoutParams params2 = secondbar.getLayoutParams();
                params2.height = Math.round(x2 + (goal[1] / 60));
                x2 = x2 + (goal[1] / 60);
                secondbar.setLayoutParams(params2);
                if (y2 <= finaltemp.get(1)) {
                    y2++;
                    secondscore.setText(Integer.toString(y2) + "%");
                }

                ViewGroup.LayoutParams params3 = thirdbar.getLayoutParams();
                params3.height = Math.round(x3 + (goal[2] / 60));
                x3 = x3 + (goal[2] / 60);
                thirdbar.setLayoutParams(params3);
                if (y3 <= finaltemp.get(2)) {
                    y3++;
                    thirdscore.setText(Integer.toString(y3) + "%");
                }

                ViewGroup.LayoutParams params4 = fourthbar.getLayoutParams();
                params4.height = Math.round(x4 + (goal[3] / 60));
                x4 = x4 + (goal[3] / 60);
                fourthbar.setLayoutParams(params4);
                if (y4 <= finaltemp.get(3)) {
                    y4++;
                    fourthscore.setText(Integer.toString(y4) + "%");
                }
            }

            @Override
            public void onFinish() {
                /*if (pauseflag!=1) {
                    System.out.println("Time finished");
                    controller(game, "5");
                    pauseflag=0;
                }*/

            }
        }.start();
        System.out.println("Starting timer2");

    }


    public void slideUpDown(final View view) {
        if (!isPanelShown) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_up);
            bottomUp.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            hiddenPanel.startAnimation(bottomUp);
            hiddenPanel.setVisibility(View.VISIBLE);
            isPanelShown = true;
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.setVisibility(View.INVISIBLE);
            isPanelShown = false;
        }
    }


    public void changeColor(final Button but, final String answ23, final Boolean right, final Button wrong, final View v) {

        if (right) {
            counter(but).setBackgroundResource(R.drawable.mybuttonanswergreen);
            counter(but).setText(but.getText());
            AlphaAnimation transparency = new AlphaAnimation(1, 0);
            transparency.setDuration(800);
            transparency.setFillAfter(true);
            but.startAnimation(transparency);
            if (stopflag == 0) {
                scoreAnimation(v, answ23);
            } else {
                restartflag = 2;
                v1 = v;
                answ234 = answ23;
            }
            if (musicon) correct.start();
        } else {
            counter(but).setBackgroundResource(R.drawable.mybuttonanswerred);
            counter(wrong).setBackgroundResource(R.drawable.mybuttonanswergreen);
            counter(but).setText(Html.fromHtml(counterstring(but, game)));
            counter(wrong).setText(Html.fromHtml(counterstring(wrong, game)));
            AlphaAnimation transparency = new AlphaAnimation(1, 0);
            transparency.setDuration(800);
            transparency.setFillAfter(true);
            but.startAnimation(transparency);
            wrong.startAnimation(transparency);
            if (stopflag == 0) {
                Handler handler4 = new Handler();
                handler4.postDelayed(new Runnable() {
                    public void run() {
                        if (musicon) wrongmusic.start();
                        controller(game, answ23);
                    }
                }, 2000);

            } else {
                restartflag = 3;
                v1 = v;
                answ234 = answ23;
            }


        }

/*

    ColorDrawable buttonColor = (ColorDrawable) but.getBackground();
    int colorId = buttonColor.getColor();
    final String strColor = String.format("#%06X", 0xFFFFFF & colorId);
    final float[] from = new float[3],
            to =   new float[3];

    Color.colorToHSV(Color.parseColor(strColor), from);
    Color.colorToHSV(Color.parseColor("#6b6b6b"), to);

    ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
    anim.setDuration(800);

    final float[] hsv  = new float[3];
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
        @Override public void onAnimationUpdate(ValueAnimator animation) {
            // Transition along each axis of HSV (hue, saturation, value)
            hsv[0] = from[0] + (to[0] - from[0])*animation.getAnimatedFraction();
            hsv[1] = from[1] + (to[1] - from[1])*animation.getAnimatedFraction();
            hsv[2] = from[2] + (to[2] - from[2])*animation.getAnimatedFraction();

            but.setBackgroundColor(Color.HSVToColor(hsv));
        }

    });
    anim.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            but.setBackgroundColor(Color.parseColor(strColor));
            Color.colorToHSV(Color.parseColor("#6b6b6b"), from);
            if (right) { Color.colorToHSV(Color.parseColor("#62b41b"), to);} else {
                Color.colorToHSV(Color.parseColor("#d8361c"), to);
            }
            ValueAnimator anim2 = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
            anim2.setDuration(500);                              // for 300 ms
            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // Transition along each axis of HSV (hue, saturation, value)
                    hsv[0] = from[0] + (to[0] - from[0]) * animation.getAnimatedFraction();
                    hsv[1] = from[1] + (to[1] - from[1]) * animation.getAnimatedFraction();
                    hsv[2] = from[2] + (to[2] - from[2]) * animation.getAnimatedFraction();

                    but.setBackgroundColor(Color.HSVToColor(hsv));
                }

            });
            anim2.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                   if (right) {
                       scoreAnimation(v, answ23);
                       correct.start();
                   }
                   if (!right){
                       System.out.println("About to call controller from ChangeColor animation");
                       controller(game, answ23);
                   }
                    //but.setBackgroundColor(Color.parseColor(strColor));
                }
            });
            final float[] from1 = new float[3],
                    to1 =   new float[3];
            final float[] hsv1  = new float[3];
            Color.colorToHSV(Color.parseColor("#8a8a8a"), from1);
            Color.colorToHSV(Color.parseColor("#62b41b"), to1);

            ValueAnimator anim3 = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
            anim3.setDuration(500);                              // for 300 ms
            anim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // Transition along each axis of HSV (hue, saturation, value)
                    hsv1[0] = from1[0] + (to1[0] - from1[0]) * animation.getAnimatedFraction();
                    hsv1[1] = from1[1] + (to[1] - from1[1]) * animation.getAnimatedFraction();
                    hsv1[2] = from1[2] + (to1[2] - from1[2]) * animation.getAnimatedFraction();

                    wrong.setBackgroundColor(Color.HSVToColor(hsv1));
                }

            });
           anim2.start();
           if (!right){
           wrongmusic.start();
           anim3.start();}
        }
    });
    anim.start();

*/

    }


    public void scoreAnimation(final View v, String answ23) {

        slideUpDown(v);
        startTimer3(2200, 100, answ23, v);

    }

    private void startTimer3(int duration, int interval, final String answ23, final View v) {
        timer3 = new CountDownTimer(6000, 100)

        {

            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long secspassed = 6000 - leftTimeInMilliseconds;
                if (stopflag == 1) {
                    timer3.cancel();
                    v1 = v;
                    answ234 = answ23;
                    restartflag = 5;
                }
                if ((secspassed > 3000) && (sleepflag < 1)) {
                    sleepflag = 1;
                    moveAnimation();

                }
                if ((secspassed > 3500) && (sleepflag < 2)) {
                    slideUpDown(v);
                    sleepflag = 2;
                }
                if ((secspassed > 5000) && (sleepflag < 3)) {
                    System.out.println("About to call controller from Timer3 onTick");
                    sleepflag = 3;
                    controller(game, answ23);
                }


            }

            @Override
            public void onFinish() {
                /*if (pauseflag!=1) {
                    System.out.println("Time finished");
                    controller(game, "5");
                    pauseflag=0;
                }*/
            }
        }.start();
        System.out.println("Starting timer3");

    }


    public void moveAnimation() {
        System.out.println("Current Question is: " + game.currquest);
        switch (game.currquest) {
            case 1:
                executeAnimation(green100, green_sofar, "#62b41b", "£100", 1000, false);
                break;
            case 2:
                executeAnimation(green200, green_sofar, "#62b41b", "£200", 1000, false);
                break;
            case 3:
                executeAnimation(green300, green_sofar, "#62b41b", "£300", 1000, false);
                break;
            case 4:
                executeAnimation(green500, green_sofar, "#62b41b", "£500", 1000, false);
                break;
            case 5:
                executeAnimation(orange1000, orange_sofar, "#f8870c", "£1000", 1000, false);
                break;
            case 6:
                executeAnimation(orange2000, orange_sofar, "#f8870c", "£2000", 1000, false);
                break;
            case 7:
                executeAnimation(orange3000, orange_sofar, "#f8870c", "£3000", 1000, false);
                break;
            case 8:
                executeAnimation(orange5000, orange_sofar, "#f8870c", "£5000", 1000, false);
                break;
            case 9:
                executeAnimation(red10000, red_sofar, "#d8361c", "£10000", 1000, false);
                break;
            case 10:
                executeAnimation(red20000, red_sofar, "#d8361c", "£20000", 1000, false);
                break;
            case 11:
                executeAnimation(red30000, red_sofar, "#d8361c", "£30000", 1000, false);
                break;
            case 12:
                executeAnimation(red50000, red_sofar, "#d8361c", "£50000", 1000, false);
                break;
            case 13:
                executeAnimation(purple100000, purple_sofar, "#7e4881", "£100000", 1000, false);
                break;
            case 14:
                executeAnimation(purple200000, purple_sofar, "#7e4881", "£200000", 1000, false);
                break;
            case 15:
                executeAnimation(purple300000, purple_sofar, "#7e4881", "£300000", 1000, false);
                break;
            case 16:
                executeAnimation(blue500, purple_sofar, "#7e4881", "£500000", 1000, false);
                break;
            case 17:
                executeAnimation(blue580, blue_sofar, "#1a86d9", "£586509", 1000, false);
                break;
            case 18:
                executeAnimation(blue1000, blue_sofar, "#1a86d9", "£1000000", 1000, false);
                break;
        }

    }

    public void executeAnimation(TextView tv, TextView tv_sofar, String color, String text, int duration, boolean reset) {
        float top = tv.getTop();
        float left = hiddenPanel.getLeft();
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        float gtop = scale * 10 + 0.5f + tv_sofar.getTop();
        float gleft = layoutcontainer.getLeft() + rel1.getWidth() + 5 * scale + 0.5f;
        System.out.println("Top is: " + top + "  Left is:" + left);
        System.out.println("GTop is: " + gtop + "  GLeft is:" + gleft);
        trial = new TextView(Game.this);
        FrameLayout.LayoutParams params25 = new FrameLayout.LayoutParams((int) FrameLayout.LayoutParams.WRAP_CONTENT, (int) FrameLayout.LayoutParams.WRAP_CONTENT);
        params25.leftMargin = (int) left;
        params25.topMargin = (int) top;
        if (!reset) {
            params25.width = red_sofar.getWidth();
            params25.height = red_sofar.getHeight();
        } else {
            params25.width = red_sofar.getWidth() + 10;
            params25.height = red_sofar.getHeight() + 10;
        }
        if (color.equals("#62b41b")) {
            trial.setBackground(getResources().getDrawable(R.drawable.mybuttonanswergreen));
        }
        if (color.equals("#f8870c")) {
            trial.setBackground(getResources().getDrawable(R.drawable.mybuttonanswerorange));
        }
        if (color.equals("#d8361c")) {
            trial.setBackground(getResources().getDrawable(R.drawable.mybuttonanswerred));
        }
        if (color.equals("#7e4881")) {
            trial.setBackground(getResources().getDrawable(R.drawable.mybuttonanswerpurple));
        }
        if (color.equals("#1a86d9")) {
            trial.setBackground(getResources().getDrawable(R.drawable.mybuttonanswerblue));
        }
        if (color.equals("#e1e0d7")) {
            trial.setBackgroundColor(Color.parseColor("#e1e0d7"));
        }
        trial.setText(text);
        trial.setTextColor(Color.parseColor("#ffffff"));
        trial.setTextSize((float) 12);
        trial.setLayoutParams(params25);
        trial.setGravity(Gravity.CENTER);
        framework.addView(trial);
        Animation moveLefttoRight = new TranslateAnimation(0, gleft - left, 0, gtop - top);
        moveLefttoRight.setDuration(duration);
        moveLefttoRight.setFillAfter(true);
        trial.startAnimation(moveLefttoRight);
        //sleepflag=2;


    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    public String getAns(Session game1, String answers1) {
        switch (answers1) {
            case "1":
                return game1.answ1;

            case "2":
                return game1.answ2;

            case "3":
                return game1.answ3;

            case "4":
                return game1.answ4;

            default:
                return game1.answ2;
        }

    }


    public void randomizeAnswers(String[] answers1) {

        Random rgen = new Random();  // Random number generator

        for (int i = 0; i < answers1.length; i++) {
            int randomPosition = rgen.nextInt(answers1.length);
            String temp = answers1[i];
            answers1[i] = answers1[randomPosition];
            answers1[randomPosition] = temp;
        }


    }

    public Button findwrong(Session game1, String[] answers1) {
        int i;
        for (i = 0; i < answers1.length; i++) {
            if (answers1[i].equals(game1.getCa())) {
                switch (i) {
                    case 0:
                        return a1;
                    case 1:
                        return a2;
                    case 2:
                        return a3;
                    case 3:
                        return a4;
                    default:
                        return a4;
                }
            }

        }
        return null;
    }

    public int[] find50(Session game1, String[] answers1) {
        int i, j, k;
        i = 0;
        j = 0;
        for (k = 0; k < answers1.length; k++) {

            if (answers1[k].equals(game1.getCa())) i = k;
            if (answers1[k].equals(game1.getSa())) j = k;
        }
        int[] gam = {i, j};
        return gam;
    }

    public ArrayList<Integer> findpub(Session game1, String[] answers1) {
        int i, j, k, l, h, z, f;
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        z = 0;
        f = 0;
        System.out.println("Answers: [" + answers1[0] + "," + answers1[1] + "," + answers1[2] + "," + answers1[3] + "]");
        System.out.println("Ca=" + game1.getCa() + "  Sa=" + game1.getSa());
        for (h = 0; h < answers1.length; h++) {

            if (answers1[h].equals(game1.getCa())) {
                i = h;
                z = 1;
            }
            if (answers1[h].equals(game1.getSa())) {
                j = h;
                z = 1;
            }
            if ((z == 0) && (f < 1)) {
                k = h;
                f = 1;
            }
            if ((z == 0) && (f < 2)) {
                l = h;
            }
            z = 0;
        }
        ArrayList<Integer> gam = new ArrayList<Integer>();
        gam.add(i);
        gam.add(j);
        gam.add(k);
        gam.add(l);
        return gam;
    }

    public void setFont(ViewGroup group, Typeface font) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof EditText || v instanceof Button) {
                ((TextView) v).setTypeface(font);
            } else if (v instanceof ViewGroup)
                setFont((ViewGroup) v, font);
        }
    }

    public void reset() {
        rh.setVisibility(View.VISIBLE);
        gh.setVisibility(View.VISIBLE);
        bh.setVisibility(View.VISIBLE);
        resetButtonFlags();
        sleepflag = 0;
        bflag = 1;
        pauseflag = 0;
        executeAnimation(green100, green_sofar, "#e1e0d7", "", 10, true);
        executeAnimation(orange1000, orange_sofar, "#e1e0d7", "", 10, true);
        executeAnimation(red10000, red_sofar, "#e1e0d7", "", 10, true);
        executeAnimation(purple100000, purple_sofar, "#e1e0d7", "", 10, true);
        executeAnimation(blue500, blue_sofar, "#e1e0d7", "", 10, true);
        game.help = new ArrayList<String>(Arrays.asList("pub", "tel", "50"));
        game.start();
        System.out.println("HELP=" + help);
        System.out.println("Game reset, help=" + game.help);
        aflag = 0;
        randomizeAnswers(answers);
        controller(game, "0");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Onpaused is called");
        if (gamemusic.isPlaying()) {
            gamemusic.pause();
            musicflag = 1;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopflag = 1;
        System.out.println("Onstop is called");
        if (gamemusic.isPlaying()) {
            gamemusic.pause();
            musicflag = 1;

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (musicflag == 1) {
            if (musicon) gamemusic.start();
            musicflag = 0;
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (restartflag == 1) {
            controller(game, "5");
            pauseflag = 0;
            musicflag = 0;
            donutProgress.setProgress(0);
        }
        if (restartflag == 2) {
            scoreAnimation(v1, answ234);

        }

        if (restartflag == 3) {
            Handler handler4 = new Handler();
            handler4.postDelayed(new Runnable() {
                public void run() {
                    if (musicon) wrongmusic.start();
                    controller(game, answ234);
                }
            }, 2000);
        }
        if (restartflag == 5) {
            startTimer3(2200, 100, answ234, v1);
        }
        restartflag = 0;
        stopflag = 0;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("On Destroy is called");
    }

    Button counter(Button a) {
        if (a.equals(a1)) return a11;
        if (a.equals(a2)) return a22;
        if (a.equals(a3)) return a33;
        else return a44;
    }

    String counterstring(Button a, Session game1) {
        if (a.equals(a1)) {
            String first = "<font color='#000000'>A. </font>";
            String next = "<font color='#ffffff'>" + getAns(game1, answers[0]) + "</font>";
            return first + next;
        }
        if (a.equals(a2)) {
            String first = "<font color='#000000'>B. </font>";
            String next = "<font color='#ffffff'>" + getAns(game1, answers[1]) + "</font>";
            return first + next;
        }

        if (a.equals(a3)) {
            String first = "<font color='#000000'>Γ. </font>";
            String next = "<font color='#ffffff'>" + getAns(game1, answers[2]) + "</font>";
            return first + next;
        } else {
            String first = "<font color='#000000'>Δ. </font>";
            String next = "<font color='#ffffff'>" + getAns(game1, answers[3]) + "</font>";
            return first + next;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void resetButtonFlags() {
        flabutton1 = 1;
        flagbutton2 = 1;
        flagbutton3 = 1;
        flagbutton4 = 1;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}