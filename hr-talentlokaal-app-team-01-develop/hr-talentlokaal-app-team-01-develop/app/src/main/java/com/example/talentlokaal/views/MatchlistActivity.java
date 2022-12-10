package com.example.talentlokaal.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.talentlokaal.R;
import com.example.talentlokaal.controllers.CustomAdapter;
import com.example.talentlokaal.controllers.MatchController;
import com.example.talentlokaal.controllers.MatchMaking;
import com.example.talentlokaal.models.Match;

public class MatchlistActivity extends AppCompatActivity {

    //Elske
    MatchController mc = new MatchController();
    Button btnMatch;
    ListView listView;

    private PopupWindow popWindow;
    private View page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        //Hier wordt de code van Wouter aangeroepen. Het is niet meer mogelijk geweest voor mij om deze code volledig te implementeren
        //Dus de lijst blijft nu nog gevuld worden vanuit de MatchController ipv de database
        //MatchMaking mm = new MatchMaking(Integer.parseInt(extras.getString("id")) ,this);
        setContentView(R.layout.activity_matchlist);

        page = findViewById(android.R.id.content).getRootView();

        btnMatch = (Button)findViewById(R.id.btnMatch);
        listView = findViewById(R.id.lstMatch);
        TextView txtTitle = findViewById(R.id.txtTitle);

        if (extras != null) {

            txtTitle.setText(extras.getString("vacancy"));
        }


        btnMatch.setOnClickListener(view -> fillListView());

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            //Object uit de listview meegeven aan popup
            Match m = (Match)listView.getItemAtPosition(i);
            showPopup(page, m);

        });

    }

    public void showPopup(View v, Match match) {
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate the custom popup layout
        final View popupView = layoutInflater.inflate(R.layout.custompopup, null,false);

        //Automatisch laten invullen van ArrayList op basis van aangeklikte item in listview
        TextView name = popupView.findViewById(R.id.txtName);
        name.setText(match.getName());
        TextView location = popupView.findViewById(R.id.txtLocation);
        location.setText(match.getLocation());
        TextView phone = popupView.findViewById(R.id.txtPhone);
        phone.setText(match.getTelNr());
        TextView email = popupView.findViewById(R.id.txtEmail);
        email.setText(match.geteMail());


        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        // set height depends on the device size
        popWindow = new PopupWindow(popupView, size.x - 80, size.y - 700, true);
        // make it focusable to show the keyboard to enter in `EditText`
        popWindow.setFocusable(true);
        // make it outside touchable to dismiss the popup window
        popWindow.setOutsideTouchable(true);

        // show the popup at bottom of the screen and set some margin at bottom ie,
        popWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView xClose = popupView.findViewById(R.id.txtClose);
        xClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });

    }

    public void fillListView(){

        CustomAdapter myCustomerAdapter = new CustomAdapter(MatchlistActivity.this, mc.match);
        listView.setAdapter(myCustomerAdapter);

    }



}