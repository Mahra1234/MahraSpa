package com.developer.companyproject;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MenuDrawerAgri extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    LinearLayout homeService,offer,booking,logout;
    ConstraintLayout hairCare,skinCare,bodyCare,NailsCare,makeup,massage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer_agri);

        initToolbar();
        initNavigationMenu();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        homeService=findViewById(R.id.homeService);
        offer=findViewById(R.id.offer);
        booking=findViewById(R.id.booking);
        logout=findViewById(R.id.logout);
        hairCare=findViewById(R.id.lyt_parent1);
        skinCare=findViewById(R.id.lyt_parent2);
        bodyCare=findViewById(R.id.lyt_parent3);
        NailsCare=findViewById(R.id.lyt_parent4);
        makeup=findViewById(R.id.lyt_parent5);
        massage=findViewById(R.id.lyt_parent6);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Home");

        homeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuDrawerAgri.this,MainActivity.class));
               finish();

            }
        });
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuDrawerAgri.this,Offer.class));

            }
        });
         booking.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MenuDrawerAgri.this,MyBooking.class));

             }
         });

        hairCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent= new Intent(MenuDrawerAgri.this, SubCategory.class);
               intent.putExtra("category","Hair Care");
                startActivity(intent);
            }
        });
        skinCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuDrawerAgri.this, SubCategory.class);
                intent.putExtra("category","Skin Care");
                startActivity(intent);
            }
        });
        bodyCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuDrawerAgri.this, SubCategory.class);
                intent.putExtra("category","Body Care");
                startActivity(intent);
            }
        });
        NailsCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuDrawerAgri.this, SubCategory.class);
                intent.putExtra("category","Nails Care");
                startActivity(intent);
            }
        });
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuDrawerAgri.this, SubCategory.class);
                intent.putExtra("category","Massage");
                startActivity(intent);
            }
        });
        makeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MenuDrawerAgri.this, SubCategory.class);
                intent.putExtra("category","Make up");
                startActivity(intent);
            }
        });
//        Tools.setSystemBarColor(this, R.color.green_600);
    }

    DrawerLayout drawer;
    private void initNavigationMenu() {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // open drawer at start
        drawer.openDrawer(GravityCompat.START);


    }

    public void onMenuClick(View view) {
        Toast.makeText(this, "On Drawer Menu Click", Toast.LENGTH_SHORT).show();
        drawer.closeDrawer(GravityCompat.START);
    }


}