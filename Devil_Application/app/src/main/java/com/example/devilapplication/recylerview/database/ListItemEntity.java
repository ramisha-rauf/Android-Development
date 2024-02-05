package com.example.devilapplication.recylerview.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "database_table")
public class ListItemEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String email;

    public ListItemEntity() {
    }

    public ListItemEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
