package com.example.talentlokaal.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.talentlokaal.R;
import com.example.talentlokaal.models.Match;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    //Elske -- Gemaakt met Robbie
    Context mContext;
    ArrayList<Match> match;

    public CustomAdapter(Context context, ArrayList<Match> match) {
        mContext = context;
        this.match = match;
    }

    @Override
    public int getCount() {
        return match.size();
    }

    @Override
    public Object getItem(int position) {
        return match.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_match, parent, false);
        }
        Match tempMatch = (Match) getItem(position);

        TextView txtName = (TextView)convertView.findViewById(R.id.txtName);
        TextView txtLocation = (TextView)convertView.findViewById(R.id.txtLocation);

        txtName.setText(tempMatch.getName());
        txtLocation.setText(tempMatch.getLocation());

        return convertView;
    }
}
