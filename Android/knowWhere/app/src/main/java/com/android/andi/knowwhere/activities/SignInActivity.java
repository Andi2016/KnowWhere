package com.android.andi.knowwhere.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.models.User;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerInterface;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class SignInActivity extends AppCompatActivity {

    private KnowWhere mApp;

    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mSignInButton;
    private TextView mSignUpButton;
    private String password;
    private String password_server;
    private User mUser;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mApp = (KnowWhere) getApplicationContext();

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);

        mSignInButton = findViewById(R.id.btn_login);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignIn();
            }
        });

        mSignUpButton = findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }

        /**
         * This function attempts to sign up
         */
        private void attemptSignIn(){

            //reset errors
            mEmailView.setError(null);
            mPasswordView.setError(null);

            //store value for login attempt
            mUsername = mEmailView.getText().toString();
            password = mPasswordView.getText().toString();

            boolean cancel = false;
            View focusView = null;

            //check for valid input
            if(TextUtils.isEmpty(mUsername)){
                mEmailView.setError("This field is required");
                focusView = mEmailView;
                cancel = true;
            }else if(TextUtils.isEmpty(password)){
                mPasswordView.setError("This field is required");
                focusView = mPasswordView;
                cancel = true;
            }

            if(cancel){
                focusView.requestFocus();
            }else{


                ServerAPI.getUserByUsername(mApp, mUsername, new ServerResponseCallback() {
                    @Override
                    public void onResponse(ServerResponseData response) {

                        if(response.statusCode == ServerAPI.STATUS_OK){
                            mUser = new Gson().fromJson(response.responseData, User.class);

                            password_server = mUser.getPassword();
                            Log.e("response", mUser.getPassword());
                        }
                    }
                });

                if(checkPassword(password, password_server)){
                    //save this user
                    mApp.getPreference().setAppState(SignInActivity.this,true);
                    mApp.getPreference().storeUser(mApp, mUser);

                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(mApp, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }

        }

        private boolean checkPassword(String password_client, String password_server){
            return(password_client.equals(password_server));
        }

}
