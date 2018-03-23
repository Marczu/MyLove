package com.marcinmejner.mylove;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcinmejner.mylove.model.User;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfileFragment extends Fragment {
    private static final String TAG = "ViewProfileFragment";

    private User user;

    @BindView(R.id.detail_profile_name) TextView detailProfileName;
    @BindView(R.id.detail_profile_gender) TextView detailProfileGender;
    @BindView(R.id.detail_profile_status) TextView detailProfilestatus;
    @BindView(R.id.detail_profile_picture)
    CircleImageView detailProfilePicture;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            user = bundle.getParcelable("userIntent");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        ButterKnife.bind(this, view);

        detailProfileName.setText("Name: " +user.getName());
        detailProfileGender.setText("Gender: " + user.getGender());
        detailProfilestatus.setText("Status: " + user.getStatus());

        Picasso.with(getActivity())
                .load(user.getProfile_image())
                .placeholder(R.drawable.ic_love)
                .into(detailProfilePicture);




        return view;
    }


}
