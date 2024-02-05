package com.example.devilapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devilapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView selectedItemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        spinner = findViewById(R.id.spinner);
        selectedItemText = findViewById(R.id.selectedItem);

        // Create a list of items for the spinner
        List<String> items = new ArrayList<>();
        items.add("Male");
        items.add("Female");
        items.add("Transgender");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set up a listener to capture the selected item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Display the selected item in the TextView
                selectedItemText.setText("Selected :" + items.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });


        Button existingRegister = findViewById(R.id.existingText);
        existingRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        // Handle form submission (e.g., when a "Submit" button is clicked)
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from EditText fields
                EditText nameEditText = findViewById(R.id.fullname);
                EditText emailEditText = findViewById(R.id.email);
                EditText phoneEditText = findViewById(R.id.phone);
                //EditText cityEditText = findViewById(R.id.city);
                EditText adressEditText = findViewById(R.id.address);

                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNumber = phoneEditText.getText().toString();
              //  String city = cityEditText.getText().toString();
                String address = adressEditText.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phoneNumber)  || TextUtils.isEmpty(address)) {
                    // Display an error message or toast indicating empty fields
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Customer Registered !",Toast.LENGTH_SHORT).show();
                }



                // Clear the EditText fields
                nameEditText.setText("");
                emailEditText.setText("");
                phoneEditText.setText("");
              //  cityEditText.setText("");
                adressEditText.setText("");
            }
        });
    }
}
