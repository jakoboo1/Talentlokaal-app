package com.example.talentlokaal.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.talentlokaal.R;
import com.example.talentlokaal.controllers.MatchMaking;
import com.example.talentlokaal.models.Match;

public class MatchPopUp extends AppCompatActivity {

    //Elske
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchlist);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //ophalen van parcelable
        MatchMaking m = intent.getParcelableExtra("o");

    }

}