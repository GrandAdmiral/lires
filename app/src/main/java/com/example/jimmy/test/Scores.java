package com.example.jimmy.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        today.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



            }
        });


        alltime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



            }
        });

    }

}