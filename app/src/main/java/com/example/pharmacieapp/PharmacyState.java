package com.example.pharmacieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacieapp.user_pharmacie.ApiClient;
import com.example.pharmacieapp.user_pharmacie.Etat;
import com.example.pharmacieapp.user_pharmacie.Pharmacie;
import com.example.pharmacieapp.user_pharmacie.PharmacieAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacyState extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    List<Pharmacie> pharmacies = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_state);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.etat);
        sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myedit=sharedPreferences.edit();
        sharedPreferences.getLong("userid",1);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.garde:
                        startActivity(new Intent(getApplicationContext(),GardeActivity.class));
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

                    case R.id.etat:
                        return true;
                }
                return false;
            }
        });

        Call<List<Pharmacie>> getResponseCall = ApiClient.getService().getPharmacieByUser((int) sharedPreferences.getLong("userid",1));
        getResponseCall.enqueue(new Callback<List<Pharmacie>>() {
            @Override
            public void onResponse(Call<List<Pharmacie>> call, Response<List<Pharmacie>> response) {
                if (!response.isSuccessful()) {
                    Log.d("erreur","Code: " + response.code());
                    return;
                }
                pharmacies=response.body();
                PharmacieAdapter adapter = new PharmacieAdapter(PharmacyState.this,pharmacies);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(PharmacyState.this));

                Log.d("TOTAL",""+pharmacies);
            }

            @Override
            public void onFailure(Call<List<Pharmacie>> call, Throwable t) {
                String message =t.getLocalizedMessage();
                Toast.makeText(PharmacyState.this,message,Toast.LENGTH_LONG).show();
            }
        });





    }




}