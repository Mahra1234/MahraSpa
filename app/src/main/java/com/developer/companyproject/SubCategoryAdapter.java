package com.developer.companyproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.HairCareViewHolder> {
    public Context context;
    public List<SubCategoryModel> hairCareList;
    DatabaseReference mRef;
    String title;

    public SubCategoryAdapter(Context context, String title, List<SubCategoryModel> hairCareList) {
        this.context = context;
        this.hairCareList = hairCareList;
        this.title = title;

    }

    @NonNull
    @Override
    public HairCareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_sub_category_card, parent, false);


        return new HairCareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HairCareViewHolder holder, final int position) {

        holder.tv_service.setText(hairCareList.get(position).service);
        holder.tv_price.setText(hairCareList.get(position).price);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Booking.class);
                intent.putExtra("subCategory", hairCareList.get(position).service);
                intent.putExtra("price", hairCareList.get(position).price);
                intent.putExtra("category", title);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return hairCareList.size();
    }

    class HairCareViewHolder extends RecyclerView.ViewHolder {
        TextView tv_service, tv_price;
        Button image;

        public HairCareViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tv_service = itemView.findViewById(R.id.title);
            tv_price = itemView.findViewById(R.id.tv_price);


        }
    }

}
