package com.example.devilapplication.API.POST;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Methods {
    @POST("/api/users") // Change this to your actual API endpoint
    @FormUrlEncoded
    Call<Model> getUserData(
            @Field("name") String name,
            @Field("job") String job
    );
}
