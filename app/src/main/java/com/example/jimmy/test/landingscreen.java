package com.example.jimmy.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by jimmy on 02/02/2015.
 */
public class landingscreen extends Activity {
/*

  */
SharedPreference sharedPreference;
private Button pushname;
private TextView entername;
private EditText editname;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingscreen);
        pushname=(Button)findViewById(R.id.pushname);
        pushname.setVisibility(View.INVISIBLE);
        entername=(TextView)findViewById(R.id.entername);
        entername.setVisibility(View.INVISIBLE);
        editname=(EditText)findViewById(R.id.editname);
        editname.setVisibility(View.INVISIBLE);
        sharedPreference = new SharedPreference();
        final Context context = getApplicationContext();
        String username = sharedPreference.getValue(context,"OP_PREFS","username");
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (username!=null){
            Intent intent = new Intent(landingscreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            entername.setText(Html.fromHtml(getString(R.string.entername_html)));
            pushname.setVisibility(View.VISIBLE);
            editname.setVisibility(View.VISIBLE);
            entername.setVisibility(View.VISIBLE);
            pushname.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SharedPreference sharedP=new SharedPreference();
                    sharedP.save(getApplicationContext(), "0", "OP_PREFS", "score");
                    sharedP.save(getApplicationContext(), editname.getText().toString(), "OP_PREFS", "username");
                    sharedP.save(getApplicationContext(), "1", "OP_PREFS", "musicon");
                    sharedP.save(getApplicationContext(), "1", "OP_PREFS", "syncon");
                    sharedP.save(getApplicationContext(), "1", "OP_PREFS", "firsttime");
                    System.out.println("Value="+sharedP.getValue(getApplicationContext(),"OP_PREFS","musicon"));
                    SoapProcedure soap = new SoapProcedure();
                    String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                    soap.createPlayer(editname.getText().toString(),android_id);
                    Intent intent = new Intent(landingscreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                });
        }


    }

}