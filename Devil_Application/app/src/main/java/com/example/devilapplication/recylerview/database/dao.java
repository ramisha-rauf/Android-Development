package com.example.devilapplication.recylerview.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface dao {
    @Insert
    void insert(ListItemEntity listItemEntity);
    @Delete
    void delete(ListItemEntity listItemEntity);
    @Update
    void update(ListItemEntity listItemEntity);

    @Query("SELECT * FROM database_table")
    LiveData<List<ListItemEntity>> getAllData();

    @Query("SELECT * FROM database_table WHERE name = :name")
    LiveData<ListItemEntity> getItemByName(String name);

    @Query("DELETE FROM database_table")
    void deleteAllData();



    // Other database operations...
}

