package com.android.andi.knowwhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.andi.knowwhere.MainFragments.MainActivity;

public class SignInActivity extends AppCompatActivity {
    private EditText _email;
    private EditText _psw;
    private Button _sign_in;
    private TextView _create_user;
    private String email;
    private String psw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        _sign_in=findViewById(R.id.sign_in_button);
        _email=findViewById(R.id.email_edit);
        _psw=findViewById(R.id.psw_edit);

        _sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email= String.valueOf(_email.getText());
                psw=String.valueOf(_psw.getText());

                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
