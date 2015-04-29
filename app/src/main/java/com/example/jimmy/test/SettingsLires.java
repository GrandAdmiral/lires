package com.example.jimmy.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jimmy on 29/04/15.
 */
public class SettingsLires extends Activity {
    ImageButton backbutton;
    EditText editusername;
    TextView settingslabel;
    Switch musicswitch,syncswitch;
    Button savesettings;
    Boolean musicon, syncon;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingslayout);
        backbutton = (ImageButton) findViewById(R.id.backbutton1);
        settingslabel = (TextView) findViewById(R.id.settingslabel);
        settingslabel.setText(Html.fromHtml(getString(R.string.settingslabel_html)));
        musicswitch=(Switch)findViewById(R.id.musicswitch);
        syncswitch=(Switch)findViewById(R.id.syncswitch);
        editusername=(EditText)findViewById(R.id.editusername);


        SharedPreference sharedP = new SharedPreference();
        //if (sharedP.getValue(context, "OP_PREFS", "musicon").equals("1")) { musicon=true;}
        //if (sharedP.getValue(context, "OP_PREFS", "syncon").equals("1")) { syncon=true;}
        if (sharedP.getValue(getApplicationContext(),"OP_PREFS","musicon").equals("1")) {musicon=true;} else {musicon=false;}
        if (sharedP.getValue(getApplicationContext(),"OP_PREFS","syncon").equals("1")) {syncon=true;} else {syncon=false;}
        if (syncon) {syncswitch.setChecked(true); } else {syncswitch.setChecked(false);}
        if (musicon) { musicswitch.setChecked(true);} else {musicswitch.setChecked(false);}

        savesettings=(Button)findViewById(R.id.savesettings);

        editusername.setText(sharedP.getValue(getApplicationContext(),"OP_PREFS","username"));
        username=sharedP.getValue(getApplicationContext(),"OP_PREFS","username");

        savesettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicswitch.isChecked()) {
                    musicon = true;
                    SharedPreference sharedPreference= new SharedPreference();
                    sharedPreference.save(getApplicationContext(), "1", "OP_PREFS", "musicon");
                }
                else
                {
                    musicon = false;
                    SharedPreference sharedPreference= new SharedPreference();
                    sharedPreference.save(getApplicationContext(), "0", "OP_PREFS", "musicon");
                }
                if (syncswitch.isChecked()) {
                    syncon = true;
                    SharedPreference sharedPreference= new SharedPreference();
                    sharedPreference.save(getApplicationContext(), "1", "OP_PREFS", "syncon");
                }
                else {
                    syncon = false;
                    SharedPreference sharedPreference = new SharedPreference();
                    sharedPreference.save(getApplicationContext(), "0", "OP_PREFS", "syncon");
                }
                if (!username.equals(editusername.getText().toString())) {
                    if (!isOnline()) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Δεν υπάρχει σύνδεση με το διαδίκτυο.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else {
                        try {
                            SoapProcedure soap = new SoapProcedure();
                            String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                                    Settings.Secure.ANDROID_ID);
                            soap.createPlayer(editusername.getText().toString(), android_id);
                            SharedPreference sharedP = new SharedPreference();
                            sharedP.save(getApplicationContext(), editusername.getText().toString(), "OP_PREFS", "username");
                        } catch (Exception e) {

                        }
                    }
                }



            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingsLires.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}