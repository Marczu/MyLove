package com.marcinmejner.mylove.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcinmejner.mylove.R;
import com.marcinmejner.mylove.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 22.03.2018.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<User> users = new ArrayList<>();


    public MainRecyclerViewAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_container, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.profileName.setText("Name: " + users.get(position).getName());
        holder.profileGender.setText("Gender: " + users.get(position).getGender());
        holder.profileStatus.setText("Status: " + users.get(position).getStatus());

        Picasso.with(context)
                .load(users.get(position).getProfile_image())
                .placeholder(R.drawable.ic_love)
                .into(holder.profileImage);


    }

    @Override
    public int getItemCount() {
        return users.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profileImage;
        private TextView profileName;
        private TextView profileGender;
        private TextView profileStatus;
        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            this.profileImage = itemView.findViewById(R.id.profile_picture);
            this.profileName = itemView.findViewById(R.id.profile_name);
            this.profileGender = itemView.findViewById(R.id.profile_gender);
            this.profileStatus = itemView.findViewById(R.id.profile_status);
            this.cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
