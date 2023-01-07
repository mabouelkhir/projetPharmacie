package com.example.pharmacieapp.user_pharmacie;

public class Garde {
    private int id;
    private TypeDeGarde type;

    public Garde(int id, TypeDeGarde type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeDeGarde getType() {
        return type;
    }

    public void setType(TypeDeGarde type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ""+ type;
    }
}
