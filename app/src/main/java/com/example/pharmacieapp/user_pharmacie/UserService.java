package com.example.pharmacieapp.user_pharmacie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("userpharmacie/auth")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("userpharmacie/add")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("pharmacies/add")
    Call<Pharmacie> registerPharmacie(@Body Pharmacie pharmacie);

    @POST("gardeinfo/add")
    Call<DatePharGarde> registerGarde(@Body DatePharGarde datePharGarde);

    @GET("zones/all")
    Call<Zone> getZones();

    @GET("gardeinfo/all")
    Call<List<DatePharGarde>> AllGardes();

    @GET("zones/all")
    Call<List<Zone>> Zone();

    @GET("gardes/all")
    Call<List<Garde>> Garde();

    @GET("pharmacies/user/{user_id}")
    Call<List<Pharmacie>> getPharmacieByUser(@Path("user_id") int user_id);

    @GET("pharmacies/user/{user_id}/valide")
    Call<List<Pharmacie>> getPharmacieByUserValide(@Path("user_id") int user_id);

}
