package com.example.devilapplication.Adapter;

import com.example.devilapplication.Room.Users;

public interface AdapterListener {
    void OnUpdate(Users users);
    void OnDelete(int id,int pos);
}
