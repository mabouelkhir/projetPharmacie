package com.example.pharmacieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.pharmacieapp.user_pharmacie.ApiClient;
import com.example.pharmacieapp.user_pharmacie.Etat;
import com.example.pharmacieapp.user_pharmacie.LoginActivity;
import com.example.pharmacieapp.user_pharmacie.LoginResponse;
import com.example.pharmacieapp.user_pharmacie.Pharmacie;
import com.example.pharmacieapp.user_pharmacie.RegisterActivity;
import com.example.pharmacieapp.user_pharmacie.RegisterRequest;
import com.example.pharmacieapp.user_pharmacie.RegisterResponse;
import com.example.pharmacieapp.user_pharmacie.Ville;
import com.example.pharmacieapp.user_pharmacie.Zone;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText edPname,edPAddress,edPLatitude,edPLongitude;
    LoginResponse loginResponse;
    SharedPreferences sharedPreferences;
    Spinner sp;
    List<Zone> zones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        edPname = findViewById(R.id.edPname);
        edPAddress = findViewById(R.id.edPAddress);
        edPLatitude= findViewById(R.id.edPLatitude);
        edPLongitude= findViewById(R.id.edPLongitude);
        sp=findViewById(R.id.spinnerZone);
        Call<List<Zone>> call1 = ApiClient.getService().Zone();
        call1.enqueue(new Callback<List<Zone>>() {
            @Override
            public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    zones = response.body();
                    ArrayAdapter<Zone> da=new ArrayAdapter<Zone>(getApplicationContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,zones);
                    sp.setAdapter(da);
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Zone>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   Object item = adapterView.getItemAtPosition(i);
               }

               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {


               }
                });


        Bundle extras = getIntent().getExtras();
        sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myedit=sharedPreferences.edit();
        sharedPreferences.getLong("userid",1);
        Log.d("userid",""+sharedPreferences.getLong("userid",1));


        Gson gson1 = new Gson();
        String json1=sharedPreferences.getString("loginresponse","");
        LoginResponse loginResponse1 = gson1.fromJson(json1,LoginResponse.class);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.addpharmacie);
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

                    case R.id.historique:
                        startActivity(new Intent(getApplicationContext(),HistoriqueActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.addpharmacie:
                        return true;
                }
                return false;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edPname.getText().toString())||TextUtils.isEmpty(edPAddress.getText().toString())||TextUtils.isEmpty(edPLatitude.getText().toString())||TextUtils.isEmpty(edPLongitude.getText().toString())) {
                    String message = "All fields are required";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }else {
                    Pharmacie pharmacie = new Pharmacie();
                    pharmacie.setNom(edPname.getText().toString());
                    pharmacie.setAdresse(edPAddress.getText().toString());
                    pharmacie.setLatitude(Double.parseDouble(edPLatitude.getText().toString()));
                    pharmacie.setLongitude(Double.parseDouble(edPLongitude.getText().toString()));
                    pharmacie.setZone((Zone) sp.getSelectedItem());
                    pharmacie.setEtat(Etat.attente);
                    pharmacie.setLoginResponse(loginResponse1);
                    registerPharmacie(pharmacie);
                }
            }
        });



    }

    public void registerPharmacie(Pharmacie pharmacie){
        Call<Pharmacie> registerResponseCall = ApiClient.getService().registerPharmacie(pharmacie);
        registerResponseCall.enqueue(new Callback<Pharmacie>() {
            @Override
            public void onResponse(Call<Pharmacie> call, Response<Pharmacie> response) {
                if(response.isSuccessful()){
                    Pharmacie pharmacie1=response.body();
                    String message = "Successful";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, PharmacyState.class));
                    finish();
                }else {
                    String message = "Unable to register user";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Pharmacie> call, Throwable t) {
                String message =t.getLocalizedMessage();
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    }