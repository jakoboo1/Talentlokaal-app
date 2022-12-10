package com.example.talentlokaal.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Vacancy {
    //gemaakt door Tomas
    //class waarin alle waardes worden gezet uit het item van de database
    private String jobFunction;
    private String companyName;

    private Integer id;

    public Vacancy(String func, String comp, String id) {
        this.jobFunction = func;
        this.companyName = comp;
        this.id = Integer.parseInt(id);
    }

    //simpele get en set functies
    public String getJobFunction() {
        return jobFunction;
    }

    public void setJobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
