package com.developer.companyproject;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class Booking extends AppCompatActivity {
    public String selectedDateStr;
    public String time;
    EditText etName, etTime, etContact, etEmail, etAddress;
    Button btnBook;
    int mHour;
    int mMinute;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    Date currentDate;
    Date selectDate;
    private HorizontalCalendar horizontalCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();
        init();
        setListener();
        /* start 2 months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -2);

        /* end after 2 months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.LTGRAY, Color.WHITE)
                .colorTextMiddle(Color.LTGRAY, Color.parseColor("#ffd54f"))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .addEvents(new CalendarEventsPredicate() {

                    Random rnd = new Random();

                    @Override
                    public List<CalendarEvent> events(Calendar date) {
                        List<CalendarEvent> events = new ArrayList<>();
                        int count = rnd.nextInt(6);

                        for (int i = 0; i <= count; i++) {
                            events.add(new CalendarEvent(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), "event"));
                        }

                        return events;
                    }
                })
                .build();

        Log.i("Default Date", DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());
        selectedDateStr=DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString();
        SimpleDateFormat curFormater = new SimpleDateFormat("EEE, MMM d, yyyy");
        try {
            currentDate = curFormater.parse( DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());
            selectDate= curFormater.parse( DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        currentDate= (Date) DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate);

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                SimpleDateFormat curFormater = new SimpleDateFormat("EEE, MMM d, yyyy");
                try {
                    selectDate= curFormater.parse( DateFormat.format("EEE, MMM d, yyyy", date).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Toast.makeText(Booking.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                Log.i("onDateSelected", selectedDateStr + " - Position = " + position);
            }

        });

    }

    private void setListener() {

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiemPicker();
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(Booking.this, "Please fill input", Toast.LENGTH_SHORT).show();
                    return;
                } else if (etContact.getText().toString().isEmpty()) {
                    Toast.makeText(Booking.this, "Please fill input", Toast.LENGTH_SHORT).show();
                    return;
                } else if (etEmail.getText().toString().isEmpty() || !isValidEmail(etEmail.getText().toString())) {
                    Toast.makeText(Booking.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                } else if (etAddress.getText().toString().isEmpty()) {
                    Toast.makeText(Booking.this, "Please fill input", Toast.LENGTH_SHORT).show();
                    return;
                } else if (etTime.getText().toString().isEmpty()) {
                    Toast.makeText(Booking.this, "Please fill input", Toast.LENGTH_SHORT).show();
                    return;
                } else if(selectDate.before(currentDate)){
                    Toast.makeText(Booking.this, "Please Select Upcoming date", Toast.LENGTH_SHORT).show();
                   return;
                }else{

                    bookNow(selectedDateStr,time,etName.getText().toString(),etContact.getText().toString(),etEmail.getText().toString(),etAddress.getText().toString());
                }


            }
        });

    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void init() {
        etName = findViewById(R.id.etName);
        etTime = findViewById(R.id.et_time);
        etContact = findViewById(R.id.etContactNo);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        btnBook = findViewById(R.id.btnBook);

    }

    private void tiemPicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay >= 0 && hourOfDay < 12) {
                            time = hourOfDay + ":" + minute + " am";
                        } else {
                            if (hourOfDay == 12) {
                                time = hourOfDay + ":" + minute + " pm";
                            } else {
                                hourOfDay = hourOfDay - 12;
                                time = hourOfDay + ":" + minute + " pm";
                            }
                        }
                        etTime.setText(time);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    void bookNow(String date, String time, String personName, String contactNo, String email, String address) {
        HashMap<String, String> param = new HashMap<>();
        param.put("userId", AppData.userId);
        param.put("category", getIntent().getStringExtra("category"));
        param.put("subCategory", getIntent().getStringExtra("subCategory"));
        param.put("date", date);
        param.put("Time", time);
        param.put("personName", personName);
        param.put("email", email);
        param.put("contact", contactNo);
        param.put("address", address);
        param.put("payment", getIntent().getStringExtra("price"));
        param.put("status", "0");

        DatabaseReference newRef = mRef.child("BookedData").push();
        newRef.setValue(param).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Booking.this, " Booking successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Booking.this,SubCategory.class);
                    intent.putExtra("category",getIntent().getStringExtra("category"));
                    finish();

                } else {
                    Toast.makeText(Booking.this, "Failed some went wrong", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}