package com.example.talentlokaal.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable{

    //Elske
    public String name;
    public String eMail;
    public String telNr;
    public String location;

    public Match(String name, String eMail, String telNr, String location) {
        this.name = name;
        this.eMail = eMail;
        this.telNr = telNr;
        this.location = location;
    }

    protected Match(Parcel in) {
        name = in.readString();
        eMail = in.readString();
        telNr = in.readString();
        location = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(eMail);
        parcel.writeString(telNr);
        parcel.writeString(location);
    }
}
