package com.example.devilapplication.API.POST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private final Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);

    public interface PostDataCallback {
        void onDataReceived(String output);

        void onError(String errorMessage);
    }

    public void postUserData(String name, String job, PostDataCallback callback) {
        Call<Model> call = methods.getUserData(name, job);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String strOutput = "Id : " + response.body().getId();
                    strOutput = strOutput + "\nName : " + response.body().getName();
                    strOutput = strOutput + "\nPrice : " + response.body().getJob();
                    strOutput = strOutput + "\nCreated At : " + response.body().getCreatedAt();
                    callback.onDataReceived(strOutput);
                } else {
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                callback.onError("Error Occurred");
            }
        });
    }
}

