package com.developer.companyproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.developer.companyproject.R.drawable.box;
import static com.developer.companyproject.R.drawable.box2;
import static com.developer.companyproject.R.drawable.btn_rounded_green_300;

public class MainActivity extends AppCompatActivity {

    private View parent_view;
    TextInputEditText etUserName,etPassword;
    Button btnLogin,btnSetUser;
    TextView tvRegister;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    //    SignUpModel signUpModel;
    TextView textView2;
    int userType=0;
    private final static int GALLERY_CODE=1;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);
        init();
        setListener();
//        setSupportActionBar(toolbar);

        progressDialog=new ProgressDialog(this);
//        signUpModel=new SignUpModel(this);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference("Company App").child("Users");

//        Tools.setSystemBarColor(this, android.R.color.white);
//        Tools.setSystemBarLight(this);

        ((View) findViewById(R.id.sign_up_for_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Sign up for an account", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setListener() {

        btnSetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userType==0){
                    btnSetUser.setText("A d m i n");
                    btnSetUser.setBackground(getResources().getDrawable(box));
                    btnSetUser.setTextColor(Color.BLACK);
                    userType=1;
                }else{
                    btnSetUser.setBackground(getResources().getDrawable(box2));
                    btnSetUser.setText("U s e r");
                    btnSetUser.setTextColor(Color.WHITE);

                    userType=0;
                }

            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUserName.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Login Wait.....");
                progressDialog.show();
                if(userType==0){
                    userLogin(username,password);
                }else{
                    adminLogin(username,password);

                }



            }
        });
    }

    private void adminLogin(String username, final String password) {
        Query query = FirebaseDatabase.getInstance().getReference("Admin").orderByChild("username").equalTo(username);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String signUpModel = childSnapshot.child("password").getValue(String.class);

                    assert signUpModel != null;
                    if (signUpModel.equals(password)) {
                        // Here is your desired location
                        AppData.userId = childSnapshot.getKey();
//                                AppData.phoneNo = childSnapshot.child("phoneNumber").getValue(String.class);
                        progressDialog.dismiss();
                        AppData.userType="admin";
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, AdminPanel.class));

                    } else {
                        progressDialog.dismiss();

                        Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });



    }


    private void init() {
     etUserName=findViewById(R.id.etUsername);
     etPassword=findViewById(R.id.etPassword);
     btnLogin=findViewById(R.id.btnLogin);
     tvRegister=findViewById(R.id.tvRegister);
     btnSetUser=findViewById(R.id.btnsetuser);


    }

    void userLogin(String username, final String password){
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(username);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String signUpModel = childSnapshot.child("password").getValue(String.class);

                    assert signUpModel != null;
                    if (signUpModel.equals(password)) {
                        // Here is your desired location
                        AppData.userId = childSnapshot.getKey();
                        AppData.userType="user";
//                                AppData.phoneNo = childSnapshot.child("phoneNumber").getValue(String.class);
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MenuDrawerAgri.class));
                        finish();

                    } else {
                        progressDialog.dismiss();

                        Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}