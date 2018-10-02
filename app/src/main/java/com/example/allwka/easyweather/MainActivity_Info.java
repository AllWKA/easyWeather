package com.example.allwka.easyweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class MainActivity_Info extends AppCompatActivity {

    private List <Citie> citiesInfo ;

    private TextView tempView,rainView,seaView,windView;

    private String name;
    private String temp;
    private String rain;
    private String sea;
    private String wind;

    private String serverInfo = new String();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main__info);

            name = new String();
            temp = new String();
            rain = new String();
            sea = new String();
            wind = new String();

            citiesInfo = new ArrayList <Citie> ();

            tempView = (TextView) findViewById(R.id.temp);
            rainView = (TextView) findViewById(R.id.rain);
            seaView = (TextView) findViewById(R.id.sea);
            windView = (TextView) findViewById(R.id.wind);

            Button update = (Button) findViewById(R.id.update);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateCities();
                }
            });
            Spinner cities = (Spinner) findViewById(R.id.spinnerCities);
            updateCities();
            cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    showInfo(position);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



    }

    private void showInfo(int pos){


        if(citiesInfo.size() != 0){


            tempView.setText(citiesInfo.get(pos).getTemp().toString()+"ºC");
            windView.setText(citiesInfo.get(pos).getWind().toString()+"km/h");
            rainView.setText(citiesInfo.get(pos).getRain().toString()+"%");
            seaView.setText(citiesInfo.get(pos).getSea().toString()+"m");

            ((TextView) findViewById(R.id.actualCitie)).setText(citiesInfo.get(pos).getName()+" - Today");

        }


    }

    public void updateCities(){

        String url = "http://192.168.201.56:40000/api/hello-world";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        serverInfo = response.toString();
                        fillCities();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("bry", error.getMessage());
                    }
        });

        RequestQueue q = Volley.newRequestQueue(getApplicationContext());
        q.add(jsonObjectRequest);

    }

    private void fillCities() {

        String citieAux = "";

        int lastCitie = 0;

        for(int j = 2 ; j < serverInfo.lastIndexOf("]");j++){

            for(lastCitie = serverInfo.indexOf("{",j) ; lastCitie< serverInfo.indexOf("}", lastCitie);lastCitie++){

                if(serverInfo.charAt(lastCitie)!='{'){citieAux += serverInfo.charAt(lastCitie) + "";}

            }

            for(int h = 0 ; h< citieAux.split(",").length;h++){

                switch (h){

                    case 0:
                        name = citieAux.split(",")[h].split(":")[1];
                        break;
                    case 1:
                        temp = citieAux.split(",")[h].split(":")[1];
                        break;
                    case 2:
                        wind = citieAux.split(",")[h].split(":")[1];
                        break;
                    case 3:
                        rain = citieAux.split(",")[h].split(":")[1];
                        break;
                    case 4:
                        sea = citieAux.split(",")[h].split(":")[1];
                        break;


                }



            }
            citiesInfo.add(new Citie(name,temp,wind,rain,sea));
            citieAux = "";
            j = lastCitie;

        }

        Log.i("bry",citieAux);

    }


}