package com.developer.companyproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubCategory extends AppCompatActivity {
    RecyclerView recyclerView;
    SubCategoryAdapter subCategoryAdapter;
    TextView tv_title;
    ImageView btnBack;
    ArrayList<SubCategoryModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        tv_title = findViewById(R.id.title);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubCategory.this, MenuDrawerAgri.class));
                finish();
            }
        });
        String title = getIntent().getStringExtra("category");
        tv_title.setText(title);
        recyclerView = findViewById(R.id.rv_sub_category);
        if (title.equals("Hair Care")) {
            hairCare();
        } else if (title.equals("Skin Care")) {
            skinCare();
        } else if (title.equals("Body Care")) {
            bodyCare();
        } else if (title.equals("Nails Care")) {
            nailsCare();
        } else if (title.equals("Make up")) {
            makeup();
        } else if (title.equals("Massage")) {
            massage();
        }


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SubCategory.this);
        recyclerView.setLayoutManager(layoutManager);
        subCategoryAdapter = new SubCategoryAdapter(SubCategory.this, title, arrayList);
        recyclerView.setAdapter(subCategoryAdapter);
    }

    private void hairCare() {
        arrayList.add(new SubCategoryModel(
                1,
                "Hot Oil + cream",
                "5-10 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                2,
                "Hot Oil package (4 Times)",
                "25 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                3,
                "Hot Oil Khashmiri",
                "4 - 7 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                4,
                "Blow Dry",
                "5 - 6 - 7 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                5,
                "Blow Dry corrugated",
                "6 - 7 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                6,
                "Blow Dry Package (5 Times)",
                "30 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                7,
                "Blow Drt Long Hair",
                "25 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                8,
                "Hair Cut",
                "5 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                9,
                "Hair Henna",
                "10 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                10,
                "Hair Traitement (10 Times)",
                "80 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                11,
                "Hair Royal Traitement (10 Times)",
                "150 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                12,
                "Hair Straightening",
                "20 - 50 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                13,
                "Premium Shampoo + Relaxing",
                "1.5 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                14,
                "Conditioner (Royal wash)",
                "4 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                15,
                "Hair Wash (Shampoo + Conditioner) ",
                "1.5 OMR"
        ));
    }

    private void bodyCare() {
        arrayList.add(new SubCategoryModel(
                1,
                "Full Body Wax",
                "20 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Full Body Sweet",
                "35 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Face Wax",
                "4 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Half Legs Wax",
                "3 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Full Legs Wax",
                "6 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Half Arms Wax",
                "3 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Full Arms Wax",
                "5 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Under Arms Wax",
                "1.5 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Bikini Wax",
                "10 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Full Face Threading",
                "4 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Eyebrows Full Body",
                "20 OMR"
        ));

        arrayList.add(new SubCategoryModel(
                1,
                "Sultana bath(Moroccan and relaxation)",
                "25 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Sultan's harem bath (Jacuzzi Smoothing whitening herbs Whitening and lightening blackness and relaxation)",
                "25 OMR"
        ));

    }

    private void skinCare() {
        arrayList.add(new SubCategoryModel(
                1,
                "Royal Facial Crystal & Gold",
                "35 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Facial & Massage",
                "15 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Gold Facial",
                "30 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Cleaning & Relaxing Facial",
                "25 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Crystal Facial",
                "25 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Indian Facial",
                "15 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Face Mask",
                "5 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Arms Henna",
                "3-25 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Feet Henna",
                "5-30 OMR"
        ));
    }

    private void nailsCare() {
        arrayList.add(new SubCategoryModel(
                1,
                "Manicure + Pedicure (Special)",
                "20 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Manicure + Pedicure (Normal)",
                "12 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "French Manicure (Tips & Toes)",
                "4 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Nail Extension (Gel / Acrylic)",
                "30 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Nail Extension for 2 Weeks",
                "15 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Pedicure and manicure VIP (private room)",
                "22 OMR"
        ));
    }

    private void makeup() {
        arrayList.add(new SubCategoryModel(
                1,
                "Eyebrow Bleaching",
                "2 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Face Bleaching",
                "3 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Eyelashes Extension",
                "30 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Eyebrow Drawing & Coloring",
                "3 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Make up Normal",
                "15 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Make up Specail",
                "15 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Engagement makeup",
                "50 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                1,
                "Make up Kids",
                "10 OMR"
        ));
    }

    private void massage() {
        arrayList.add(new SubCategoryModel(
                1,
                "Full Body Massage (60 Minuts)",
                "20 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                2,
                "Head Massage Half Hours (30 Minuts)",
                "7 OMR"
        ));
        arrayList.add(new SubCategoryModel(
                3,
                "Body Handi Massage (50 Minuts)",
                "20 OMR"
        ));
    }
}