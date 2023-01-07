package com.example.pharmacieapp.user_pharmacie;

import java.io.Serializable;

public class Zone implements Serializable {

    private int id;
    private String nom;
    private Ville ville;

    public Zone(int id, String nom, Ville ville) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
    }

    public Zone(int i) {
        this.id=i;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "" + nom ;
    }
}
