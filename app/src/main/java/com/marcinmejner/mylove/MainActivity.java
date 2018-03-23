package com.marcinmejner.mylove;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.marcinmejner.mylove.Utils.PreferenceKeys;
import com.marcinmejner.mylove.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivity, BottomNavigationViewEx.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";
    private BottomNavigationViewEx bottomNavigationViewEx;
    
    private HomeFragment homeFragment;
    private SavedConnectionsFragment savedConnectionsFragment;
    private MessagesFragment messagesFragment;

    private ArrayList<String> fragmentTags = new ArrayList<>();
    private ArrayList<FragmentTag> fragments = new ArrayList<>();

    private int exitCount = 0;
    private ViewProfileFragment viewProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewEx = findViewById(R.id.bot_nav_view);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);

        isFirstLogin();
        initBotNavigationView();
        fragmentSetup();
    }

    private void initBotNavigationView(){
        bottomNavigationViewEx.enableAnimation(false);

    }

    public void isFirstLogin() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin = sharedPreferences.getBoolean(PreferenceKeys.FIRST_LOGIN, true);

        if (isFirstLogin) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("If this is your first login, make sure to input your preferences")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d(TAG, "onClick: closing dialog");
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(PreferenceKeys.FIRST_LOGIN, false)
                                    .commit();
                            dialogInterface.dismiss();
                        }
                    });
            alertDialog.setIcon(R.drawable.ic_love)
                    .setTitle(" ");
            AlertDialog dialog = alertDialog.create();
            dialog.show();
        }

    }

    private void setFragmentsVisibility(String tagName){

        for (int i = 0; i < fragments.size(); i++) {

            if(tagName.equals(fragments.get(i).getTag())){

                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.show(fragments.get(i).getFragment())
                        .commit();
            }else {
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragments.get(i).getFragment())
                        .commit();
            }

        }



    }

    public void fragmentSetup() {


            if(homeFragment == null) {

                homeFragment = new HomeFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_content_frame, homeFragment, "Home")
                        .commit();
                fragmentTags.add("Home");

                fragments.add(new FragmentTag(homeFragment, "Home"));
            }
            else{
                fragmentTags.remove("Home");
                fragmentTags.add("Home");
            }
            setFragmentsVisibility("Home");

    }

    @Override
    public void inflateViewProfileFragment(User user) {
//        viewProfileFragment = new ViewProfileFragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("userIntent", user);
//        viewProfileFragment.setArguments(bundle);
//
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.main_content_frame, viewProfileFragment, "setFragmentsVisibility")
//                .commit();
//
//        setFragmentsVisibility("setFragmentsVisibility");






//
        if(viewProfileFragment == null) {


            viewProfileFragment = new ViewProfileFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable("userIntent", user);
            viewProfileFragment.setArguments(bundle);

            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.main_content_frame, viewProfileFragment, "setFragmentsVisibility")
                    .commit();

            fragmentTags.add("setFragmentsVisibility");

            fragments.add(new FragmentTag(viewProfileFragment, "setFragmentsVisibility"));
        }
        else{
            fragmentTags.remove("setFragmentsVisibility");
            fragmentTags.add("setFragmentsVisibility");
        }
        setFragmentsVisibility("setFragmentsVisibility");





    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.bottom_nav_home: {
                Log.d(TAG, "onNavigationItemSelected: home fragmen test");
                item.setChecked(true);

                if(homeFragment == null) {

                    homeFragment = new HomeFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.main_content_frame, homeFragment, "Home")
                            .commit();
                    fragmentTags.add("Home");

                    fragments.add(new FragmentTag(homeFragment, "Home"));
                }
                else{
                    fragmentTags.remove("Home");
                    fragmentTags.add("Home");
                }
                setFragmentsVisibility("Home");
                break;
            }
            case R.id.bottom_nav_connections: {
                Log.d(TAG, "onNavigationItemSelected: conect fragmen test");
                item.setChecked(true);



                if(savedConnectionsFragment == null) {

                    savedConnectionsFragment = new SavedConnectionsFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.main_content_frame, savedConnectionsFragment, "Saved")
                            .commit();
                    fragmentTags.add("Saved");

                    fragments.add(new FragmentTag(savedConnectionsFragment, "Saved"));
                }
                else{
                    fragmentTags.remove("Saved");
                    fragmentTags.add("Saved");
                }
                setFragmentsVisibility("Saved");
                break;




            }
            case R.id.bottom_nav_messages: {
                Log.d(TAG, "onNavigationItemSelected: messages fragmen test");
                item.setChecked(true);


                if(messagesFragment == null) {

                    messagesFragment = new MessagesFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.main_content_frame, messagesFragment, "Message")
                            .commit();
                    fragmentTags.add("Message");

                    fragments.add(new FragmentTag(messagesFragment, "Message"));
                }
                else{
                    fragmentTags.remove("Message");
                    fragmentTags.add("Message");
                }
                setFragmentsVisibility("Message");
                break;
            }

        }

        return false;
    }

    @Override
    public void onBackPressed() {


        int backStackCount = fragmentTags.size();
        if(backStackCount > 1){

            String topFragmentTag = fragmentTags.get(backStackCount - 1);
            String newTopFragmentStack = fragmentTags.get(backStackCount -2);

            setFragmentsVisibility(newTopFragmentStack);
            fragmentTags.remove(topFragmentTag);
        }else if(backStackCount == 1){
            exitCount++;
            Toast.makeText(this, "One more click to exit", Toast.LENGTH_SHORT).show();
        }
        if (exitCount >= 2){
            super.onBackPressed();
        }
    }
}
