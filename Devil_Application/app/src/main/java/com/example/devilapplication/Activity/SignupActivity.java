package com.example.devilapplication.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.devilapplication.R;
import com.example.devilapplication.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binder;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder= ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());

// Create a SpannableString for the text
        String checkBoxText = "Agree to the Terms and Conditions";
        SpannableString spannableString = new SpannableString(checkBoxText);

// Find the start and end indices of the words you want to style
        int start = checkBoxText.indexOf("Terms");
        int end = start + "Terms and Conditions".length();

// Create a ClickableSpan for the underlined text
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Show the terms and conditions dialog here
                showTermsAndConditionsDialog();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                // Customize the appearance of the underlined text
                ds.setUnderlineText(true);
                ds.setColor(Color.BLUE);
            }
        };

// Apply the ClickableSpan to the specified range
        spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

// Set the styled text to the CheckBox
        binder.agreeTermsCheckbox.setText(spannableString);

// Enable the ClickableSpan to receive clicks
        binder.agreeTermsCheckbox.setMovementMethod(LinkMovementMethod.getInstance());

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Set up a click listener for the signup button
        binder.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String firstName = binder.firstName.getText().toString();
                String lastName = binder.lastName.getText().toString();
                String username = binder.username.getText().toString();
                String email = binder.email.getText().toString().trim();
                String password = binder.password.getText().toString();
                String confirmPassword = binder.confirmPassword.getText().toString();
//                int selectedGenderId = binder.genderRadioGroup.getCheckedRadioButtonId();
//                String selectedGender = "";
//                if (selectedGenderId != -1) {
//                    RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
//                    selectedGender = selectedGenderRadioButton.getText().toString();
//                }
//                String selectedProvince = binder.provinceSpinner.getSelectedItem().toString();
                boolean agreedToTerms = binder.agreeTermsCheckbox.isChecked();

                // Perform validation
                if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()  || !agreedToTerms) {
                    // Display a message to fill in all fields and agree to terms
                    Toast.makeText(SignupActivity.this, "Please fill in all fields and agree to the terms.", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    // Display a message for an invalid email format
                    Toast.makeText(SignupActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    // Display a message for password mismatch
                    Toast.makeText(SignupActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                } else {
                    // Save user credentials to SharedPreferences
                    saveCredentials(username, password);

                    Intent intent= new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // Populate the Spinner with province options
//        ArrayAdapter<CharSequence> provinceAdapter = ArrayAdapter.createFromResource(
//                this,
//                R.array.province_array,
//                android.R.layout.simple_spinner_item
//        );
//        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binder.provinceSpinner.setAdapter(provinceAdapter);
    }

    private void saveCredentials(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
    private boolean isValidEmail(String email) {
        // You can implement your email format validation here
        // This is a simple example; you can use regular expressions for a more robust check
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void onclicklogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showTermsAndConditionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Customize the appearance and content of the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.custom_terms_dialog, null);
        TextView termsTextView = dialogView.findViewById(R.id.termsTextView);
        termsTextView.setText(R.string.terms);

        // Set up the AlertDialog
        builder.setTitle("Terms and Conditions");
        builder.setView(dialogView);

        // Set up positive button (OK)
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "OK"
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}



































//package com.example.devilapplication.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.devilapplication.R;
//
//public class SignupActivity extends AppCompatActivity {
//
//    EditText Username, Password, ConfirmPassword, Email;
//    Button SignupButton, ExistingText;
//    TextView SignupText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        Username = findViewById(R.id.username);
//        Password = findViewById(R.id.password);
//        ConfirmPassword = findViewById(R.id.confirmPassword);
//        Email = findViewById(R.id.email);
//        SignupButton = findViewById(R.id.signupButton);
//        ExistingText = findViewById(R.id.existingText);
//        SignupText = findViewById(R.id.signupText);
//
//        ExistingText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//            }
//        });
//
//        SignupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View view){
//                String username = Username.getText().toString().trim();
//                String password = Password.getText().toString().trim();
//                String email = Email.getText().toString().trim();
//                String confirm = ConfirmPassword.getText().toString().trim();
//                //Database db = new Database(getApplicationContext(),"tastybite",null,1);
//                // Check for empty fields
//                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm) || TextUtils.isEmpty(email)) {
//                    // Display an error message or toast indicating empty fields
//                    Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    // Hardcoded authentication logic
//                    if (password.compareTo(confirm)==0) {
//                       if (isValid(password)){
//                        //   db.signup(username,email,password);
//                           Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
//                           startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//                       }
//                       else{
//                           Toast.makeText(getApplicationContext(),"Password must contain atleast 8 characters, having letters,digits and special characters",Toast.LENGTH_SHORT).show();
//                       }
//                    }
//                    else {
//                        // Authentication failed, display an error message
//                        Toast.makeText(getApplicationContext(), "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
//
//    public static boolean isValid(String passwordhere){
//        int f1=0,f2=0,f3=0;
//        if (passwordhere.length()<8){
//            return false;
//        } else {
//            for (int p=0; p<passwordhere.length();p++){
//                if (Character.isLetter(passwordhere.charAt(p))){
//                    f1=1;
//                }
//            }
//            for (int p=0; p<passwordhere.length();p++){
//                if (Character.isLetter(passwordhere.charAt(p))){
//                    f2=1;
//                }
//            }
//            for (int p=0; p<passwordhere.length();p++){
//                if (Character.isLetter(passwordhere.charAt(p))){
//                    f3=1;
//                }
//            }
//
//            if (f1==1 && f2==1 && f3==1)
//                return true;
//            return false;
//
//        }
//    }
//}
