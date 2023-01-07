package com.example.pharmacieapp.user_pharmacie;

import java.util.Date;

public class DatePharGarde {


    private DatePharmPk pk = new DatePharmPk();
    private Date dateFin;
    private Pharmacie pharmacie;
    private Garde garde;

    public DatePharGarde(DatePharmPk pk, Date dateFin, Pharmacie pharmacie, Garde garde) {
        this.pk = pk;
        this.dateFin = dateFin;
        this.pharmacie = pharmacie;
        this.garde = garde;
    }

    public DatePharGarde() {
    }

    public DatePharmPk getPk() {
        return pk;
    }

    public void setPk(DatePharmPk pk) {
        this.pk = pk;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public Garde getGarde() {
        return garde;
    }

    public void setGarde(Garde garde) {
        this.garde = garde;
    }
}
