package com.example.jimmy.test;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    public SoapObject e;
    public Question a;
    ProgressDialog barProgressDialog;
    int num,diff;
    public String btext,banswer1,banswer2,banswer3,banswer4,bcorrect,bsecond;
    public ArrayList<Question> Questions = new ArrayList<Question>();
    public ArrayList<Player> Players = new ArrayList<Player>();
    private Button pame,skor,protaseis;
    private TextView lires,scoremain3,scoremain4;
    private static Context context;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        scoremain4 = (TextView) findViewById(R.id.scoremain4);
        lires = (TextView) findViewById(R.id.lires);
        Typeface fontlight=Typeface.createFromAsset(getAssets(),"Roboto-Bold.ttf");
        lires.setTypeface(fontlight);
        lires.setText(Html.fromHtml(getString(R.string.lires_html)));
        pame = (Button) findViewById(R.id.pame);
        pame.setTypeface(font);
        pame.setText(Html.fromHtml(getString(R.string.pame_html)));
        skor = (Button) findViewById(R.id.skor);
        skor.setText(Html.fromHtml(getString(R.string.skor_html)));
        skor.setTypeface(font);


        pame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                music.stop();
                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);
                finish();
            }
        });


        skor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                music.stop();
                Intent intent = new Intent(MainActivity.this, Scores.class);
                startActivity(intent);
                finish();
            }
        });



        final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Παρακαλώ περιμένετε...",	"Γίνεται ενημέρωση ερωτήσεων...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    SharedPreference sharedPreference=new SharedPreference();
                    SoapProcedure soap = new SoapProcedure();
                    Questions = soap.qresult();
                    //Players= soap.presult();

                    String score = sharedPreference.getValue(context,"OP_PREFS","score");
                    System.out.println("Score at this point:" + score);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreference sharedPreference=new SharedPreference();
                            String score = sharedPreference.getValue(context,"OP_PREFS","score");
                            String s2="<![<FONT COLOR=\"#515045\">"+score+"  Λ</FONT><FONT COLOR=\"#d8361c\">Ι</FONT><FONT COLOR=\"#62b41b\">Ρ</FONT><FONT COLOR=\"#1a86d9\">Ε</FONT><FONT COLOR=\"#515045\">Σ</FONT>";
                            scoremain4.setText(Html.fromHtml(s2));
                        }
                    });

                    String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                    String username = sharedPreference.getValue(context,"OP_PREFS","username");
                    String scoresofar = sharedPreference.getValue(context,"OP_PREFS","scoresofar");
                    soap.submitScore(username,android_id,scoresofar);
                    System.out.println(context.toString());

                    Gson gson = new Gson();
                    String user_json = gson.toJson(Questions);
                    //String user_players=gson.toJson(Players);
                    System.out.println(user_json);
                    //System.out.println(user_players);
                    SharedPreference sharedP=new SharedPreference();
                    sharedP.save(getApplicationContext(),user_json,"OP_PREFS","questionsdownloaded");
                    //sharedP.save(getApplicationContext(),user_players,"OP_PREFS","listofplayers");
                    int temp=0;
                    int i;


                    /*for (i=0; i<Players.size(); i++){
                            if (Players.get(i).deviceid.equals(android_id)){
                            System.out.println("Current Score:"+Players.get(i).getScoreAmount());
                            temp=1;
                            final int finalI = i;
                            runOnUiThread (new Thread(new Runnable() {
                                public void run() {
                                    scoremain4.setText(Players.get(finalI).getScoreAmount());

                                }
                            }));
                        }
                    }
                        if (temp==0) {
                            System.out.println("Curr Score: 0");
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    String s2="<![<FONT COLOR=\"#515045\">10000  Λ</FONT><FONT COLOR=\"#d8361c\">Ι</FONT><FONT COLOR=\"#62b41b\">Ρ</FONT><FONT COLOR=\"#1a86d9\">Ε</FONT><FONT COLOR=\"#515045\">Σ</FONT>";
                                    scoremain4.setText(Html.fromHtml(s2));
                                }
                            }));
                        }*/


                }

               catch (Exception e) {
                   System.out.println(e.getMessage());
                   runOnUiThread(new Runnable() {
                       public void run() {
                           Toast.makeText(getApplicationContext(), "No Response",Toast.LENGTH_LONG).show();
                       }
                   });
                }
                ringProgressDialog.dismiss();
            }
        }).start();


        pame.post(new Runnable() {

            @Override
            public void run() {
                context=getApplicationContext();
                music= MediaPlayer.create(context, R.raw.start);
                music.setLooping(true);
                music.start();

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}