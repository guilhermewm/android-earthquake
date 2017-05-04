/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthquakeList earthquakeList = new EarthquakeList();

        // Cria um novo adapter que pega a lista de earthquakes como entrada
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakeList.getListEarthquake());

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setAdapter(adapter);

        TrataJsonEarthquakes trata_json_earthquakes = new TrataJsonEarthquakes(EarthquakeActivity.this, getApplicationContext(), LOG_TAG, earthquakeListView, earthquakeList);
        trata_json_earthquakes.execute();


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Achar o terremoto atual que foi clicado
                Earthquake currentEarthquake = adapter.getItem(position);

                // Converte o URL String em um objeto URI (para passar no construtor de Intent)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Cria um novo intent para visualizar a URI do earthquake
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Envia o intent para lançar uma nova activity
                startActivity(websiteIntent);
            }
        });


    }


}
