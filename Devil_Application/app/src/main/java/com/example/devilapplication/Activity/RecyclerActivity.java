package com.example.devilapplication.Activity;

// MainActivity.java
// MainActivity.java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devilapplication.Adapter.AnimalAdapter;
import com.example.devilapplication.Model.Animal;
import com.example.devilapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Animal> animals = generateAnimalData();
        adapter = new AnimalAdapter(animals);
        recyclerView.setAdapter(adapter);
    }

    private List<Animal> generateAnimalData() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Lion"));
        animals.add(new Animal("Tiger"));
        animals.add(new Animal("Elephant"));
        animals.add(new Animal("Giraffe"));
        animals.add(new Animal("Polar Bear"));
        animals.add(new Animal("Gorilla"));
        animals.add(new Animal("Dog"));
        animals.add(new Animal("Cat"));
        animals.add(new Animal("Monkey"));
        animals.add(new Animal("Horse"));
        animals.add(new Animal("Donkey"));
        animals.add(new Animal("Goat"));
        animals.add(new Animal("Rat"));
        animals.add(new Animal("Lion"));
        animals.add(new Animal("Tiger"));
        animals.add(new Animal("Elephant"));
        animals.add(new Animal("Giraffe"));
        animals.add(new Animal("Polar Bear"));
        animals.add(new Animal("Gorilla"));
        animals.add(new Animal("Dog"));
        animals.add(new Animal("Cat"));
        animals.add(new Animal("Monkey"));
        animals.add(new Animal("Horse"));
        animals.add(new Animal("Donkey"));
        animals.add(new Animal("Goat"));
        animals.add(new Animal("Rat"));

        // Add more animals as needed
        return animals;
    }
}


