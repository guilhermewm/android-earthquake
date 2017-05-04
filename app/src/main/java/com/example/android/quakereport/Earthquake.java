package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by gmendes on 02/05/2017.
 */

public class Earthquake {
    private double magnitude;
    private String city;
    private long timeInMilliseconds;
    private String url;

    public Earthquake(double magnitude, String city, long timeInMilliseconds, String url){
        this.city = city;
        this.magnitude = magnitude;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getCity() {
        return city;
    }

    public long getTimeInMilliseconds() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
