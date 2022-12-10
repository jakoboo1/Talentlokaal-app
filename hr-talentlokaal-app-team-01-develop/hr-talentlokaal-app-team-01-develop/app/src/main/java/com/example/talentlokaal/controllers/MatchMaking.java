package com.example.talentlokaal.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MatchMaking {

    //Wouter
    private ArrayList<String> jobOfferSkills;
    private ArrayList<ArrayList> userSkills;
    private ArrayList<ArrayList<String>> userCulture;
    private ArrayList<ArrayList<String>> userCultureParsed;
    private ArrayList<String> jobOfferCulture;
    private Context context;
    private ArrayList<String> users;
    private String idEmployer;
    private ArrayList<ArrayList<String>> matches;
    private ArrayList<ArrayList<String>> matchClear;

    public ArrayList<ArrayList<String>> getMatchList() {
        //Hier geeft de matches terug
        return matchClear ;
    }

    private ArrayList<ArrayList<String>> matchPercent;

    public MatchMaking(int vacId, Context context){
        //Alle arraylisten declaren om ze later te gebruiken
        this.context = context;
        this.jobOfferSkills = new ArrayList<>();
        this.users = new ArrayList<>();
        this.userSkills = new ArrayList<>();
        this.userCulture = new ArrayList<>();
        this.jobOfferCulture = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.matchPercent = new ArrayList<>();
        this.userCultureParsed = new ArrayList<>();
        this.matchClear = new ArrayList<>();

        //eerste matchfuntie aanroepen
        match(vacId);

    }

    // JobOfferSection

    // Joboffer Skill selection

    public void getJobOfferSkillsAPI(int id, VolleyCallBack callBack){
        String url = "https://bp4.adainforma.tk/hr_talenlokaal/TeamA/API/vacature_kwaliteiten/read/?id="+id;

        RequestQueue qeue  = Volley.newRequestQueue(this.context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            // Ons repsonse

            JSONObject object = response;
            try {
                //Define JsonArray met de response
                JSONArray array = object.getJSONArray("data");
                //parse de Json in de array
                setJobOfferSkills(array);
                callBack.onSuccess();
            } catch (JSONException e) {
                callBack.onError(e.toString());
            }
        }, error -> {
            // Ons error
            callBack.onError(error.toString());
        });
        qeue.add(jsonObjectRequest);

    }
    private void setJobOfferSkills(JSONArray array){
        try {
            //de resultaten van de jobofferarry worden in een array geduwd om de kwaliteiten te ordenen
            for (int i = 0; i < array.length(); i++) {
                JSONObject unitArray = array.getJSONObject(i);
                //selecteren van de kwaliteit
                String skill = unitArray.getString("Kwaliteit_id");
                //toevoegen aan de global arraylist
                this.jobOfferSkills.add(skill);
            }
        }
        catch(Exception e){
            Log.e("APIErrr setJobSkill: ", e.toString());
        }
    }

    public void getJobCultureAPI(String vacId, VolleyCallBack callback){
        //job kwaliteiten ophalen
        String url = "https://bp4.adainforma.tk/hr_talenlokaal/TeamA/API/gebruiker_vragen/read/?id="+vacId;

        RequestQueue qeue = Volley.newRequestQueue(this.context);
        //request uitvoeren
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            // Ons response
            JSONObject object = response;
            try {
                //Define JsonArray met de response
                JSONArray array = object.getJSONArray("data");
                //functie aanroepen om hem in een array te zetten
                getJobCulture(array);
                //jobculture is gelukt dus terug naar de functie
                callback.onSuccess();

            } catch (JSONException e) {
                callback.onError(e.toString());
            }
        }, error -> {
            // Ons error
            callback.onError(error.toString());
        });
        qeue.add(jsonObjectRequest);

    }

    public void getJobCulture(JSONArray array){
            //Elke regel wordt doorlopen en in een array gezet
            for (int i = 0; i < array.length(); i++) {
                JSONObject unitArray = null;
                try {
                    //hier pakt die de waarde
                    unitArray = array.getJSONObject(i);
                    String skill = unitArray.getString("Waarde");
                    //hier zet die hem in de global araylist
                    this.jobOfferCulture.add(skill);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    }

    //User section

    //Ophalen van alle users
    public void getUsers(VolleyCallBack callBack) {
        //alle gebruikers ophalen
        String url = "https://bp4.adainforma.tk/hr_talenlokaal/TeamA/API/gebruiker/read/";
        RequestQueue qeue = Volley.newRequestQueue(this.context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            // Ons repsonse
            JSONObject object = response;
            try {
                //Define JsonArray met de response
                JSONArray array = object.getJSONArray("data");
                //parse de Json in de array
                this.users = setArrayUser(array, new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        callBack.onSuccess();
                    }
                    @Override
                    public void onError(String error) {
                        callBack.onError(error.toString());
                    }
                });
            } catch (JSONException e) {
                callBack.onError(e.toString());
            }
        }, error -> {
            // Ons error
            callBack.onError(error.toString());
        });
        qeue.add(jsonObjectRequest);
    }

    //Alle users in een array zetten (hun Id dan)
    public ArrayList<String> setArrayUser(JSONArray array, VolleyCallBack callBack){
        ArrayList<String> users = new ArrayList<>();
        try{
            //alle resultaten inladen
            for (int i = 0; i < array.length(); i++) {
                JSONObject unitArray = array.getJSONObject(i);
                //Id oppikken
                String user = unitArray.getString("Id");
                //user toevoegen
                users.add(user);
            }
            callBack.onSuccess();
        } catch(JSONException e) {
            callBack.onError(e.toString());
        }
        return users;
    }

    //De users skills doorlopen/ophalen
    public void setUserSkills(VolleyMatchCallBack callback){

        int userCount = this.users.size();
        //alle users doorlopen
        for (int i = 0; i < this.users.size(); i++){
            int count  = i;
            //alle users doorlopen en dan de skills ophalen
            getUserSkills(this.users.get(i), new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    //  if(count == usereCount) {
                    callback.onSuccess(count);

                    // }
                }
                @Override
                public void onError(String error) {
                    callback.onError(error);
                }
            });
        }
    }

    //Alle users hun skills ophalen
    public void getUserSkills(String userId, VolleyCallBack callback){
        //skills ophalen
        String url = "https://bp4.adainforma.tk/hr_talenlokaal/TeamA/API/matchmaking/read/?id="+userId;

        RequestQueue qeue = Volley.newRequestQueue(this.context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            // Ons repsonse
            JSONObject object = response;
            try {
                //Define JsonArray met de response
                JSONArray array = object.getJSONArray("data");
                //parse de Json in de array
                if(array.length() != 0) {
                    //skills in een array zetten met het userid erbij
                    setArrayUserSkill(array, userId, new VolleyCallBack() {
                        @Override
                        public void onSuccess() {
                            callback.onSuccess();
                        }
                        @Override
                        public void onError(String error) {
                        }
                    });

                }
                else{
                    callback.onSuccess();
                }
            } catch (JSONException e) {
                callback.onError(e.toString());
            }

        }, error -> {
            // Ons error
        });
        qeue.add(jsonObjectRequest);
    }

    //Alle skills in een array zetten
    public void setArrayUserSkill(JSONArray array, String userId, VolleyCallBack callBack){
        try{
            //Alle userskills in een aparte array zetten
            ArrayList<String> user = new ArrayList<>();
            //userId toevoegen voor de herkenning in de array
            user.add(userId);
            for (int i = 0; i < array.length(); i++) {
                JSONObject unitArray = array.getJSONObject(i);
                //kwaliteit oppikken
                String skill = unitArray.getString("Kwaliteit");
                //kwaliteit toevoegen
                user.add(skill);
            }
            if(!this.userSkills.contains(user)){
                //Als die al in de array zit voegt die hem niet toe, hiet wel dus
                this.userSkills.add(user);
                callBack.onSuccess();
            }
            else{
                callBack.onSuccess();
            }
        } catch(JSONException e) {
            callBack.onError(e.toString());
        }
    }

    public void getEmployerId(int vacId, VolleyCallBack callback){
        //Gebruiker die de vacature heeft aangemaakt, zijn id ophalen
        String url = "https://bp4.adainforma.tk/hr_talenlokaal/TeamA/API/vacature_werkgever/read/?id="+vacId;

        RequestQueue qeue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            // Ons response
            JSONObject object = response;
            try {
                //Define JsonArray met de response
                JSONArray array = object.getJSONArray("data");
                //functie aanroepen om zijn id erin te zetten
                setEmployerId(array);
                callback.onSuccess();

            } catch (JSONException e) {
                callback.onError(e.toString());
            }
        }, error -> {
            // Ons error
            callback.onError(error.toString());
        });
        qeue.add(jsonObjectRequest);

    }

    public void setEmployerId(JSONArray array){
        try {
            JSONObject unitArray = array.getJSONObject(0);
            //id in de variabel zetten
            this.idEmployer = unitArray.getString("id");
        }
        catch(Exception e){
            Log.e("Error idEmployer", e.toString());
        }
    }

    //Array maken met de skills
    public void getMatchSkills(VolleyCallBack callBack){
        //hier maakt die een array met de matchSkills die overeen komen met die van de job
        ArrayList<ArrayList<String>> skillsMatch = new ArrayList<>();
        for(ArrayList<String> userSkill : this.userSkills){
            //Elke gebruiker met skills haalt die hierdoorheen
            if(!(userSkill.size() ==0)){
                //Als de gebruiker niet leeg is komt die hiet terecht
                ArrayList<String> matchUser = new ArrayList<>();
                //Hier pakt die de userId uit
                String user = userSkill.get(0);
                //count variable om op te halen hoeveel er overeen komen
                int match = 0;
                //user toevoegen voor de herkenning
                matchUser.add(user);
                int countRow = 0;

                for(String skill : userSkill){
                    if(countRow == 0){
                        //Hier komt die als het de userId betreft want dat is de eerste regel
                    }
                    else {
                        if (this.jobOfferSkills.contains(skill)) {
                            //Hier telt die 1 bij de match om het aantal op the voeren
                            match++;
                            //De skill in de overeenkomsten array toevoegen
                            matchUser.add(skill);
                        }
                    }
                    countRow++;
                }
                if(match==0){ }
                else {
                    //Hier voegt die de array bij de overal array om zo een verschil te maken
                    // tussen zij die geen overeenkomst hebben en zij die er wel 1 of meerder hebben
                    skillsMatch.add(matchUser);
                }
            }

        }

        this.matches =skillsMatch;
        callBack.onSuccess();

    }

    //begin van de match
    public void match(int vacId){
        //Hier haalt die all skills op die bij de vacature horen
        getJobOfferSkillsAPI(vacId, new VolleyCallBack() {
            @Override
            public void onSuccess() {
                //Bij succes alle users uitvoeren
                getUsers(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        //Bij succes de werkcultuurophalen
                        getEmployerId(vacId, new VolleyCallBack() {
                            @Override
                            public void onSuccess() {
                                //Bij succes de jobculture ophalen
                                getJobCultureAPI(idEmployer, new VolleyCallBack() {
                                    @Override
                                    public void onSuccess() {
                                        //Bij succes verder gaan met de matchmaking
                                        matchMaking();
                                    }

                                    @Override
                                    public void onError(String error) {
                                        Log.e("Error jobCulture: ", error);
                                    }
                                });
                            }

                            @Override
                            public void onError(String error) {
                                Log.e("errorEmploy", error);
                            }
                        });

                    }
                    public void onError(String error){
                        Log.e("Error users: ", error);
                    }
                });
            }
            public void onError(String error){
                Log.e("Error jobOffer: ", error);
            }
        });
    }

    public void matchCulture(VolleyMatchCallBack callback){
        // Hier matcht die de cultuur op die van de users
        ArrayList<ArrayList<String>> culture;
        culture = this.userCulture;
        int count = 0;
        // Elke opgehaalde user doorloopt die hier
        for(int i = 0; i < culture.size(); i++){
            ArrayList<String> cultureUser = new ArrayList<>();
            //UserId wordt hier toegevoegd voor de herkenning
            cultureUser.add(culture.get(i).get(0));
            //Hier telt die er 1 op voor de callback functie
            count++;
            //User wordt uit de orginele array gegooid voor de loop hieronder
            culture.get(i).remove(0);
            //Hier telt die de overeenkomsten
            double same = 0;
            for (int e = 0; e < culture.get(i).size(); e++){
                //Hier pakt die de strings voor de overzichtelijkheid
                String valueString1 = culture.get(i).get(e);
                String valueString2 = jobOfferCulture.get(e);

                //Hier zet die ze in integers
                int value1 = Integer.parseInt(valueString1);
                int value2 = Integer.parseInt(valueString2);
                //Hier kijkt die of ze 1 op 1 overeenkomen, zo ja dan telt die een heel punt
                if(value1 == value2){
                    same++;
                }
                //Hier kijkt die of ze 1 punt naar beneden verschillen, zo ja dan telt die een half punt
                else if((value1-1)==value2){
                    same = same +0.50;
                }
                //Hier kijkt die of ze 1 punt naar boven verschillen, zo ja dan telt die een half punt
                else if((value1+1)==value2){
                    same = same +0.50;
                }
                else {
                }
            }
            // Hier voegt die de overeenkomsten toe aan de array met de userId
            cultureUser.add(String.valueOf(countmatch(same)));
            //Hier voegt die hem toe aan de overall
            userCultureParsed.add(cultureUser);
            callback.onSuccess(count);

        }


    }

    public double countmatch(double numbers){
        //Hier berekent die hoeveel erovereen komen
        double count = 0;
        count = 100 / this.jobOfferCulture.size() ;
        count = count * numbers;
        return count;
    }

    public void setUserCulture(JSONArray array, String userId, VolleyCallBack callBack){
        try{
            //Hier pakt die de array en zet hem in een arraylist met de userId
            ArrayList<String> user = new ArrayList<>();
            //Add userId
            user.add(userId);
            for (int i = 0; i < array.length(); i++) {
                JSONObject unitArray = array.getJSONObject(i);
                //Voeg de waarde toe aan de array
                String waarde = unitArray.getString("Waarde");
                user.add(waarde);
            }
            if(!this.userCulture.contains(user)){
                //als die al is toegevoegd voegt die hem niet toe, hier dus wel
                this.userCulture.add(user);
                callBack.onSuccess();
            }
            else{
                callBack.onSuccess();
            }
        } catch(JSONException e) {
            callBack.onError(e.toString());
        }
    }

    public void getUserCultureApi(String userId,VolleyCallBack callBack){
        //User culture id ophalen
        String url = "https://bp4.adainforma.tk/hr_talenlokaal/TeamA/API/gebruiker_vragen/read/?id="+userId;

        RequestQueue qeue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            // Ons response
            JSONObject object = response;
            try {
                //Define JsonArray met de response
                JSONArray array = object.getJSONArray("data");
                //Checken of de array niet leeg is en de gebruiker dus geen antwoorden heeft gegeven
                if(array.length() != 0) {
                    //hier zet die ze in een array als de array niet leeg is
                    setUserCulture(array, userId, new VolleyCallBack() {
                        @Override
                        public void onSuccess() {
                            callBack.onSuccess();
                        }

                        @Override
                        public void onError(String error) {

                        }
                    });
                }
                else {
                    callBack.onSuccess();
                }

            } catch (JSONException e) {
                callBack.onError(e.toString());
            }
        }, error -> {
            // Ons error
            callBack.onError(error.toString());
        });
        qeue.add(jsonObjectRequest);

    }

    public void getUserCulture(VolleyMatchCallBack callback){
        //Elke user wordt hier gepakt en in een loop gegooid
        for (int i = 0; i < this.users.size(); i++){
            int count  = i;
            //Hier haalt die de cultuur op van de users
            getUserCultureApi(this.users.get(i), new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    //  if(count == usereCount) {
                    //Kijken of het aantal van de users overeen komt met het aantal dat de loop is doorlopen
                    if(count==(users.size()-1)) {
                        callback.onSuccess(count);
                    }
                    // }
                }
                @Override
                public void onError(String error) {
                    callback.onError(error);
                }
            });
        }
    }

    public void matchMaking() {
        //Users in een lokale array zetten
        ArrayList<String> users = this.users;

        //Alle userSkills ophalen
        setUserSkills(new VolleyMatchCallBack() {
            @Override
            public void onSuccess(int num) {
                // Hier kijkt die of het aantal keer dat de loop is doorlopen gelijk staat aan het aantal users
                if(num==(users.size()-1)) {
                    //Hier maakt die de match op basis van de skills
                    getMatchSkills(new VolleyCallBack() {
                        @Override
                        public void onSuccess() {
                           // Alle user cultuur punten ophalen
                            getUserCulture(new VolleyMatchCallBack() {
                                @Override
                                public void onSuccess(int num) {
                                    // Hier kijkt die of het aantal keer dat de loop is doorlopen gelijk staat aan het aantal users
                                    if(num== (users.size()-1)){
                                        //De matches op de cultuur vergelijken
                                        matchCulture(new VolleyMatchCallBack() {
                                            @Override
                                            public void onSuccess(int num) {
                                                if (num == userCulture.size()){
                                                    //De matches ophalen met de cultuur
                                                    setMatch();
                                                }
                                            }

                                            @Override
                                            public void onError(String error) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onError(String error) {

                                }
                            });
                            //Match skills percentages maken
                            matchPercent();
                        }

                        @Override
                        public void onError(String error) {
                            Log.e("error match", error);
                        }
                    });
                }
            }
            @Override
            public void onError(String error) {
                Log.e("Error userSkill", error);
            }
        });
    }

    public void setMatch(){
        //Aparte matchlist maken
        ArrayList<ArrayList<String>> matchesList = new ArrayList<>();
        //Alle userCultures pakken die er zijn en die in een for loop zetten voor controle
        for (ArrayList<String> array : this.userCultureParsed){
            //Hier worden de percentages gepakt
            double percent = Double.valueOf(array.get(1));
            //Hier worden de percentages gepakt en gecontroleerd of ze voldoen aan de aantallen
            if(Integer.parseInt(String.valueOf((int) percent))>=60) {
                //Hier wordt de matchlijst gepakt om te kijken of ze erin staan
                for (ArrayList<String> skill : this.matchClear) {
                    //Als ze genoeg percantage hebben wordt de arrays toegevoegd aan de definiteve array
                    if(Objects.equals(skill.get(0), array.get(0))){
                        matchesList.add(skill);
                    }
                }
            }
        }
        // De array wordt hier in het uiteindelijke resultaat gezet
        this.matchClear = matchesList;
        see();
    }

    public void matchPercent(){
        //Arrays defineren
        ArrayList<ArrayList<String>> matchPer = new ArrayList<>();
        ArrayList<ArrayList<String>> matchList = new ArrayList<>();
        // Hier worden alle matches doorlopen om een percentage te maken voor de skills
        for(ArrayList<String> match : this.matches){
            //Nogmaals  arrays defineren
            ArrayList<String> percent = new ArrayList<>();
            ArrayList<String> matchUser = new ArrayList<>();
            //Hier worden de userId gepakt, in de array gezet, en uit de overal array verwijderd
            percent.add(match.get(0));
            matchUser.add(match.get(0));
            match.remove(0);

            //Hier wordt de lengte (dus het aantal skills dat overeen komt) gepakt
            double matchesSize = match.size();

            //Er zijn altijd maar 4 skills
            matchesSize = matchesSize / 4;
            matchesSize = matchesSize * 100;
            //Percentage wordt uit de double gehaald en in een int gezet
            int percentCount = (int) matchesSize;
            //Percentage wordt toegevoegd na de userId
            percent.add(String.valueOf(percentCount));
            matchUser.add(String.valueOf(percentCount));
            //Hier worden de skills toegvoegd voor de definitieve match
            for(String skill : match){
                matchUser.add(skill);
            }
            //Toevoegen in de arrays
            matchList.add(matchUser);
            matchPer.add(percent);
        }
        //Toevoegen in de arrays
        matchClear = matchList;
        matchPercent = matchPer;

    }

    public void see(){
        //De matches in de Log zetten
        Log.e("De matches", this.matchClear.toString());
    }


}
