package com.marcinmejner.mylove;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcinmejner.mylove.R;
import com.marcinmejner.mylove.Utils.MessagesRecyclerViewAdapter;
import com.marcinmejner.mylove.Utils.PreferenceKeys;
import com.marcinmejner.mylove.Utils.Users;
import com.marcinmejner.mylove.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MessagesFragment extends Fragment {
    private MessagesRecyclerViewAdapter messagesRecyclerViewAdapter;
    private RecyclerView recyclerView;

    private ArrayList<User> matches = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        recyclerView = view.findViewById(R.id.messagesRecyclerView);

        getConnections();

        return view;
    }

    private void getConnections(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, new HashSet<String>());

        Users users = new Users();
        if(matches != null){
            matches.clear();
        }
        for(User user : users.USERS){
            matches.add(user);
        }
        if(messagesRecyclerViewAdapter == null){
            initRecyclerView();
        }


    }

    private void initRecyclerView() {
        messagesRecyclerViewAdapter = new MessagesRecyclerViewAdapter(getActivity(), matches);
        recyclerView.setAdapter(messagesRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

}
