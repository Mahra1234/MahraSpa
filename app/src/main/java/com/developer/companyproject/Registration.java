package com.developer.companyproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    TextInputEditText et_username;
    TextInputEditText et_password;
    TextInputEditText et_phone;
    TextInputEditText et_email;
    TextInputEditText et_city;
    ImageView imageSignUp;
    Button btnSignUp;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    Toolbar toolbar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
//        parent_view = findViewById(android.R.id.content);
        intit();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Company App");
        progressDialog = new ProgressDialog(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = et_username.getText().toString().trim();
                final String password = et_password.getText().toString().trim();
                final String phone = et_phone.getText().toString().trim();
                final String email = et_email.getText().toString().trim();
                final String city = et_city.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Registration.this, "Enter UserName", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Registration.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone NO", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(city)) {
                    Toast.makeText(getApplicationContext(), "Enter City", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("SignUp Loading...");
                progressDialog.show();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").push();
                ref.child("phoneNumber").setValue(phone);
                ref.child("username").setValue(username);
                ref.child("password").setValue(password);
                ref.child("city").setValue(city);
                ref.child("email").setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registration.this, MainActivity.class));
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private void intit() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_city = findViewById(R.id.et_city);
        btnSignUp = findViewById(R.id.btnSignup);


    }
}