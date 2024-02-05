package com.example.devilapplication.recylerview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.devilapplication.databinding.ActivityListViewBinding;
import com.example.devilapplication.recylerview.form.FormData;
import com.example.devilapplication.recylerview.database.ListItemEntity;
import com.example.devilapplication.R;
import com.example.devilapplication.recylerview.Mvvm.ViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    ActivityListViewBinding binder;
    private List<ListItemEntity> items;
    private ListAdapter adapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityListViewBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());

        binder.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        // Pass the ViewModel instance to the ListAdapter
        adapter = new ListAdapter(items, viewModel);
        binder.recyclerView.setAdapter(adapter);

        // Set up "Delete All" button click listener
        binder.btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAllConfirmationDialog();
            }
        });

        Intent intent = getIntent();
        FormData formData = intent.getParcelableExtra("formData");

        if (formData != null) {
            ListItemEntity newItem = new ListItemEntity();
            newItem.setName(formData.getName());
            newItem.setEmail(formData.getEmail());
            viewModel.insertData(newItem);
        }

        // Load existing data from the database and observe changes
        viewModel.getAllData().observe(this, new Observer<List<ListItemEntity>>() {
            @Override
            public void onChanged(List<ListItemEntity> listItems) {
                if (listItems != null) {
                    adapter.setData(listItems);
                }
            }
        });
    }

    private void showDeleteAllConfirmationDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to delete all items?");

        // Set up positive button (Yes)
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "Yes," delete all items
                viewModel.deleteAllData();
                dialog.dismiss();
            }
        });

        // Set up negative button (No)
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "No" or the back button, do nothing
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Set up a handler to dismiss the dialog after 5 seconds
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (alertDialog != null && alertDialog.isShowing()) {
                    // User didn't respond within 5 seconds, dismiss the dialog
                    alertDialog.dismiss();
                }
            }
        }, 5000); // 5000 milliseconds = 5 seconds
    }
}
