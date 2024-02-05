package com.example.devilapplication.Room;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<Users>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<Users>> getAllUsers() {
        return allUsers;
    }

    public void insert(Users users) {
        userRepository.insert(users);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    // You can add other methods as needed
}
