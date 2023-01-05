package com.developer.companyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Offer extends AppCompatActivity {
ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        init();
        setListener();
    }

    private void setListener() {
    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Offer.this,MenuDrawerAgri.class));
            finish();
        }
    });

    }

    private void init() {
    btnBack=findViewById(R.id.btnBack);

    }
}