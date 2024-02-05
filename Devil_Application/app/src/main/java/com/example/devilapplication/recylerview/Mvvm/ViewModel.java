package com.example.devilapplication.recylerview.Mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.devilapplication.recylerview.database.ListItemEntity;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private MediatorLiveData<List<ListItemEntity>> allData;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allData = new MediatorLiveData<>();
        allData.addSource(repository.getAllData(), allData::setValue);
    }

    public void insertData(ListItemEntity listItemEntity) {
        repository.insertData(listItemEntity);
    }

    public void deleteAllData() {
        repository.deleteAllData();
    }

    public void delete(ListItemEntity listItemEntity) {
        repository.delete(listItemEntity);
    }

    public LiveData<ListItemEntity> getItemByName(String name) {
        return repository.getItemByName(name);
    }

    public void updateData(ListItemEntity listItemEntity) {
        repository.updateData(listItemEntity);
    }

    public LiveData<List<ListItemEntity>> getAllData() {
        return allData;
    }

    // Other ViewModel methods...
}
