package com.example.devilapplication.API.POST;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devilapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Display_POST extends AppCompatActivity {

    private EditText txtName,txtJob;
    private TextView lblOutput;
    private Button btnPostData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_post);

        txtName = findViewById(R.id.txtName);
        txtJob = findViewById(R.id.txtJob);
        lblOutput = findViewById(R.id.lblOutput);
        btnPostData = findViewById(R.id.btnPostData);


        btnPostData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Data From TextBox
                String strName,strJob;
                strName = txtName.getText().toString();
                strJob = txtJob.getText().toString();

                if(strName == "")
                {
                    Toast.makeText(Display_POST.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                }
                else if(strJob == "")
                {
                    Toast.makeText(Display_POST.this,"Please Enter Job",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                    Call<Model> call = methods.getUserData(strName,strJob);

                    call.enqueue(new Callback<Model>() {
                        @Override

                        public void onResponse(Call<Model> call, Response<Model> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                String strOutput = "Id : " + response.body().getId();
                                strOutput = strOutput + "\nName : " + response.body().getName();
                                strOutput = strOutput + "\nPrice : " + response.body().getJob();
                                strOutput = strOutput + "\nCreated At : " + response.body().getCreatedAt();
                                lblOutput.setText(strOutput);
                            } else {
                                Toast.makeText(Display_POST.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Model> call, Throwable t) {
                            Toast.makeText(Display_POST.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}