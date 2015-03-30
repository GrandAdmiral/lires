package com.example.jimmy.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by jimmy on 26/03/15.
 */
public class Scores extends Activity {
    private ListView listv;
    ImageButton backbutton;
    TextView finalscore;
    Button today,weekly,alltime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorelayout);
        backbutton=(ImageButton)findViewById(R.id.backbutton);
        finalscore=(TextView) findViewById(R.id.finalscore);
        weekly=(Button)findViewById(R.id.weekly);
        today=(Button)findViewById(R.id.today);
        alltime=(Button)findViewById(R.id.alltime);
        finalscore.setText(Html.fromHtml(getString(R.string.vathmologia_html)));
        listv=(ListView)findViewById(R.id.listView);



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

        Player a= new Player("andrikkos","deviceid",8, 800);
        Player b= new Player("andrikkosbaby","deviceid2",18, 8000);
        Player[] list=new Player[2];
        list[0]=a;
        list[1]=b;

        final MobileArrayAdapter adapter = new MobileArrayAdapter(this, list);
        listv.setAdapter(adapter);

    }

}