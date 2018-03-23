package com.marcinmejner.mylove.Utils;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcinmejner.mylove.IMainActivity;
import com.marcinmejner.mylove.R;
import com.marcinmejner.mylove.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 23.03.2018.
 */

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<User> users = new ArrayList<>();
    private List<User> filteredUsers = new ArrayList<>();
    private IMainActivity iMainActivity;

    public MessagesRecyclerViewAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        this.filteredUsers = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_messages_container, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.messagesProfilename.setText(users.get(position).getName());
        Picasso.with(context)
                .load(users.get(position).getProfile_image())
                .placeholder(R.drawable.ic_love)
                .into(holder.messagesProfileImage);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView messagesProfileImage;
        private TextView messagesProfilename;

        public ViewHolder(View itemView) {
            super(itemView);
            this.messagesProfileImage = itemView.findViewById(R.id.message_profile_picture);
            this.messagesProfilename = itemView.findViewById(R.id.message_profile_name);
        }
    }
}
