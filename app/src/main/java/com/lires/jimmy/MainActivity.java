package com.lires.jimmy;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ImageButton;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends ActionBarActivity {
    public SoapObject e;
    public Question a;
    ProgressDialog barProgressDialog;
    int num, diff;
    public String btext, banswer1, banswer2, banswer3, banswer4, bcorrect, bsecond;
    public ArrayList<Question> Questions = new ArrayList<Question>();
    public ArrayList<Player> Players = new ArrayList<Player>();
    private Button pame;
    private ImageButton skor, togglesound, togglesync, feedback, settings;
    private TextView lires, scoremain3, scoremain4, scoremain444;
    private static Context context;
    private MediaPlayer music;
    private boolean musicon, syncon, firsttime, justrun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        SharedPreference sharedP = new SharedPreference();
        //if (sharedP.getValue(context, "OP_PREFS", "musicon").equals("1")) { musicon=true;}
        //if (sharedP.getValue(context, "OP_PREFS", "syncon").equals("1")) { syncon=true;}
        if (sharedP.getValue(getApplicationContext(), "OP_PREFS", "musicon").equals("1")) {
            musicon = true;
        } else {
            musicon = false;
        }
        if (sharedP.getValue(getApplicationContext(), "OP_PREFS", "firsttime").equals("1")) {
            firsttime = true;
        } else {
            firsttime = false;
        }
        if (sharedP.getValue(getApplicationContext(), "OP_PREFS", "syncon").equals("1")) {
            syncon = true;
        } else {
            syncon = false;
        }
        if (sharedP.getValue(getApplicationContext(), "OP_PREFS", "justrun").equals("1")) {
            justrun = true;
        } else {
            justrun = false;
        }
        scoremain4 = (TextView) findViewById(R.id.scoremain4);
        scoremain444 = (TextView) findViewById(R.id.scoremain444);
        lires = (TextView) findViewById(R.id.lires);
        Typeface fontlight = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");
        lires.setTypeface(fontlight);
        lires.setText(Html.fromHtml(getString(R.string.lires_html)));
        pame = (Button) findViewById(R.id.pame);
        pame.setTypeface(font);
        pame.setText(Html.fromHtml(getString(R.string.pame_html)));
        skor = (ImageButton) findViewById(R.id.gotoscore);
        togglesound = (ImageButton) findViewById(R.id.togglesound);
        togglesync = (ImageButton) findViewById(R.id.togglesync);
        settings = (ImageButton) findViewById(R.id.gotosettings);
        feedback = (ImageButton) findViewById(R.id.gotofeedback);
        if (!musicon) togglesound.setAlpha(0.5f);
        if (!syncon) togglesync.setAlpha(0.5f);
        String android_id1 = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        System.out.println("Device ID is: " + android_id1);


        pame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreference sharedPreference = new SharedPreference();
                String user_json = sharedPreference.getValue(context, "OP_PREFS", "questionsdownloaded");
                System.out.println("Checking Questions" + user_json);
                System.out.println("Userjson=" + user_json);
                if ((user_json != null) && (user_json != "[]") && (user_json != "") && (!user_json.isEmpty()) && (!user_json.equals("[]"))) {

                    music.stop();
                    Intent intent = new Intent(MainActivity.this, Game.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Δεν έχουν αποθηκευτεί οι ερωτήσεις. Μάλλον δεν υπάρχει σύνδεση με το διαδίκτυο.", Toast.LENGTH_LONG).show();

                }

            }
        });


        skor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((isOnline())) {
                    music.stop();
                    Intent intent = new Intent(MainActivity.this, Scores.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Δεν υπάρχει σύνδεση με το διαδίκτυο.", Toast.LENGTH_LONG).show();
                }
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((isOnline())) {
                    music.stop();
                    Intent intent = new Intent(MainActivity.this, Suggestions.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Δεν υπάρχει σύνδεση με το διαδίκτυο.", Toast.LENGTH_LONG).show();
                }
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                music.stop();
                Intent intent = new Intent(MainActivity.this, SettingsLires.class);
                startActivity(intent);
                finish();
            }
        });

        togglesound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dothemusic();
            }
        });
        togglesync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dothesync();
            }
        });
        SharedPreference sharedPreference = new SharedPreference();
        int score = Integer.parseInt(sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "score"));
        String username = sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "username");
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        String su = "<![<FONT COLOR=\"#515045\">" + username + "</FONT>";
        String s2 = "<![<FONT COLOR=\"#515045\">" + String.valueOf(formatter.format(score)) + "  Λ</FONT><FONT COLOR=\"#d8361c\">Ι</FONT><FONT COLOR=\"#62b41b\">Ρ</FONT><FONT COLOR=\"#1a86d9\">Ε</FONT><FONT COLOR=\"#515045\">Σ</FONT>";
        scoremain4.setText(Html.fromHtml(s2));
        scoremain444.setText(Html.fromHtml(su));

        System.out.println("Is online?" + isOnline());
        if ((isOnline())) {
            if (((syncon) || (firsttime)) && (justrun)) {
                final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Παρακαλώ περιμένετε...", "Γίνεται ενημέρωση ερωτήσεων...", true);
                ringProgressDialog.setCancelable(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SharedPreference sharedPreference = new SharedPreference();
                            sharedPreference.save(getApplicationContext(), "0", "OP_PREFS", "justrun");
                            SoapProcedure soap = new SoapProcedure();
                            Questions = soap.qresult();
                            //Players= soap.presult();
                            Gson gson = new Gson();
                            String user_json = gson.toJson(Questions);
                            //String user_players=gson.toJson(Players);
                            System.out.println(user_json);
                            //System.out.println(user_players);
                            SharedPreference sharedP = new SharedPreference();
                            sharedP.save(getApplicationContext(), user_json, "OP_PREFS", "questionsdownloaded");
                            //sharedP.save(getApplicationContext(),user_players,"OP_PREFS","listofplayers");
                            int temp = 0;
                            int i;
                            firsttime = false;
                            sharedP.save(getApplicationContext(), "0", "OP_PREFS", "firsttime");

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


                        } catch (Exception e) {
                            System.out.println(e.getMessage());

                        }
                        ringProgressDialog.dismiss();
                    }
                }).start();
            } else if (justrun) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        System.out.println("Syncon:" + syncon);
                        System.out.println("Firsttime:" + firsttime);
                        Toast.makeText(getApplicationContext(), "Ο συγχρονισμός ερωτήσεων είναι απενεργοποιημένος.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "Δεν υπάρχει σύνδεση με το διαδίκτυο.", Toast.LENGTH_LONG).show();
                }
            });
        }

        pame.post(new Runnable() {

            @Override
            public void run() {
                context = getApplicationContext();
                music = MediaPlayer.create(context, R.raw.start);
                music.setLooping(true);
                if (musicon) music.start();

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void dothemusic() {
        if (musicon) {
            //togglesound.getBackground().setAlpha(252);
            SharedPreference sharedPreference = new SharedPreference();
            sharedPreference.save(getApplicationContext(), "0", "OP_PREFS", "musicon");
            togglesound.setAlpha(0.5f);
            musicon = false;
            music.stop();
        } else {
            SharedPreference sharedPreference = new SharedPreference();
            sharedPreference.save(getApplicationContext(), "1", "OP_PREFS", "musicon");
            togglesound.setAlpha(1f);
            musicon = true;
            context = getApplicationContext();
            music = new MediaPlayer();
            music = MediaPlayer.create(context, R.raw.start);
            music.setLooping(true);
            music.start();
        }
    }

    public void dothesync() {
        System.out.println("Do the sync");
        if (syncon) {
            //togglesound.getBackground().setAlpha(252);
            SharedPreference sharedPreference = new SharedPreference();
            sharedPreference.save(getApplicationContext(), "0", "OP_PREFS", "syncon");
            togglesync.setAlpha(0.5f);
            syncon = false;
        } else {
            SharedPreference sharedPreference = new SharedPreference();
            sharedPreference.save(getApplicationContext(), "1", "OP_PREFS", "syncon");
            togglesync.setAlpha(1f);
            syncon = true;
        }
    }

}