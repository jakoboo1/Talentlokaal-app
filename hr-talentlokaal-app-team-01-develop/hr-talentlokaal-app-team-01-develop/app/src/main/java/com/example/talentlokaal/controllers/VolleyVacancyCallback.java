package com.example.talentlokaal.controllers;

import com.example.talentlokaal.models.Vacancy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public interface VolleyVacancyCallback {
    void onSuccess(ArrayList<Vacancy> array);
    void onError(String error);
}
