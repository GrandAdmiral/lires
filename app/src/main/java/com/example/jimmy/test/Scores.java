package com.example.jimmy.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jimmy on 26/03/15.
 */
public class Scores extends Activity {
    private ListView listv;
    ImageButton backbutton;
    TextView thesi,scoregmt,finalscore;
    Button today,weekly,alltime;
    private static Context context;
    Player[] list,list1,list2,list3,weeklist,todaylist,lastlist;
    SoapObject malakia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorelayout);
        backbutton=(ImageButton)findViewById(R.id.backbutton);
        finalscore=(TextView) findViewById(R.id.finalscore);
        thesi=(TextView)findViewById(R.id.thesigamwta);
        scoregmt=(TextView)findViewById(R.id.scoremain44);
        weekly=(Button)findViewById(R.id.weekly);
        today=(Button)findViewById(R.id.today);
        alltime=(Button)findViewById(R.id.alltime);
        finalscore.setText(Html.fromHtml(getString(R.string.vathmologia_html)));
        listv=(ListView)findViewById(R.id.listView);


        SharedPreference sharedPreference=new SharedPreference();
        String score = sharedPreference.getValue(getApplicationContext(),"OP_PREFS","score");
        String s2="<![<FONT COLOR=\"#515045\">"+score+"  Λ</FONT><FONT COLOR=\"#d8361c\">Ι</FONT><FONT COLOR=\"#62b41b\">Ρ</FONT><FONT COLOR=\"#1a86d9\">Ε</FONT><FONT COLOR=\"#515045\">Σ</FONT>";
        scoregmt.setText(Html.fromHtml(s2));

        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Scores.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        weekly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final MobileArrayAdapter adapter = new MobileArrayAdapter(getApplicationContext(), weeklist);
                listv.setAdapter(adapter);

            }
        });


        /*today.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                today.setPressed(true);
                return true;
            }
        });
*/
        today.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //today.setTextColor(Color.parseColor("#1a86d9"));
                //today.setPressed(true);
                //System.out.println("Today:"+todaylist[0].getName().toString());
                final MobileArrayAdapter adapter = new MobileArrayAdapter(getApplicationContext(), todaylist);
                listv.setAdapter(adapter);


            }
        });


        alltime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final MobileArrayAdapter adapter = new MobileArrayAdapter(getApplicationContext(), list3);
                listv.setAdapter(adapter);

            }
        });
        final ProgressDialog ringProgressDialog = ProgressDialog.show(Scores.this, "Παρακαλώ περιμένετε...", " Συγχρονισμός Βαθμολογίας...", true);
        ringProgressDialog.setCancelable(true);
        ringProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                final ProgressDialog ringProgressDialog1 = ProgressDialog.show(Scores.this, "Παρακαλώ περιμένετε...", "Γίνεται λήψη βαθμολογιών...", true);
                ringProgressDialog.setCancelable(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SoapProcedure soap = new SoapProcedure();
                        ArrayList<ArrayList<Player>> scores = new ArrayList<ArrayList<Player>>();
                        SharedPreference sharedPreference = new SharedPreference();
                        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                        String score = sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "score");
                        System.out.println("Score at this point:" + score);
                        String username = sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "username");
                        String scoresofar = sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "scoresofar");
                        System.out.println("Score Submission: " + scoresofar);
                        try {
                            scores = soap.getTop(android_id);
                            System.out.println(scores.toString());
                            list = new Player[scores.get(0).size()];
                            weeklist = new Player[scores.get(1).size()];
                            todaylist = new Player[scores.get(2).size()];
                            lastlist = new Player[scores.get(3).size()];
                            list2 = new Player[0];
                            if (!scores.isEmpty()) {
                                int i = 0;
                                System.out.println("Scores:" + scores.toString());
                                for (Player item : scores.get(0)) {
                                    list[i] = item;
                                    i++;
                                }
                                i = 0;
                                for (Player item : scores.get(1)) {
                                    weeklist[i] = item;
                                    i++;
                                }
                                i = 0;
                                for (Player item : scores.get(2)) {
                                    todaylist[i] = item;
                                    i++;
                                }
                                i = 0;
                                for (Player item : scores.get(3)) {
                                    lastlist[i] = item;
                                    i++;
                                }
                            } else {
                                Player a = new Player("Not able to connect to the database.", 0, 0);
                                list2[0] = a;
                            }
                            if (!scores.isEmpty()) {
                                list3 = list;
                                //Player cure = new Player(lastlist[0].getName(),lastlist[0].getScoreAmount());
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        System.out.println(lastlist.toString());
                                        thesi.setText("ΘΕΣΗ: " + Integer.toString(lastlist[0].getPosition()));
                                        final MobileArrayAdapter adapter = new MobileArrayAdapter(getApplicationContext(), list3);
                                        listv.setAdapter(adapter);

                                    }
                                });
                            } else {
                                list3 = list2;
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        final MobileArrayAdapter adapter = new MobileArrayAdapter(getApplicationContext(), list3);
                                        listv.setAdapter(adapter);
                                    }
                                });
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());

                        }
                        ringProgressDialog1.dismiss();
                    }
                }).start();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
        SoapProcedure soap = new SoapProcedure();
        ArrayList<ArrayList<Player>> scores = new ArrayList<ArrayList<Player>>();
        SharedPreference sharedPreference = new SharedPreference();
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        String score = sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "score");
        System.out.println("Score at this point:" + score);
        String username = sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "username");
        String scoresofar = sharedPreference.getValue(getApplicationContext(), "OP_PREFS", "scoresofar");
        System.out.println("Score Submission: "+scoresofar);
        try {
            malakia=soap.submitScore(username, android_id, scoresofar);
        }
        catch (Exception e) {
            System.out.println("Score submission failed:" + e);
        }
        System.out.println("Malakia:"+malakia.toString());
        ringProgressDialog.dismiss();
            }
        }).start();







    }

}