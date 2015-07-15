package com.lires.jimmy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

/**
 * Created by jimmy on 02/02/2015.
 */
public class Suggestions extends Activity {
    /*

      */
    SharedPreference sharedPreference;
    private Button pushsuggestion;
    private ImageButton sugback;
    private TextView suggestionslabel;
    private EditText editsuggestion;
    public SoapObject malakia;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestions);
        pushsuggestion = (Button) findViewById(R.id.pushsuggestion);
        sugback = (ImageButton) findViewById(R.id.backbuttonsug);
        suggestionslabel = (TextView) findViewById(R.id.suggestionslabel);
        editsuggestion = (EditText) findViewById(R.id.editsuggestion);
        sharedPreference = new SharedPreference();
        final Context context = getApplicationContext();
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        suggestionslabel.setText(Html.fromHtml(getString(R.string.suggestions_html)));

        sugback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Suggestions.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        pushsuggestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ProgressDialog ringProgressDialog = ProgressDialog.show(Suggestions.this, "Παρακαλώ περιμένετε...", " Αποστολή Πρότασης...", true);
                ringProgressDialog.setCancelable(true);
                ringProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(getApplicationContext(), "Η καταχώρηση ολοκληρώθηκε επιτυχώς.", Toast.LENGTH_LONG).show();
                        Handler handler4 = new Handler();
                        handler4.postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(Suggestions.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);


                    }
                });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SoapProcedure soap = new SoapProcedure();
                        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                        try {
                            malakia = soap.submitSuggestion(editsuggestion.getText().toString(), android_id);
                        } catch (Exception e) {
                            System.out.println("Score submission failed:" + e);
                        }
                        System.out.println("Malakia:" + malakia.toString());
                        ringProgressDialog.dismiss();
                    }
                }).start();


            }
        });

    }

}