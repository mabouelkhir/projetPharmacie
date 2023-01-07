package com.example.pharmacieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pharmacieapp.user_pharmacie.ApiClient;
import com.example.pharmacieapp.user_pharmacie.DatePharGarde;
import com.example.pharmacieapp.user_pharmacie.DatePharmPk;
import com.example.pharmacieapp.user_pharmacie.Etat;
import com.example.pharmacieapp.user_pharmacie.Garde;
import com.example.pharmacieapp.user_pharmacie.Pharmacie;
import com.example.pharmacieapp.user_pharmacie.PharmacieAdapter;
import com.example.pharmacieapp.user_pharmacie.PharmacieAdapterValide;
import com.example.pharmacieapp.user_pharmacie.TypeDeGarde;
import com.example.pharmacieapp.user_pharmacie.Zone;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GardeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Spinner spP,spG;
    Button btnMention;
    List<Pharmacie> pharmacies;
    List<Garde> gardes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garde);

        sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myedit=sharedPreferences.edit();
        sharedPreferences.getLong("userid",1);
        spP=findViewById(R.id.spinnerPharmacie);
        spG=findViewById(R.id.gardes);
        btnMention =findViewById(R.id.btnMention);

        ///////////////////////////////////////////////
        Call<List<Pharmacie>> call1 = ApiClient.getService().getPharmacieByUserValide((int) sharedPreferences.getLong("userid",1));
        call1.enqueue(new Callback<List<Pharmacie>>() {
            @Override
            public void onResponse(Call<List<Pharmacie>> call, Response<List<Pharmacie>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    pharmacies = response.body();
                    ArrayAdapter<Pharmacie> da=new ArrayAdapter<Pharmacie>(getApplicationContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,pharmacies);
                    spP.setAdapter(da);
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Pharmacie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        spP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
        //////////////////////////////////////////////////////////////
        Call<List<Garde>> call2 = ApiClient.getService().Garde();
        call2.enqueue(new Callback<List<Garde>>() {
            @Override
            public void onResponse(Call<List<Garde>> call, Response<List<Garde>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    gardes = response.body();
                    ArrayAdapter<Garde> da=new ArrayAdapter<Garde>(getApplicationContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,gardes);
                    spG.setAdapter(da);
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Garde>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        spG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.garde);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.etat:
                        startActivity(new Intent(getApplicationContext(),PharmacyState.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.addpharmacie:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.historique:
                        startActivity(new Intent(getApplicationContext(),HistoriqueActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.garde:
                        return true;
                }
                return false;
            }
        });

        btnMention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePharGarde datePharGarde = new DatePharGarde();
                DatePharmPk datePharmPk = new DatePharmPk();
                datePharGarde.setPk(new DatePharmPk());
                datePharGarde.setPharmacie((Pharmacie) spP.getSelectedItem());
                datePharGarde.setGarde((Garde) spG.getSelectedItem());

                    registerGarde(datePharGarde);

            }
        });

    }

    public void registerGarde(DatePharGarde datePharGarde){
        Call<DatePharGarde> registerResponseCall = ApiClient.getService().registerGarde(datePharGarde);
        registerResponseCall.enqueue(new Callback<DatePharGarde>() {
            @Override
            public void onResponse(Call<DatePharGarde> call, Response<DatePharGarde> response) {

                if(response.isSuccessful()){
                    DatePharGarde datePharGarde1=response.body();
                    String message = "Successful";
                    Toast.makeText(GardeActivity.this,message,Toast.LENGTH_LONG).show();

                }else {
                    String message = "Unable to register user";
                    Toast.makeText(GardeActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DatePharGarde> call, Throwable t) {
                String message =t.getLocalizedMessage();
                Toast.makeText(GardeActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
}