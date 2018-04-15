package com.android.andi.knowwhere.Contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.RestfulUtilities;

public class AddFriendActivity extends AppCompatActivity {

    EditText _username;
    Button _add;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        _username=findViewById(R.id.username_add);
        _add=findViewById(R.id.add_button);

        _add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username= String.valueOf(_username.getText());
                try {
                    RestfulUtilities.addFriend("Joe", "Tina");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();

            }
        });

    }
}
