package com.example.devilapplication.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface UserDao {
    @Insert
    void insert(Users users);

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllUsers();

    @Update
    void update(Users users);

    @Query("delete from users where id=:id")
    void delete(int id);
}

