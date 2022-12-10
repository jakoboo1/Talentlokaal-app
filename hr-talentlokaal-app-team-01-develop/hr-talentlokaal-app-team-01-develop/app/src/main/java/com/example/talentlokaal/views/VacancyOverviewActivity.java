package com.example.talentlokaal.views;

import androidx.appcompat.app.AppCompatActivity;
import com.example.talentlokaal.R;
import com.example.talentlokaal.controllers.VacancyAdapter;
import com.example.talentlokaal.controllers.VacancyController;
import com.example.talentlokaal.controllers.VolleyVacancyCallback;
import com.example.talentlokaal.models.Vacancy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class VacancyOverviewActivity extends AppCompatActivity {
//gemaakt door Tomas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_overview);

        ListView lvVacancies = findViewById(R.id.lvJobs);

        //vacancycontroller aanspreken voor data ophalen met volley
        VacancyController vacControl = new VacancyController(getApplicationContext());

        //app vasthangen tot data is geladen
        vacControl.getVacancyData(77, new VolleyVacancyCallback() {
            @Override
            public void onSuccess(ArrayList<Vacancy> array) {

                VacancyAdapter adapt = new VacancyAdapter(array);

                lvVacancies.setAdapter(adapt);
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });
        //Click event op elk item in de list zetten
        //Geeft de waardes mee die het volgende scherm nodig heeft
        lvVacancies.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Vacancy o = (Vacancy) lvVacancies.getItemAtPosition(i);
                System.out.println(o.getJobFunction());
                Intent test = new Intent(VacancyOverviewActivity.this, MatchlistActivity.class);
                test.putExtra("id", o.getId());
                test.putExtra("vacancy", o.getJobFunction());
                startActivity(test);
            }
        });


    }
}