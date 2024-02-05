package com.example.devilapplication.API.GET;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Model.Data>> dataLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<ArrayList<Model.Data>> getData() {
        return dataLiveData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadDataFromAPI() {
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getAllData();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {
                    ArrayList<Model.Data> data = response.body().getData();
                    dataLiveData.setValue(data);
                } else {
                    errorMessage.setValue("Request not successful");
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                errorMessage.setValue("An error has occurred");
            }
        });
    }
}

