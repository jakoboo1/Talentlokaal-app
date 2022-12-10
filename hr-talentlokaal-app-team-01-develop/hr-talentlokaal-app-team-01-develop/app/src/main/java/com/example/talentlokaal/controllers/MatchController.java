package com.example.talentlokaal.controllers;

import android.os.Bundle;
import android.service.autofill.FieldClassification;

import androidx.fragment.app.FragmentManager;

import com.example.talentlokaal.models.Match;

import java.util.ArrayList;

public class MatchController {

    public ArrayList<Match> match;

    public MatchController(){
        match = new ArrayList<>();
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
        match.add(new Match("Elske Asselbergs", "ef.asselbergs1@ad-academie.nl ", "012345678", "Breda"));
        match.add(new Match("Yannick Nijssen", "jj.nijssen@ad-academie.nl", "57144516", "Roosendaal"));
        match.add(new Match("Wouter de Ruiter", "w.deruiter3@ad-academie.nl", "7454512248", "Rotterdam"));
        match.add(new Match("Tomas Schijvenaars", "tgp.schijvenaars@ad-academie.nl", "451451651", "Roosendaal"));
        match.add(new Match("Jakub Kinzler", "ja.kinzler@ad-academie.nl", "144125405", "Etten-Leur"));
    }

}
