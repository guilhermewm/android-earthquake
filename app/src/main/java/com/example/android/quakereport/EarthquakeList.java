package com.example.android.quakereport;

import java.util.ArrayList;

/**
 * Created by gmendes on 04/05/2017.
 */

public class EarthquakeList {
    private ArrayList<Earthquake> earthquakeList;

    public EarthquakeList(){
        this.earthquakeList = new ArrayList<>();
    }

    public void addEarthquake(Earthquake earthquake){
        earthquakeList.add(earthquake);
    }

    public ArrayList<Earthquake> getListEarthquake(){
        return earthquakeList;
    }

}
