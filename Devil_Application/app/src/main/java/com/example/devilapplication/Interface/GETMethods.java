package com.example.devilapplication.Interface;

import android.graphics.ColorSpace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GETMethods {

    @GET("api/users?page=2")
    Call<ColorSpace.Model> getAllData();
}

