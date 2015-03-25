package com.example.jimmy.test;

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
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
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
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import android.provider.Settings.Secure;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Game extends ActionBarActivity {


    public ArrayList<String> help = new ArrayList<String>(Arrays.asList("pub", "tel","50"));
    public Boolean right;
    public float x1,x2,x3,x4=0;
    CountDownTimer a,timer2,timer3;
    final Session game=new Session();
    int bflag,aflag,pauseflag,sleepflag,musicflag;
    public Dialog dialog,pub;
    long secs;
    private SharedPreference sharedPreference;
    ProgressBar ab;
    private Handler mHandler = new Handler();
    public ArrayList<Question> Questions;
    private TextView voith,questtext,textTimer,tel,anstel,publ,firstbar,firstwords,secondbar,secondwords,thirdbar,thirdwords,fourthbar,fourthwords;
    private TextView red_sofar,green_sofar,blue_sofar,purple_sofar,orange_sofar,green_500,trial,trial2;
    private TextView green100,green200,green300,green500,orange1000,orange2000,orange3000,orange5000,red10000,red20000,red30000,red50000,purple100000,purple200000,purple300000;
    private TextView blue500,blue580,blue1000;
    private Button a1,a2,a3,a4,exittel,exitpub,gamw;
    private ImageButton gh,rh,bh;
    private DonutProgress donutProgress;
    private RelativeLayout hiddenPanel;
    private RelativeLayout rel1;
    private FrameLayout framework;
    private LinearLayout scores_sofar,layoutcontainer;
    private boolean isPanelShown;
    public String[] answers = {"1","2","3","4"};
    public MediaPlayer answertapped,correct,gamemusic,startmusic,wrongmusic;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        musicflag=0;
        framework=(FrameLayout)findViewById(R.id.framework);
        layoutcontainer=(LinearLayout)findViewById(R.id.layoutContainer);
        rel1=(RelativeLayout)findViewById(R.id.rel1);
        red_sofar=(TextView)findViewById(R.id.red_sofar);
        blue_sofar=(TextView)findViewById(R.id.blue_sofar);
        green_sofar=(TextView)findViewById(R.id.green_sofar);
        orange_sofar=(TextView)findViewById(R.id.orange_sofar);
        purple_sofar=(TextView)findViewById(R.id.purple_sofar);
        scores_sofar=(LinearLayout)findViewById(R.id.scores_sofar);
        hiddenPanel = (RelativeLayout)findViewById(R.id.hidden_panel);
        hiddenPanel.setVisibility(View.INVISIBLE);
        isPanelShown = false;
        green100=(TextView)findViewById(R.id.green_100);
        green200=(TextView)findViewById(R.id.green_200);
        green300=(TextView)findViewById(R.id.green_300);
        green500=(TextView)findViewById(R.id.green_500);
        orange1000=(TextView)findViewById(R.id.orange_1000);
        orange2000=(TextView)findViewById(R.id.orange_2000);
        orange3000=(TextView)findViewById(R.id.orange_3000);
        orange5000=(TextView)findViewById(R.id.orange_5000);
        red10000=(TextView)findViewById(R.id.red_10000);
        red20000=(TextView)findViewById(R.id.red_20000);
        red30000=(TextView)findViewById(R.id.red_30000);
        red50000=(TextView)findViewById(R.id.red_50000);
        purple100000=(TextView)findViewById(R.id.purple_100000);
        purple200000=(TextView)findViewById(R.id.purple_200000);
        purple300000=(TextView)findViewById(R.id.purple_300000);
        blue500=(TextView)findViewById(R.id.blue_500000);
        blue580=(TextView)findViewById(R.id.blue_586509);
        blue1000=(TextView)findViewById(R.id.blue_1000000);
        gamw= (Button) findViewById(R.id.gamwta);

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

     setFont(framework,font);


        sleepflag=0;
        bflag=1;
        pauseflag=0;




        a1=(Button)findViewById(R.id.ans1);
        a1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if (bflag == 0) {
                    bflag = 1;
                    gamemusic.pause();
                    answertapped.start();
                    pauseflag=1;
                    a1.setBackgroundColor(Color.parseColor("#1a86d9"));
                    right = (game.ca.equals(answers[0]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                        }
                    else {
                        wrong=null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a1, answers[0], right, wrong,v);
                        }
                    }, 7000);

                }
            }
        });
        a2=(Button)findViewById(R.id.ans2);
        a2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if (bflag == 0) {
                    bflag=1;
                    gamemusic.pause();
                    answertapped.start();
                    pauseflag=1;
                    a2.setBackgroundColor(Color.parseColor("#1a86d9"));
                    right= (game.ca.equals(answers[1]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                    }
                    else {
                        wrong=null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a2,answers[1],right,wrong,v);
                        }
                    }, 7000);

                }
            }
        });
        a3=(Button)findViewById(R.id.ans3);
        a3.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if (bflag == 0) {
                    bflag=1;
                    gamemusic.pause();
                    answertapped.start();
                    pauseflag=1;
                    a3.setBackgroundColor(Color.parseColor("#1a86d9"));
                    right= (game.ca.equals(answers[2]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                    }
                    else {
                        wrong=null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a3,answers[2],right,wrong,v);
                        }
                    }, 7000);
                }
            }
        });


        a4=(Button)findViewById(R.id.ans4);
        a4.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if (bflag == 0) {
                    bflag = 1;
                    gamemusic.pause();
                    answertapped.start();
                    pauseflag=1;
                    a4.setBackgroundColor(Color.parseColor("#1a86d9"));
                    right= (game.ca.equals(answers[3]));
                    final Button wrong;
                    if (!right) {
                        wrong = findwrong(game, answers);
                    }
                    else {
                        wrong=null;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            changeColor(a4,answers[3],right,wrong,v);
                        }
                    }, 7000);

                }
            }
        });





        gh=(ImageButton)findViewById(R.id.greenhelp);
        gh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 boitheia("green");
            }  });
        rh=(ImageButton)findViewById(R.id.redhelp);
        rh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boitheia("red");
            }  });
        bh=(ImageButton)findViewById(R.id.bluehelp);
        bh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //System.out.println("Button setting pauseflag 1");
                boitheia("blue");
            }  });



        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setMax(45);
        donutProgress.setProgress(45);
        voith = (TextView) findViewById(R.id.voith);
        voith.setText(Html.fromHtml(getString(R.string.voith_html)));


        //textTimer = (TextView) findViewById(R.id.textViewToday);

        Intent intent = this.getIntent();
        sharedPreference = new SharedPreference();
        Context context = getApplicationContext();
        String user_json = sharedPreference.getValue(context,"OP_PREFS","questionsdownloaded");
        String username=sharedPreference.getValue(context,"OP_PREFS","username");
        System.out.println(username);
        Gson gson = new Gson();
        System.out.println(user_json);
        Questions = gson.fromJson(user_json, new TypeToken<ArrayList<Question>>() {
        }.getType());
        questtext = (TextView) findViewById(R.id.questtext);
        String android_id = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        game.setPlayerid(android_id);
        game.setQuestions(Questions);
        game.help = new ArrayList<String>(Arrays.asList("pub", "tel","50"));
        answertapped = MediaPlayer.create(context, R.raw.answertapped);
        correct = MediaPlayer.create(context, R.raw.correct);
        gamemusic=MediaPlayer.create(context,R.raw.game);
        wrongmusic= MediaPlayer.create(context,R.raw.wrong);
        game.start();
        aflag=0;
        randomizeAnswers(answers);
        controller(game, "0");


    }

    public void controller(final Session game,String answer){
        System.out.println("controller("+answer);
        System.out.println("Current Question="+game.getCurrquest()+"   Score="+game.getScore());
        if (answer.equals("0")) {
            //System.out.println("Answer equals 0");
            if (game.getFlag() < 1) {

                System.out.println("Answer equals set the board");
                    randomizeAnswers(answers);
                    setBoard(game);
                    bflag=0;
                    donutProgress.setMax(45);
                    donutProgress.setProgress(45);
                    pauseflag=0;
                    startTimer(45000,1000);
                    System.out.println("Pause flag (controller)= "+pauseflag);

                } else {

                    pauseflag = 1;
                    Context context=getApplicationContext();
                    String score = sharedPreference.getValue(context,"OP_PREFS","score");
                    String scoresofar = sharedPreference.getValue(context,"OP_PREFS","scoresofar");
                    int newscore=Integer.parseInt(score)+game.getScore();
                    String newscoresofar=scoresofar+"0;0;20121212;####0;1;20121212;####"+game.getScore()+";"+game.getCurrquest()+";20121212;####";
                    sharedPreference.save(context,"score","OP_PREFS",Integer.toString(newscore));
                    sharedPreference.save(context,"scoresofar","OP_PREFS",newscoresofar);
                    dialog = new Dialog(Game.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.finishscore);

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dialog.getWindow().getAttributes());
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;
                    int height = size.y;
                    lp.width = (int) (width*0.6);
                    lp.height = (int) (height*0.6);
                    dialog.getWindow().setAttributes(lp);

                    TextView kerdisate= (TextView)dialog.findViewById(R.id.kerdisate);
                    kerdisate.setText(Html.fromHtml(getString(R.string.kerdisate_html)));
                    TextView lires12= (TextView)dialog.findViewById(R.id.lires12);
                    lires12.setText(Html.fromHtml(getString(R.string.lires_html)));
                    TextView finalscore = (TextView) dialog.findViewById(R.id.finalscore);

                    finalscore.setText(Integer.toString(game.getScore()));
                    Button pameksana = (Button)dialog.findViewById(R.id.goagain);
                    Button pisw=(Button)dialog.findViewById(R.id.goback);
                    pameksana.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Button pressed?");
                        dialog.dismiss();

                    }  });
                    pisw.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(Game.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }  });
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(final DialogInterface arg0) {
                        //ADDSCORE
                        reset();


                        }
                    });
                    dialog.show();


                }
            }
            else{
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
                if ((!right)&&(game.getCurrquest()>1)) timer3.cancel();
                System.out.println("About to call controller from controller");
                controller(game, "0");
                }
        }




    public void setBoard(Session game1){
        questtext.setText(game1.questt);
        String first = "<font color='#000000'>A. </font>";
        String next = "<font color='#ffffff'>"+getAns(game1,answers[0])+"</font>";
        a1.setText(Html.fromHtml(first + next));
        first = "<font color='#000000'>B. </font>";
        next = "<font color='#ffffff'>"+getAns(game1,answers[1])+"</font>";
        a2.setText(Html.fromHtml(first + next));
        first = "<font color='#000000'>Γ. </font>";
        next = "<font color='#ffffff'>"+getAns(game1,answers[2])+"</font>";
        a3.setText(Html.fromHtml(first + next));
        first = "<font color='#000000'>Δ. </font>";
        next = "<font color='#ffffff'>"+getAns(game1,answers[3])+"</font>";
        a4.setText(Html.fromHtml(first + next));
        a1.setBackgroundColor(Color.parseColor("#8a8a8a"));
        a2.setBackgroundColor(Color.parseColor("#8a8a8a"));
        a3.setBackgroundColor(Color.parseColor("#8a8a8a"));
        a4.setBackgroundColor(Color.parseColor("#8a8a8a"));
    }





    private void startTimer(int duration,int interval) {
            System.out.println("Creating timer1");
            a = new CountDownTimer(duration, interval)

            {

                // 500 means, onTick function will be called at every 500 milliseconds

                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    long seconds = leftTimeInMilliseconds / 1000;
                    //System.out.println("Time left="+safeLongToInt(seconds));
                    final int barVal= (100) - ((int)(seconds/60*100)+(int)(seconds%60));
                    donutProgress.setProgress(donutProgress.getProgress() - 1);
                    if (!(gamemusic.isPlaying()) && !(bflag==1)&&(musicflag==0)) {
                        /*
                        gamemusic.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            public void onPrepared(MediaPlayer player) {
                          */      gamemusic.start();
                            /*}
                        });
                        Context context =getApplicationContext();
                        //gamemusic=MediaPlayer.create(context,R.raw.game);
                        //gamemusic.reset();
                        gamemusic.release();
                        gamemusic.reset();
                        gamemusic.prepareAsync();*/
                    }
                    if (pauseflag==1) {
                        secs=seconds;
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
                    if (pauseflag!=1) {
                        System.out.println("Time finished");
                        System.out.println("About to call controller from Timer onFinish");
                        right=false;
                        controller(game, "5");
                        pauseflag=0;
                    }
                }
            }.start();
        gamemusic.start();
        System.out.println("Starting timer1");

        }



    public void boitheia(final String color){
    Random randomGenerator = new Random();
    System.out.println("Help="+game.help);
    int index = randomGenerator.nextInt(game.help.size());
    String h = game.help.get(index);
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
                if (color.equals("green")){  gh.setVisibility(View.INVISIBLE);}
                if (color.equals("red")) { rh.setVisibility(View.INVISIBLE);}
                if (color.equals("blue")) { bh.setVisibility(View.INVISIBLE);}

            }
        });


        // set values for custom dialog components - text, image and button
               /* TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Custom dialog Android example.");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                image.setImageResource(R.drawable.image0);
            */      exittel= (Button)dialog.findViewById(R.id.exittel);
        exittel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();

    }
    if (h.equals("50")) {
        int n = find50(game,answers)[0]+1;
        int n1 = find50(game,answers)[1]+1;
        System.out.println("N="+n+"  ,N1="+n1);
        if ((n != 1) && (n1 != 1)) {
            a1.setText("               ");
        }
        if ((n != 2) && (n1 != 2)) {
            a2.setText("               ");
        }
        if ((n != 3) && (n1 != 3)) {
            a3.setText("               ");
        }
        if ((n != 4) && (n1 != 4)) {
            a4.setText("               ");
        }
        game.help.remove("50");
        if (color.equals("green")){  gh.setVisibility(View.INVISIBLE);}
        if (color.equals("red")) { rh.setVisibility(View.INVISIBLE);}
        if (color.equals("blue")) { bh.setVisibility(View.INVISIBLE);}
    }
    if (h.equals("pub")) {

        pauseflag = 1;
        pub = new Dialog(Game.this);
        pub.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Include dialog.xml file
        pub.setContentView(R.layout.pub);
        publ = (TextView) pub.findViewById(R.id.pub);
        publ.setText(Html.fromHtml(getString(R.string.pub_html)));
        ArrayList<Integer> temp= new ArrayList<Integer>();
        temp=game.getPercentages();
        firstbar=(TextView)pub.findViewById(R.id.firstbar);
        firstwords=(TextView)pub.findViewById(R.id.firstwords);
        secondbar=(TextView)pub.findViewById(R.id.secondbar);
        secondwords=(TextView)pub.findViewById(R.id.secondwords);
        thirdbar=(TextView)pub.findViewById(R.id.thirdbar);
        fourthwords=(TextView)pub.findViewById(R.id.fourthwords);
        fourthbar=(TextView)pub.findViewById(R.id.fourthbar);
        final float  scale =getApplicationContext().getResources().getDisplayMetrics().density;
        ArrayList<Integer>finaltemp=new ArrayList<Integer>();
        System.out.println("Findpub: "+findpub(game,answers).toString());
        for (int q=0; q<4; q++) {
                System.out.println("q="+q);
                System.out.println("Index of findpub for current q: " + findpub(game, answers).indexOf(q));
                finaltemp.add(temp.get(findpub(game, answers).indexOf(q)));
                System.out.println("Done for q="+q);
            }
        float[] goal = new float[] { (finaltemp.get(0) * scale + 0.5f),(finaltemp.get(1) * scale + 0.5f),(finaltemp.get(2) * scale + 0.5f),(finaltemp.get(3) * scale + 0.5f)};
        System.out.println("Goal1= "+temp.get(0)+" , Goal2= "+temp.get(1)+" , Goal3= "+temp.get(2)+" , Goal4= "+temp.get(3));
        System.out.println("Goal1= "+goal[0]+" , Goal2= "+goal[1]+" , Goal3= "+goal[2]+" , Goal4= "+goal[3]);
        System.out.println("100dp= "+ 100*scale+0.5f);
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
        startTimer2(3000,50,goal);
         //<TextView android:text="First line\r\nNext line"
        pub.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(final DialogInterface arg0) {
                timer2.cancel();
                x1=0;
                x2=0;
                x3=0;
                x4=0;
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
                if (color.equals("green")){  gh.setVisibility(View.INVISIBLE);}
                if (color.equals("red")) { rh.setVisibility(View.INVISIBLE);}
                if (color.equals("blue")) { bh.setVisibility(View.INVISIBLE);}

            }
        });


        // set values for custom dialog components - text, image and button
               /* TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Custom dialog Android example.");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                image.setImageResource(R.drawable.image0);
            */      exitpub= (Button)pub.findViewById(R.id.exitpub);
        exitpub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pub.dismiss();

            }
        });

        pub.show();
    }
}


    private void startTimer2(int duration,int interval,final float[] goal) {
        timer2 = new CountDownTimer(duration+1000, interval)

        {

            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {

                //System.out.println("Timer left in milliseconds= " + leftTimeInMilliseconds);

                ViewGroup.LayoutParams params1 = firstbar.getLayoutParams();
                params1.height = Math.round((x1+(goal[0]/60)));
                x1=x1+(goal[0]/60);
                firstbar.setLayoutParams(params1);
                //System.out.println("Height for first bar: "+ params.height);

                ViewGroup.LayoutParams params2 = secondbar.getLayoutParams();
                params2.height = Math.round(x2+(goal[1]/60));
                x2=x2+(goal[1]/60);
                secondbar.setLayoutParams(params2);

                ViewGroup.LayoutParams params3 = thirdbar.getLayoutParams();
                params3.height = Math.round(x3+(goal[2]/60));
                x3=x3+(goal[2]/60);
                thirdbar.setLayoutParams(params3);

                ViewGroup.LayoutParams params4 = fourthbar.getLayoutParams();
                params4.height = Math.round(x4+(goal[3]/60));
                x4=x4+(goal[3]/60);
                fourthbar.setLayoutParams(params4);
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
        if(!isPanelShown) {
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
        }
        else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.setVisibility(View.INVISIBLE);
            isPanelShown = false;
        }
    }


public void changeColor(final Button but, final String answ23,final Boolean right, final Button wrong,final View v) {
    ColorDrawable buttonColor = (ColorDrawable) but.getBackground();
    int colorId = buttonColor.getColor();
    final String strColor = String.format("#%06X", 0xFFFFFF & colorId);
    final float[] from = new float[3],
            to =   new float[3];

    Color.colorToHSV(Color.parseColor(strColor), from);   // from white
    Color.colorToHSV(Color.parseColor("#6b6b6b"), to);     // to red

    ValueAnimator anim = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
    anim.setDuration(800);                              // for 300 ms

    final float[] hsv  = new float[3];                  // transition color
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



}



public void scoreAnimation(final View v,String answ23){

    slideUpDown(v);
    startTimer3(2200,100,answ23,v);

}

    private void startTimer3(int duration,int interval,final String answ23,final View v) {
        timer3 = new CountDownTimer(6000, 100)

        {

            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
            long secspassed = 6000-leftTimeInMilliseconds;
                if ((secspassed>3000)&&(sleepflag<1)){
                    sleepflag=1;
                    moveAnimation();

                }
                if ((secspassed>3500)&&(sleepflag<2)) {
                    slideUpDown(v);
                    sleepflag = 2;
                }
                if ((secspassed>5000)&&(sleepflag<3)){
                    System.out.println("About to call controller from Timer3 onTick");
                    sleepflag=3;
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


public void moveAnimation(){
    System.out.println("Current Question is: "+game.currquest);
   switch (game.currquest){
       case 1: executeAnimation(green100,green_sofar,"#62b41b","100 λίρες",1000);
           break;
       case 2: executeAnimation(green200,green_sofar,"#62b41b","200 λίρες",1000);
           break;
       case 3: executeAnimation(green300,green_sofar,"#62b41b","300 λίρες",1000);
           break;
       case 4: executeAnimation(green500,green_sofar,"#62b41b","500 λίρες",1000);
           break;
       case 5: executeAnimation(orange1000,orange_sofar,"#f8870c","1000 λίρες",1000);
           break;
       case 6: executeAnimation(orange2000,orange_sofar,"#f8870c","2000 λίρες",1000);
           break;
       case 7: executeAnimation(orange3000,orange_sofar,"#f8870c","3000 λίρες",1000);
           break;
       case 8: executeAnimation(orange5000,orange_sofar,"#f8870c","5000 λίρες",1000);
           break;
       case 9: executeAnimation(red10000,red_sofar,"#d8361c","10000 λίρες",1000);
           break;
       case 10: executeAnimation(red20000,red_sofar,"#d8361c","20000 λίρες",1000);
           break;
       case 11: executeAnimation(red30000,red_sofar,"#d8361c","30000 λίρες",1000);
           break;
       case 12: executeAnimation(red50000,red_sofar,"#d8361c","50000 λίρες",1000);
           break;
       case 13: executeAnimation(purple100000,purple_sofar,"#7e4881","100000 λίρες",1000);
           break;
       case 14: executeAnimation(purple200000,purple_sofar,"#7e4881","200000 λίρες",1000);
           break;
       case 15: executeAnimation(purple300000,purple_sofar,"#7e4881","300000 λίρες",1000);
           break;
       case 16: executeAnimation(blue500,purple_sofar,"#7e4881","500000 λίρες",1000);
           break;
       case 17: executeAnimation(blue580,blue_sofar,"#1a86d9","586509 λίρες",1000);
           break;
       case 18: executeAnimation(blue1000,blue_sofar,"#1a86d9","1000000 λίρες",1000);
           break;
   }

}

public void executeAnimation(TextView tv,TextView tv_sofar,String color,String text,int duration){
    float top=tv.getTop();
    float left=hiddenPanel.getLeft();
    final float  scale =getApplicationContext().getResources().getDisplayMetrics().density;
    float gtop=scale*10+0.5f+tv_sofar.getTop();
    float gleft=layoutcontainer.getLeft()+rel1.getWidth()+5*scale+0.5f;
    System.out.println("Top is: "+top+"  Left is:"+left );
    System.out.println("GTop is: "+gtop+"  GLeft is:"+gleft );
    trial= new TextView(Game.this);
    FrameLayout.LayoutParams params25=new FrameLayout.LayoutParams((int)FrameLayout.LayoutParams.WRAP_CONTENT, (int)FrameLayout.LayoutParams.WRAP_CONTENT);
    params25.leftMargin=(int)left;
    params25.topMargin=(int)top;
    params25.width=red_sofar.getWidth();
    params25.height=red_sofar.getHeight();
    trial.setBackgroundColor(Color.parseColor(color));
    trial.setText(text);
    trial.setTextColor(Color.parseColor("#ffffff"));
    trial.setTextSize((float) 12);
    trial.setLayoutParams(params25);
    trial.setGravity(Gravity.CENTER);
    framework.addView(trial);
    Animation moveLefttoRight = new TranslateAnimation(0,gleft-left, 0, gtop-top);
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

    public int[] find50(Session game1, String[] answers1){
        int i,j,k;
        i=0; j=0;
        for (k = 0; k < answers1.length; k++) {

            if (answers1[k].equals(game1.getCa())) i=k;
            if (answers1[k].equals(game1.getSa())) j=k;
        }
        int[] gam= {i,j};
        return gam;
    }

    public ArrayList<Integer> findpub(Session game1, String[] answers1){
        int i,j,k,l,h,z,f;
        i=0; j=0; k=0; l=0;z=0;f=0;
        System.out.println("Answers: ["+answers1[0]+","+answers1[1]+","+answers1[2]+","+answers1[3]+"]");
        System.out.println("Ca="+game1.getCa()+"  Sa="+game1.getSa());
        for (h = 0; h < answers1.length; h++) {

            if (answers1[h].equals(game1.getCa())) { i=h; z=1;}
            if (answers1[h].equals(game1.getSa())) {j=h; z=1;}
            if ((z==0)&&(f<1)) {k=h;f=1;}
            if ((z==0)&&(f<2)) {l=h;}
            z=0;
        }
        ArrayList<Integer> gam= new ArrayList<Integer>();
        gam.add(i); gam.add(j); gam.add(k); gam.add(l);
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
public void reset(){
    rh.setVisibility(View.VISIBLE);
    gh.setVisibility(View.VISIBLE);
    bh.setVisibility(View.VISIBLE);
    sleepflag=0;
    bflag=1;
    pauseflag=0;
    executeAnimation(green100,green_sofar,"#e1e0d7","",10);
    executeAnimation(orange1000,orange_sofar,"#e1e0d7","",10);
    executeAnimation(red10000,red_sofar,"#e1e0d7","",10);
    executeAnimation(purple100000,purple_sofar,"#e1e0d7","",10);
    executeAnimation(blue500,blue_sofar,"#e1e0d7","",10);
    game.help = new ArrayList<String>(Arrays.asList("pub", "tel","50"));
    game.start();
    System.out.println("HELP="+help);
    System.out.println("Game reset, help="+game.help);
    aflag=0;
    randomizeAnswers(answers);
    controller(game, "0");
}
@Override
protected void onPause() {
    super.onPause();
    if (gamemusic.isPlaying()) {
        gamemusic.pause();
        musicflag=1;

    }
}
protected void onResume(){
    super.onResume();
    if (musicflag==1) {
        gamemusic.start();
        musicflag=0;
    }
}

}


