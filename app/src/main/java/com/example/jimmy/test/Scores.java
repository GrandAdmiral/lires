package com.example.jimmy.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
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



            }
        });


        today.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                today.setPressed(true);
                return true;
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            today.setTextColor(Color.parseColor("#1a86d9"));
            today.setPressed(true);


            }
        });


        alltime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



            }
        });
        SoapProcedure soap = new SoapProcedure();
        ArrayList<ArrayList<Player>> scores = new ArrayList<ArrayList<Player>>();
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        scores = soap.getTop(android_id);
        Player[] list = new Player[scores.get(0).size()];
        Player[] list2=new Player[0];
        if (!scores.isEmpty()) {
            int i = 0;
            System.out.println("Scores:" + scores.toString());
            for (Player item : scores.get(0)) {
                list[i] = item;
                i++;
            }
        }
        else{
            Player a=new Player("Not able to connect to the database.",0);
            list2[0]=a;
        }
        Player[] list3;
        if (!scores.isEmpty()){
            list3=list;
        }
        else{
            list3=list2;
        }

            final MobileArrayAdapter adapter = new MobileArrayAdapter(this, list3);

        listv.setAdapter(adapter);

    }

}