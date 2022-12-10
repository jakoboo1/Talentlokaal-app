package com.example.talentlokaal.controllers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.talentlokaal.models.Vacancy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VacancyController {
    //gemaakt Tomas en Yannick
    //hier worden de API endpoints aangesproken en verwerkt

    private Context context;

    public VacancyController(Context context){
        this.context = context;
    }

    public void getVacancyData(int vacId, VolleyVacancyCallback callback) {
        //endpoint aanspreken met userID
        String targetURL = "https://bp4.adainforma.tk/hr_talenlokaal/TeamA/API/vacature/readFromWerkgever/?id=" + vacId;
        ArrayList<Vacancy> respVacancy = new ArrayList<>();
        //volley aanspreken
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        try {
            String url = targetURL;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //waardes uit de responce halen d.m.v. een loop die elk item telt en daarna de waardes in een Vacancy zet
                        for(int i=0; i < response.getJSONArray("vacancies").length(); i++) {
                            String functie = response.getJSONArray("vacancies").getJSONObject(i).getString("Functie");
                            String bedrijfsnaam = response.getJSONArray("vacancies").getJSONObject(i).getString("BedrijfNaam");
                            String id = response.getJSONArray("vacancies").getJSONObject(i).getString("Vacature_Id");
                            respVacancy.add(new Vacancy(functie, bedrijfsnaam, id));
                        }
                        callback.onSuccess(respVacancy);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
