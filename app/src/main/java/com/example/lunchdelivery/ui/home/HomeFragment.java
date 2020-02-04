package com.example.lunchdelivery.ui.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lunchdelivery.R;
import com.example.lunchdelivery.Viewholder.RestaurantsViewHolder;
import com.example.lunchdelivery.model.Restaurants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.lunchdelivery.R.color.colorAccent;
import static com.example.lunchdelivery.R.color.colorPrimary;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;

    private DatabaseReference RestaurantRef;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RestaurantRef = FirebaseDatabase.getInstance().getReference().child("App Delivery");

        recyclerView=  root.findViewById(R.id.restaurantsmenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false );
        recyclerView.setLayoutManager(layoutManager);






        progressDialog =new ProgressDialog(getActivity());





//        final CardView LunchCardview = root.findViewById(R.id.lunchcardview);
//
//
//        LunchCardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LoadDataTorecyclerView();
//            }
//        });





        return root;
    }

    private void LoadDataTorecyclerView() {



        progressDialog.setTitle("Fetching data");
        progressDialog.setMessage("please wait..");
        progressDialog.show();


        FirebaseRecyclerOptions<Restaurants> options =
                new FirebaseRecyclerOptions.Builder<Restaurants>()
                        .setQuery(RestaurantRef.child("products").child("lunch").child("sellers"),Restaurants.class)
                        .build();



        FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder> adapter =new FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull final RestaurantsViewHolder holder, int position, @NonNull Restaurants model)
            {
                holder.HotelName.setText(model.getName());
                progressDialog.dismiss();

                final RecyclerView nestedrecyclerView=  holder.MenuRecylcerview;
                nestedrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false );
                nestedrecyclerView.setLayoutManager(layoutManager);



                //Get The specific Hotel Menu


                        FirebaseRecyclerOptions<Restaurants> options =
                                new FirebaseRecyclerOptions.Builder<Restaurants>()
                                        .setQuery(RestaurantRef.child("products").child("lunch").child("sellers"),Restaurants.class)
                                        .build();
                        FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder> adapter =new FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder>(options)
                        {
                            @Override
                            protected void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position, @NonNull Restaurants model)
                            {
                                Toast.makeText(getContext(), "Ndanii ", Toast.LENGTH_SHORT).show();


                            }

                            @NonNull
                            @Override
                            public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
                            {

                                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_layout,viewGroup, false);
                                RestaurantsViewHolder holder = new RestaurantsViewHolder(view);
                                return holder;
                            }
                        };

                        nestedrecyclerView.setAdapter(adapter);
                        adapter.startListening();











            }

            @NonNull
            @Override
            public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
            {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restaurants_layout,viewGroup, false);
                RestaurantsViewHolder holder = new RestaurantsViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();




    }



    /// Replace this method with general or most like hotels/restaurants
    @Override
    public void onStart() {
        super.onStart();

        progressDialog.setTitle("Refreshing");
        progressDialog.setMessage("please wait..");
        progressDialog.show();


        FirebaseRecyclerOptions<Restaurants> options =
                new FirebaseRecyclerOptions.Builder<Restaurants>()
                        .setQuery(RestaurantRef.child("products").child("lunch").child("sellers"),Restaurants.class)
                        .build();



        FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder> adapter =new FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull final RestaurantsViewHolder holder, int position, @NonNull Restaurants model)
            {
                holder.HotelName.setText(model.getName());
                progressDialog.dismiss();

                final RecyclerView nestedrecyclerView=  holder.MenuRecylcerview;
                nestedrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false );
                nestedrecyclerView.setLayoutManager(layoutManager);



                //Get The specific Hotel Menu


                FirebaseRecyclerOptions<Restaurants> options =
                        new FirebaseRecyclerOptions.Builder<Restaurants>()
                                .setQuery(RestaurantRef.child("products").child("lunch").child("sellers"),Restaurants.class)
                                .build();
                FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder> adapter =new FirebaseRecyclerAdapter<Restaurants, RestaurantsViewHolder>(options)
                {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    protected void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position, @NonNull Restaurants model)
                    {
                        Toast.makeText(getContext(), "Ndanii ", Toast.LENGTH_SHORT).show();


                    }

                    @NonNull
                    @Override
                    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
                    {

                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_layout,viewGroup, false);
                        RestaurantsViewHolder holder = new RestaurantsViewHolder(view);
                        return holder;
                    }
                };

                nestedrecyclerView.setAdapter(adapter);
                adapter.startListening();

            }

            @NonNull
            @Override
            public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
            {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restaurants_layout,viewGroup, false);
                RestaurantsViewHolder holder = new RestaurantsViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }
}