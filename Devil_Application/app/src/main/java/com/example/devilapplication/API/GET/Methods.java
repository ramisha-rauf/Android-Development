package com.example.devilapplication.API.GET;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Methods {
    @GET("api/unknown")
    Call<Model> getAllData();

}