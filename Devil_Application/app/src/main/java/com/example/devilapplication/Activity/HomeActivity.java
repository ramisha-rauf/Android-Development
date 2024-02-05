package com.example.devilapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.fragment.app.FragmentTransaction;
import androidx.core.app.NotificationCompat;

import com.example.devilapplication.API.GET.Display_API;
import com.example.devilapplication.API.POST.Display_POST;
import com.example.devilapplication.Fragments.DynamicFragment;
import com.example.devilapplication.R;
import com.example.devilapplication.Fragments.StaticFragment;
import com.example.devilapplication.Room.CrudActivity;
import com.example.devilapplication.recylerview.form.form;

public class HomeActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "FOOD APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnStaticFragment = findViewById(R.id.showStatic);

        btnStaticFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a fragment transaction
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace the current fragment with the static fragment
                transaction.replace(R.id.fragmentContainer, new StaticFragment());

                // Add the transaction to the back stack
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        createNotificationChannel();

        Button showNotificationButton = findViewById(R.id.show_notification_button);
        showNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });


        Button btnApiRequest = findViewById(R.id.GetButton);
        Button btnApiResponse = findViewById(R.id.postBtn);


        btnApiRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Display_API.class);
                startActivity(intent);
            }
        });

        btnApiResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Display_POST.class);
                startActivity(intent);
            }
        });
    }

    public void onclickUserDb(View view){
        Intent intent = new Intent(this, form.class);
        startActivity(intent);
    }

    public void onclickGet(View view){
        Intent intent = new Intent(this, com.example.devilapplication.API.GET.Display_API.class);
        startActivity(intent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "TastyBites";
            String description = "Food App";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Quote")
                .setContentText("Believe in the magic within you.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    public void onclickPost(View view){
        Intent intent = new Intent(this, com.example.devilapplication.API.POST.Display_POST.class);
        startActivity(intent);
    }

    public void onclickcalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void onclickview(View view) {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }

//
    public void onclickform(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

public void onclickDFragment(View view) {
    DynamicFragment settingsFragment = new DynamicFragment();

    // Add the fragment to the container using a FragmentTransaction
    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragmentContainer, new DynamicFragment())
            .addToBackStack(null)  // Add to back stack for navigation
            .commit();
}


    public void onclickstaticfragment(View view){
        Intent intent = new Intent(this, StaticFragment.class);
        startActivity(intent);
    }




}

