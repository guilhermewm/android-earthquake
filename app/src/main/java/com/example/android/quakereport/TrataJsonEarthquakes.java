package com.example.android.quakereport;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Created by gmendes on 03/05/2017.
 */

public class TrataJsonEarthquakes extends AsyncTask<Void, Void, Void>{
        private String LOG_TAG;
        private EarthquakeActivity activity;
        private Context applicationContext;
        private ListView earthquakeListView;
        private EarthquakeList earthquakeList;

        public TrataJsonEarthquakes(EarthquakeActivity activity, Context applicationContext, String LOG_TAG, ListView earthquakeListView, EarthquakeList earthquakeList){
            this.activity = activity;
            this.applicationContext = applicationContext;
            this.LOG_TAG = LOG_TAG;
            this.earthquakeListView = earthquakeListView;
            this.earthquakeList = earthquakeList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(activity,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String urlJSON = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";
            String jsonStr = sh.makeServiceCall(urlJSON);

            Log.e(LOG_TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray earthquake = jsonObj.getJSONArray("features");

                    // looping through All Contacts
                    for (int i = 0; i < earthquake.length(); i++) {
                        JSONObject c = earthquake.getJSONObject(i).getJSONObject("properties");

                        //System.out.println(c.getString("mag"));
                        double mag = c.getDouble("mag");
                        String place = c.getString("place");
                        long time = c.getLong("time");
                        String url = c.getString("url");

                        earthquakeList.addEarthquake(new Earthquake(mag, place, time, url));

                    }
                } catch (final JSONException e) {
                    Log.e(LOG_TAG, "Json parsing error: " + e.getMessage());
                }

            } else {
                Log.e(LOG_TAG, "Couldn't get json from server.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(activity, earthquakeList.getListEarthquake());

            earthquakeListView.setAdapter(earthquakeAdapter);
        }
    }

