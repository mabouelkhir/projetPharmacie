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
import android.widget.Toast;

import com.example.pharmacieapp.user_pharmacie.ApiClient;
import com.example.pharmacieapp.user_pharmacie.DatePharGarde;
import com.example.pharmacieapp.user_pharmacie.Pharmacie;
import com.example.pharmacieapp.user_pharmacie.PharmacieAdapter;
import com.example.pharmacieapp.user_pharmacie.PharmacieAdapterValide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriqueActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    List<DatePharGarde> gardes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.historique);

        sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myedit=sharedPreferences.edit();
        sharedPreferences.getLong("userid",1);
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.etat:
                        startActivity(new Intent(getApplicationContext(),PharmacyState.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.garde:
                        startActivity(new Intent(getApplicationContext(),GardeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.addpharmacie:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.historique:
                        return true;
                }
                return false;
            }
        });
            //(int) sharedPreferences.getLong("userid",1)
        Call<List<DatePharGarde>> getResponseCall = ApiClient.getService().AllGardes();
        getResponseCall.enqueue(new Callback<List<DatePharGarde>>() {
            @Override
            public void onResponse(Call<List<DatePharGarde>> call, Response<List<DatePharGarde>> response) {
                if (!response.isSuccessful()) {
                    Log.d("erreur","Code: " + response.code());
                    return;
                }
                gardes=response.body();
                PharmacieAdapterValide adapter = new PharmacieAdapterValide(HistoriqueActivity.this,gardes);
                recyclerView1.setAdapter(adapter);
                recyclerView1.setLayoutManager(new LinearLayoutManager(HistoriqueActivity.this));

                Log.d("TOTAL",""+gardes);
            }

            @Override
            public void onFailure(Call<List<DatePharGarde>> call, Throwable t) {
                String message =t.getLocalizedMessage();
                Toast.makeText(HistoriqueActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
}