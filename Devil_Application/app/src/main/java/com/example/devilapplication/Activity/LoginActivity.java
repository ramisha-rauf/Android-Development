package com.example.devilapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.devilapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binder;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public void onclicksignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginButtonClick(View view) {
        // Get the text from the username and password EditText fields
        String username = binder.usernameEditText.getText().toString();
        String password = binder.passwordEditText.getText().toString();
        // Retrieve stored credentials from SharedPreferences
        String storedUsername = sharedPreferences.getString("username", "");
        String storedPassword = sharedPreferences.getString("password", "");


        // Check if username and password are not empty
        if (username.isEmpty() || password.isEmpty()) {
            // Display a message to fill in the required fields
            Toast.makeText(this, "Please fill in both username and password.", Toast.LENGTH_SHORT).show();
        } else if (username.equals(storedUsername) && password.equals(storedPassword)) {
            // Continue with the login process
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Display a message for invalid credentials
            Toast.makeText(this, "Invalid credentials. Please check your username and password.", Toast.LENGTH_SHORT).show();
        }
    }
}