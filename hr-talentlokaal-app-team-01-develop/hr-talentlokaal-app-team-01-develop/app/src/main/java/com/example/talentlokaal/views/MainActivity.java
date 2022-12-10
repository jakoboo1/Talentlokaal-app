package com.example.talentlokaal.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.talentlokaal.R;
import com.example.talentlokaal.controllers.MatchMaking;

public class MainActivity extends AppCompatActivity {

    private Button contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void Klik(View v){
            TextView txtText = findViewById(R.id.txtTest);
            Button btnKlik = findViewById(R.id.btnKlik);
            Intent intent = new Intent(this, VacancyOverviewActivity.class);
            startActivity(intent);

    }
}