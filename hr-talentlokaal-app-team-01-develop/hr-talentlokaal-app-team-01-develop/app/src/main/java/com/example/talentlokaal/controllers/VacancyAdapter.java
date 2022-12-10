package com.example.talentlokaal.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.talentlokaal.R;
import com.example.talentlokaal.models.Vacancy;

import java.util.ArrayList;


public class VacancyAdapter extends BaseAdapter {

    private ArrayList<Vacancy> Vacancies;
    public VacancyAdapter(ArrayList<Vacancy> vac) {
        this.Vacancies = vac;
    }

    @Override
    public int getCount() {
        return Vacancies.size();
    }

    @Override
    public Object getItem(int i) {
        return Vacancies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        Vacancy curSol = Vacancies.get(i);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(viewGroup.getContext());
            v = vi.inflate(R.layout.list_item, null);
        }
        TextView t1 = v.findViewById(R.id.lvText1);
        TextView t2 = v.findViewById(R.id.lvText2);
        t1.setText(curSol.getJobFunction());
        t2.setText(curSol.getCompanyName());

        return v;

    }
}
