package com.example.jimmy.test;

import android.provider.Settings;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jimmy on 05/03/2015.
 */
public class SoapProcedure {
    private static String SOAP_ACTION1 = "http://tempuri.org/GetAllQuestions";

    private static String SOAP_ACTION2 = "http://tempuri.org/GetAllPlayersWithScores";

    private static String SOAP_ACTION3 = "http://tempuri.org/ScoreSubmissionWithFullScore";

    private static String SOAP_ACTION4 = "http://tempuri.org/CreatePlayer";

    private static String SOAP_ACTION5 = "http://tempuri.org/GetTop100";

    private static String NAMESPACE = "http://tempuri.org/";

    private static String METHOD_NAME1 = "GetAllQuestions";

    private static String METHOD_NAME2 = "GetAllPlayersWithScores";

    private static String METHOD_NAME3 = "ScoreSubmissionWithFullScore";

    private static String METHOD_NAME4 = "CreatePlayer";

    private static String METHOD_NAME5 = "GetTop100";

    private static String URL = "http://test.kypriakeslires.com/WS.asmx?";

    public ArrayList<Question> qresult() {
        ArrayList<Question> Questions = new ArrayList<Question>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
        request.addProperty("username", "jimmys");
        request.addProperty("password", "aek");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        System.out.println(request.toString());
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION1, envelope);
            SoapObject result = null;
            result = (SoapObject) envelope.getResponse();
            if (result != null) {
                int i;
                for (i = 0; i < result.getPropertyCount(); i++) {
                    try {
                        SoapObject e = (SoapObject) result.getProperty(i);
                        System.out.println("Processing property" + i);
                        String btext = e.getProperty("Text").toString();
                        String banswer1 = e.getProperty("Answer1").toString();
                        String banswer2 = e.getProperty("Answer2").toString();
                        String banswer3 = e.getProperty("Answer3").toString();
                        String banswer4 = e.getProperty("Answer4").toString();
                        String bcorrect = e.getProperty("CorrectAnswer").toString();
                        String bsecond = e.getProperty("SecondAnswer").toString();
                        int diff = Integer.parseInt(e.getProperty("Difficulty").toString());
                        int num = Integer.parseInt(e.getProperty("Number").toString());
                        Question a = new Question(btext, banswer1, banswer2, banswer3, banswer4, bcorrect, bsecond, diff, num);
                        Questions.add(a);
                        //System.out.println(a.text);
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {

        }
        return Questions;
    }

    public ArrayList<Player> presult() {
        ArrayList<Player> Players = new ArrayList<Player>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
        request.addProperty("username", "jimmys");
        request.addProperty("password", "aek");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        System.out.println(request.toString());
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION2, envelope);
            SoapObject result = null;
            result = (SoapObject) envelope.getResponse();
            if (result != null) {
                int i;
                for (i = 0; i < result.getPropertyCount(); i++) {
                    try {
                        SoapObject e = (SoapObject) result.getProperty(i);
                        System.out.println("Processing property" + i);
                        String name = e.getProperty("Name").toString();
                        String deviceid = e.getProperty("DeviceId").toString();
                        int timesplayed = Integer.parseInt(e.getProperty("TimesPlayed").toString());
                        int score = Integer.parseInt(e.getProperty("ScoreAmount").toString());
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                        Date date = df.parse(e.getProperty("UpdatedDate").toString());
                        Player a = new Player(name, deviceid, timesplayed, score);
                        Players.add(a);
                        //System.out.println(a.text);
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {

        }
        return Players;
    }


    public void submitScore(String name, String uuid, String scoreSoFar) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME3);
        request.addProperty("username", "jimmys");
        request.addProperty("password", "aek");

        request.addProperty("name", name);
        request.addProperty("uuid", uuid);
        request.addProperty("scoresSoFar", scoreSoFar);
        request.addProperty("fullScore", 0);
        request.addProperty("maxLevel", 0);
        //0;0;20121212;####0;1;20121212;####500;6;20121212;####
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        System.out.println(request.toString());
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            androidHttpTransport.call(SOAP_ACTION3, envelope);
            SoapObject result = null;
            result = (SoapObject) envelope.getResponse();
        } catch (Exception e) {


        }

    }


    public void createPlayer(String name, String uuid) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME4);
        request.addProperty("username", "jimmys");
        request.addProperty("password", "aek");
        request.addProperty("name", name);
        request.addProperty("deviceId", uuid);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        System.out.println(request.toString());
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            androidHttpTransport.call(SOAP_ACTION4, envelope);
            SoapObject result = null;
            result = (SoapObject) envelope.getResponse();
        } catch (Exception e) {


        }

    }

    public ArrayList<ArrayList<Player>> getTop(String uuid){
        ArrayList<Player> alltime = new ArrayList<Player>();
        ArrayList<Player> weekly = new ArrayList<Player>();
        ArrayList<Player> today = new ArrayList<Player>();
        ArrayList<ArrayList<Player>> scores = new ArrayList<ArrayList<Player>>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME5);
        request.addProperty("username", "jimmys");
        request.addProperty("password", "aek");
        request.addProperty("uiid", uuid);
        request.addProperty("date", "");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        System.out.println(request.toString());
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            androidHttpTransport.call(SOAP_ACTION5, envelope);
            SoapObject result = null;
            result = (SoapObject) envelope.getResponse();

            if (result != null) {
                int i;
                System.out.println("Result Property count="+result.getPropertyCount());
                for (i = 0; i < result.getPropertyCount(); i++) {
                    try {

                        SoapObject e = (SoapObject) result.getProperty(i);
                        System.out.println("E count="+e.getPropertyCount());
                        System.out.println("Processing property" + i);

                            for (int j = 0; j < e.getPropertyCount(); j++) {
                                SoapObject f = (SoapObject) e.getProperty(j);
                                System.out.println("F count="+f.getPropertyCount());

                                 //for (int k = 0; k < f.getPropertyCount(); k++) {
                                    //SoapObject ela = (SoapObject) f.getProperty(k);
                                    String name = f.getProperty("Name").toString();
                                    System.out.println("Name="+name);
                                    int score1 = Integer.parseInt(f.getProperty("ScoreAmount").toString());
                                    Player a = new Player(name, score1);
                                    if (i == 0) {
                                        alltime.add(a);
                                        if (j==99) System.out.println("Alltime! "+alltime.toString());
                                    } else if (i == 1) {
                                        System.out.println("Adding in weekly");
                                        weekly.add(a);
                                    } else if (i == 2) {
                                        today.add(a);
                                    }
                                }
                        //    }
                        //System.out.println(a.text);
                    } catch (Exception e) {
                        System.out.println(e);

                    }
                }
                System.out.println("Alltime="+alltime.toString());
                scores.add(alltime);
                scores.add(weekly);
                scores.add(today);

            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return scores;

    }


}
