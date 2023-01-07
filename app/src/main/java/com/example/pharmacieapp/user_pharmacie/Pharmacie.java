package com.example.pharmacieapp.user_pharmacie;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pharmacie implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("nom")
    private String nom;
    @SerializedName("zone")
    private Zone zone ;
    @SerializedName("etat")
    private Etat etat;
    @SerializedName("userPharmacie")
    private LoginResponse loginResponse;

    public Pharmacie(String adresse, double latitude, double longitude, String nom, Zone zone, Etat etat, LoginResponse loginResponse) {
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.zone = zone;
        this.etat = etat;
        this.loginResponse = loginResponse;
    }

    public Pharmacie() {

    }

    public Pharmacie(String s, String s1, Double s2, Double s3, Etat s4) {
        this.nom=s;
        this.adresse=s1;
        this.latitude=s2;
        this.longitude=s3;
        this.etat=s4;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return ""+ nom ;
    }
}
