package com.developer.companyproject;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.HairCareViewHolder> {
    public Context context;
    public List<BookingModel> bookingModelList;

    public BookingAdapter(Context context, List<BookingModel> bookingModelList) {
        this.context = context;
        this.bookingModelList = bookingModelList;

    }

    @NonNull
    @Override
    public HairCareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_booking, parent, false);


        return new HairCareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HairCareViewHolder holder, final int position) {

        if (AppData.userType.equals("user")) {
            holder.tv_category.setText(bookingModelList.get(position).category);
            holder.tv_sub_category.setText(bookingModelList.get(position).subCategory);
            holder.tv_date_time.setText(bookingModelList.get(position).date + " " + bookingModelList.get(position).time);
            holder.tv_price.setText(bookingModelList.get(position).price);
            holder.tv_address.setVisibility(View.GONE);
            holder.tv_contact.setVisibility(View.GONE);
            holder.tv_rejected.setVisibility(View.GONE);
            holder.tv_approved.setVisibility(View.GONE);
            holder.tv_complete.setVisibility(View.GONE);

            if (bookingModelList.get(position).status.equals("0")) {
                holder.tv_status.setText("Pending");

            } else if (bookingModelList.get(position).status.equals("1")) {
                holder.tv_status.setText("Approved");

            } else if (bookingModelList.get(position).status.equals("2")) {
                holder.tv_status.setText("Completed");
            }else{
                holder.tv_status.setText("Rejected");

            }
        }else{
            holder.tv_contact.setText("Phone No :"+bookingModelList.get(position).contact);
            holder.tv_address.setText(bookingModelList.get(position).address);
            holder.tv_category.setText(bookingModelList.get(position).category);
            holder.tv_sub_category.setText(bookingModelList.get(position).subCategory);
            holder.tv_date_time.setText(bookingModelList.get(position).date + " " + bookingModelList.get(position).time);
            holder.tv_price.setText(bookingModelList.get(position).price);
            if (bookingModelList.get(position).status.equals("0")) {
                holder.tv_status.setText("Pending");
                holder.tv_complete.setVisibility(View.VISIBLE);
                holder.tv_rejected.setVisibility(View.VISIBLE);
                holder.tv_approved.setVisibility(View.VISIBLE);

            } else if (bookingModelList.get(position).status.equals("1")) {
                holder.tv_status.setText("Approved");
                holder.tv_complete.setVisibility(View.GONE);
                holder.tv_rejected.setVisibility(View.GONE);
                holder.tv_approved.setVisibility(View.GONE);

            } else if (bookingModelList.get(position).status.equals("2")) {
                holder.tv_status.setText("Completed");
                holder.tv_complete.setVisibility(View.GONE);
                holder.tv_rejected.setVisibility(View.GONE);
                holder.tv_approved.setVisibility(View.GONE);
            }else{
                holder.tv_status.setText("Rejected");
                holder.tv_rejected.setVisibility(View.GONE);
                holder.tv_complete.setVisibility(View.GONE);
                holder.tv_approved.setVisibility(View.GONE);

            }
            holder.tv_approved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editData(bookingModelList.get(position).bookingId,"1");
                }
            });

            holder.tv_rejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editData(bookingModelList.get(position).bookingId,"3");

                }
            });
            holder.tv_complete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editData(bookingModelList.get(position).bookingId,"2");

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return bookingModelList.size();
    }

    class HairCareViewHolder extends RecyclerView.ViewHolder {
        TextView tv_category, tv_sub_category, tv_date_time, tv_price, tv_address, tv_status, tv_contact, tv_complete, tv_rejected, tv_approved;


        public HairCareViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.title);
            tv_sub_category = itemView.findViewById(R.id.tv_sub_cat);
            tv_date_time = itemView.findViewById(R.id.tv_date);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_contact = itemView.findViewById(R.id.tv_contact);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_approved = itemView.findViewById(R.id.tv_approve);
            tv_rejected = itemView.findViewById(R.id.tv_reject);
            tv_complete = itemView.findViewById(R.id.tv_completed);


        }
    }
    private void editData(String id, final String status) {
        DatabaseReference mRef;
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();
        Query editQuery = mRef.child("BookedData").orderByKey().equalTo(id);
        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot edtData : dataSnapshot.getChildren()) {
                    edtData.getRef().child("status").setValue(status);
                }
              if(status.equals("1")){

              }else  if(status.equals("2")) {
                  bookingListCurrentAdmin();
              }else{
                  bookingPreviousListAdmin();
              }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bookingListCurrentAdmin() {
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

                        if((dataBaseDate.after(currentDate) || dataBaseDate.equals(currentDate)) && !childSnapshot.child("status").getValue(String.class).equals("2")){
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


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void bookingPreviousListAdmin() {
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

                        if(dataBaseDate.before(currentDate) || childSnapshot.child("status").getValue(String.class).equals("2") ){
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


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
