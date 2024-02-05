package com.example.devilapplication.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devilapplication.Activity.UpdateActivity;
import com.example.devilapplication.Adapter.AdapterListener;
import com.example.devilapplication.Adapter.UserAdapter;
import com.example.devilapplication.R;

import java.util.List;

public class CrudActivity extends AppCompatActivity implements AdapterListener {

    EditText editName, editPrice;
    Button insertButton;
    RecyclerView myrecycler;

    private UserDatabase userDatabase;
    private UserDao userDao;

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userAdapter = new UserAdapter(this, this);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        editName = findViewById(R.id.name);
        editPrice = findViewById(R.id.email);
        insertButton = findViewById(R.id.insert);
        myrecycler = findViewById(R.id.usersRecycler);

        myrecycler.setAdapter(userAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String priceStr = editPrice.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(priceStr)) {
                    try {
                        Users users = new Users(0, name, priceStr);
                        userViewModel.insert(users);

                        editName.setText("");
                        editPrice.setText("");

                        Toast.makeText(CrudActivity.this, "Inserted. Email: " + priceStr, Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(CrudActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CrudActivity.this, "Please enter a name and email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetchData();
    }

    private void fetchData() {
        userViewModel.getAllUsers().observe(this, users -> {
            userAdapter.setusersList(users);
        });
    }

    @Override
    public void OnUpdate(Users users) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("model", users);
        startActivity(intent);
    }

    @Override
    public void OnDelete(int id, int pos) {
        userViewModel.delete(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
