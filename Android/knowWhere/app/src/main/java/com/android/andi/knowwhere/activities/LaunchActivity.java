package com.android.andi.knowwhere.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.application.KnowWhere;

public class LaunchActivity extends AppCompatActivity {

    private KnowWhere mApp;
    private boolean userState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mApp = (KnowWhere) getApplicationContext();
        userState = mApp.getPreference().getAppState(LaunchActivity.this);

        if(userState){
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            finish();
        }else{
            startActivity(new Intent(LaunchActivity.this, SignInActivity.class));
            finish();
        }
    }
}
