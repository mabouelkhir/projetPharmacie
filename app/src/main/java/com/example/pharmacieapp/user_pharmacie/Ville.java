package com.example.pharmacieapp.user_pharmacie;

import java.io.Serializable;
import java.util.List;

public class Ville implements Serializable {

    private int id;
    private String nom;

    public Ville(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

}
