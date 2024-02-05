package com.example.devilapplication.API.POST;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostViewModel extends ViewModel {
    private final PostRepository repository = new PostRepository();

    private final MutableLiveData<String> outputLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getOutputLiveData() {
        return outputLiveData;
    }

    public void postUserData(String name, String job) {
        repository.postUserData(name, job, new PostRepository.PostDataCallback() {
            @Override
            public void onDataReceived(String output) {
                outputLiveData.postValue(output);
            }

            @Override
            public void onError(String errorMessage) {
                outputLiveData.postValue(errorMessage);
            }
        });
    }
}

