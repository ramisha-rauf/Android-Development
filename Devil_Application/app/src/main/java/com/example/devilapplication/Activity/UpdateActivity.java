package com.example.devilapplication.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.devilapplication.R;
import com.example.devilapplication.Room.UserDao;
import com.example.devilapplication.Room.UserDatabase;
import com.example.devilapplication.Room.Users;

public class UpdateActivity extends AppCompatActivity {
    private EditText editName, emailEdit;
    private Button updateButton;

    private Users users;
    private UserDatabase userDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        emailEdit = findViewById(R.id.email);
        editName = findViewById(R.id.name);
        updateButton = findViewById(R.id.update);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        users = (Users) getIntent().getSerializableExtra("model");

        editName.setText(users.getName());
        emailEdit.setText(users.getEmail());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    private void updateData() {
        // Get data from your UI components
        String newName = editName.getText().toString(); // get new name from your UI component;
        String newEmail = emailEdit.getText().toString(); // get new email from your UI component;

        // Check if the fields are not empty
        if (TextUtils.isEmpty(newName) || TextUtils.isEmpty(newEmail)) {
            Toast.makeText(UpdateActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the user object with new data
        Users updatedUser = new Users(users.getId(), newName, newEmail);

        // Move the database operation to a background thread
        new UpdateUserAsyncTask(userDao, updatedUser).execute();
    }

    private class UpdateUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private Users user;

        private UpdateUserAsyncTask(UserDao userDao, Users user) {
            this.userDao = userDao;
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.update(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Update your UI or perform any other necessary operations after the update
            finish(); // Finish the activity after the update is complete
        }
    }
}
