package com.example.lunchdelivery.Viewholder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.lunchdelivery.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantsViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {


    public AdapterView.OnItemClickListener listener;

    public TextView HotelName;
    public ImageView profimageView;
    public RecyclerView MenuRecylcerview;
    public ElegantNumberButton elegantNumberButton;


    public RestaurantsViewHolder(@NonNull View itemView) {
        super(itemView);


        HotelName = itemView.findViewById(R.id.restaurantName);
        MenuRecylcerview = itemView.findViewById(R.id.menurecyclerview);

        elegantNumberButton = itemView.findViewById(R.id.numberbutton);


    }

    @Override
    public void onClick(View view) {

    }
}
