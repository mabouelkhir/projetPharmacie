package com.example.pharmacieapp.user_pharmacie;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DatePharmPk implements Serializable {
    private int pharmacie;
    private int garde;
    private String datedebut = String.valueOf(Calendar.getInstance().getTime());

    public DatePharmPk(int pharmacie, int garde, String datedebut) {
        this.pharmacie = pharmacie;
        this.garde = garde;
        this.datedebut = datedebut;
    }

    public DatePharmPk() {
    }

    public int getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(int pharmacie) {
        this.pharmacie = pharmacie;
    }

    public int getGarde() {
        return garde;
    }

    public void setGarde(int garde) {
        this.garde = garde;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }
}
