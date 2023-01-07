package com.example.pharmacieapp.user_pharmacie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacieapp.MainActivity;
import com.example.pharmacieapp.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUsername,edPassword;
    TextView noAccount;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin=findViewById(R.id.btnLogin);
        edUsername = findViewById(R.id.etUsername);
        edPassword = findViewById(R.id.etPassword);
        noAccount = findViewById(R.id.tvCreateAccount);

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
                    String message = "All fields are required";
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                } else {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(edUsername.getText().toString());
                    loginRequest.setPassword(edPassword.getText().toString());
                    loginUser(loginRequest);
                }
            }
        });

    }

    public void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myedit=sharedPreferences.edit();
                    myedit.putLong("userid", loginResponse.getId());
                    myedit.commit();
                    Gson gson = new Gson();
                    String json = gson.toJson(loginResponse);
                    myedit.putString("loginresponse",json);
                    myedit.commit();
                    Log.d("loooooooogin",json);
                    finish();
                }else {
                    String message = "Unable to register user";
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message =t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
}