package com.developer.companyproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PreviousBooking extends Fragment {

    RecyclerView recyclerView;
    BookingAdapter bookingAdapter;
    ArrayList<BookingModel> arrayList= new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_previous_booking, container, false);

//        arrayList.add(new BookingModel(
//                "Hair Care",
//                "Hot Oil package (4 Times)",
//                "Sat, Jun 13, 2020 7:00 AM",
//                "5 OMR"
//        ));
//        arrayList.add(new BookingModel(
//                "Hair Care",
//                "Hot Oil + cream",
//                "Sun, Jun 14, 2020 9:00 AM",
//                "7 OMR"
//        ));
//        arrayList.add(new BookingModel(
//                "Hair Care",
//                "Hot Oil Khashmiri",
//                "Mon, Jun 15, 2020 9:00 AM",
//                "20 OMR"
//        ));

        recyclerView=view.findViewById(R.id.rv_previous_booking);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        bookingAdapter = new BookingAdapter(getContext(),arrayList);
//        recyclerView.setAdapter(bookingAdapter);
        if(AppData.userType.equals("user")){
            bookingList();
        }else {
            bookingListAdmin();
        }

        return view;
    }
    private void bookingList() {
        final List<BookingModel> bookingModelList=new ArrayList<>();
        Query query = FirebaseDatabase.getInstance().getReference("BookedData");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingModelList.clear();
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                    if(childSnapshot.child("userId").getValue(String.class).equals(AppData.userId)) {
                        final Calendar defaultSelectedDate = Calendar.getInstance();
                        SimpleDateFormat curFormater = new SimpleDateFormat("EEE, MMM d, yyyy");
                        try {
                            Date dataBaseDate= curFormater.parse(childSnapshot.child("date").getValue(String.class));
                            Date currentDate = curFormater.parse( DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());

                            if(dataBaseDate.before(currentDate) || childSnapshot.child("status").getValue(String.class).equals("2")){
                                bookingModelList.add(new BookingModel(
                                        childSnapshot.getKey(),
                                        childSnapshot.child("category").getValue(String.class),
                                        childSnapshot.child("subCategory").getValue(String.class),
                                        childSnapshot.child("date").getValue(String.class),
                                        childSnapshot.child("Time").getValue(String.class),
                                        childSnapshot.child("payment").getValue(String.class),
                                        childSnapshot.child("address").getValue(String.class),
                                        childSnapshot.child("contact").getValue(String.class),
                                        childSnapshot.child("status").getValue(String.class)
                                ));

                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        bookingAdapter = new BookingAdapter(getContext(), bookingModelList);
                        recyclerView.setAdapter(bookingAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void bookingListAdmin() {
        final List<BookingModel> bookingModelList=new ArrayList<>();
        Query query = FirebaseDatabase.getInstance().getReference("BookedData");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingModelList.clear();
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                        final Calendar defaultSelectedDate = Calendar.getInstance();
                        SimpleDateFormat curFormater = new SimpleDateFormat("EEE, MMM d, yyyy");
                        try {
                            Date dataBaseDate= curFormater.parse(childSnapshot.child("date").getValue(String.class));
                            Date currentDate = curFormater.parse( DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());

                            if(dataBaseDate.before(currentDate) || childSnapshot.child("status").getValue(String.class).equals("2")){
                                bookingModelList.add(new BookingModel(
                                        childSnapshot.getKey(),
                                        childSnapshot.child("category").getValue(String.class),
                                        childSnapshot.child("subCategory").getValue(String.class),
                                        childSnapshot.child("date").getValue(String.class),
                                        childSnapshot.child("Time").getValue(String.class),
                                        childSnapshot.child("payment").getValue(String.class),
                                        childSnapshot.child("address").getValue(String.class),
                                        childSnapshot.child("contact").getValue(String.class),
                                        childSnapshot.child("status").getValue(String.class)
                                ));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        bookingAdapter = new BookingAdapter(getContext(), bookingModelList);
                        recyclerView.setAdapter(bookingAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}