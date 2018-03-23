package com.marcinmejner.mylove;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcinmejner.mylove.Utils.MainRecyclerViewAdapter;
import com.marcinmejner.mylove.Utils.PreferenceKeys;
import com.marcinmejner.mylove.Utils.Users;
import com.marcinmejner.mylove.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SavedConnectionsFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    public static final int NUM_COLLUMNS = 2;
    private RecyclerView recyclerView;

    private ArrayList matches = new ArrayList();
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saved_connections, container, false );

        recyclerView = view.findViewById(R.id.recyclerView);

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
        if(mainRecyclerViewAdapter == null){
            initRecyclerView();
        }


    }

    private void initRecyclerView() {

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), matches);
        recyclerView.setAdapter(mainRecyclerViewAdapter);




    }

}
