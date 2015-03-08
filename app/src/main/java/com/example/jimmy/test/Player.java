package com.example.jimmy.test;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jimmy on 05/03/15.
 */
public class Player implements Serializable {
    public String name;
    public String deviceid;
    public int timesplayed;
    public Date updateddate;
    public int ScoreAmount;

    public Player(String name, String deviceid, int timesplayed, Date updateddate, int scoreAmount) {
        this.name = name;
        this.deviceid = deviceid;
        this.timesplayed = timesplayed;
        this.updateddate = updateddate;
        ScoreAmount = scoreAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public int getTimesplayed() {
        return timesplayed;
    }

    public void setTimesplayed(int timesplayed) {
        this.timesplayed = timesplayed;
    }

    public Date getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    public int getScoreAmount() {
        return ScoreAmount;
    }

    public void setScoreAmount(int scoreAmount) {
        ScoreAmount = scoreAmount;
    }
}