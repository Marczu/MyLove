package com.marcinmejner.mylove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.marcinmejner.mylove.Utils.PreferenceKeys;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFirstLogin();
        fragmentSetup();
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

    public void fragmentSetup() {
        HomeFragment homeFragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content_frame, homeFragment, "Home")
                .addToBackStack("home")
                .commit();

    }
}
