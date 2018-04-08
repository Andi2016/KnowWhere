package com.android.andi.knowwhere.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.models.User;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.gson.Gson;

public class SignUpActivity extends AppCompatActivity {

    private KnowWhere mApp;

    //UI references
    private EditText mUserNameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mSignUpButton;

    @Override
    public void onBackPressed(){
        finish();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mApp = (KnowWhere) getApplicationContext();
        setUpActionBar();
        mUserNameView = findViewById(R.id.username);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mSignUpButton = findViewById(R.id.btn_signup);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });
    }

    /**
     * This function sets up the actionbar for the screen
     */
    private void setUpActionBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Sign Up");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void attemptSignUp(){
        mUserNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);

        //Stores values at the time of the login attempt
        String userName = mUserNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(userName)){
            mUserNameView.setError("This field is required");
            focusView = mUserNameView;
            cancel = true;
        } else if(TextUtils.isEmpty(email)){
            mEmailView.setError("This field is required");
            focusView = mEmailView;
            cancel = true;
        } else if(!isEmailValid(email)){
            mEmailView.setError("This email address is invalid");
            focusView = mEmailView;
            cancel = true;
        } else if(TextUtils.isEmpty(password)){
            mEmailView.setError("This field is required");
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)){
            mPasswordView.setError("Password should at least has 8 characters");
            focusView = mPasswordView;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else {
            fetchUserDetails(userName, email, password);
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        }




    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length()>=8;
    }

    public void fetchUserDetails(String fName, String lName, String email) {
        ServerAPI.createUser(mApp.getApplicationContext(), fName, lName, email, new ServerResponseCallback() {
            @Override
            public void onResponse(ServerResponseData response) {
                if (response.statusCode == ServerAPI.STATUS_OK) {
                    User user = new Gson().fromJson(response.responseData, User.class);

                    mApp.getPreference().setAppState(SignUpActivity.this,true);
                    mApp.getPreference().storeUser(SignUpActivity.this,user);

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Show the error to the user

                }
            }
        });

    }
}
