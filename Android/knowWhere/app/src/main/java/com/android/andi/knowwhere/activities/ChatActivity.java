package com.android.andi.knowwhere.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.adapters.ChatListAdapter;
import com.android.andi.knowwhere.adapters.MessageAdapter;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.fragments.ChatFragment;
import com.android.andi.knowwhere.models.Chat;
import com.android.andi.knowwhere.models.Message;
import com.android.andi.knowwhere.models.User;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements MessageAdapter.MessageAdapterListener {

    private String group_name;
    private RecyclerView mRecyclerView;
    private MessageAdapter mAdapter;
    private EditText mChatboxView;
    private Button mSendButtonView;

    private KnowWhere mApp;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mApp = (KnowWhere) getApplicationContext();
        mUser = mApp.getPreference().getUser(mApp.getApplicationContext());

        mRecyclerView = findViewById(R.id.message_recycler_view);
        mChatboxView = findViewById(R.id.edittext_chatbox);
        mSendButtonView = findViewById(R.id.button_chatbox_send);

        Intent intent = getIntent();
        group_name = intent.getStringExtra("groupname");

        setUpActionBar();
        setupView();
        fetchMessages();
        sendMessage();



    }

    /**
     * This function sets up Recycler view
     */
    private void setupView(){

        mAdapter = new MessageAdapter(mApp, ChatActivity.this);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mApp);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * This function sets up the actionbar for the screen
     */
    private void setUpActionBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(group_name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        }
    }

    /**
     * This function fetch Messages under this group;
     */
    private int mMessageCount;
    private void fetchMessages(){
        ServerAPI.getMessagesByGroupName(mApp, group_name, new ServerResponseCallback() {
            @Override
            public void onResponse(ServerResponseData response) {
                Log.e("jjfj", response.responseData);
                if(response.statusCode == ServerAPI.STATUS_OK){
                    try{
                        final ArrayList<Chat> list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.responseData);
                        mMessageCount = jsonArray.length();
                        if(mMessageCount == 0){
                            mAdapter.clearItems();
                            mAdapter.notifyDataSetChanged();
                        }

                        for(int i = 0; i < jsonArray.length(); i++){
                            Chat chat = new Gson().fromJson(jsonArray.get(i).toString(), Chat.class);
                            list.add(chat);
                            mMessageCount--;
                        }

                        if(mMessageCount == 0){
                            Toast.makeText(mApp, list.size()+"", Toast.LENGTH_SHORT).show();
                            mAdapter.addItems(list);
                            mAdapter.notifyDataSetChanged();
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * This function send user's message
     */
    private void sendMessage(){
        mSendButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mChatboxView.getText().toString();
                if(content.equals("")){
                    return;
                }
                final Message message = new Message(mUser.getUsername(), group_name ,content);
                ServerAPI.sendMessage(mApp, message, new ServerResponseCallback() {
                    @Override
                    public void onResponse(ServerResponseData response) {
                        if(response.statusCode == ServerAPI.STATUS_OK){
                            fetchMessages();
                        }
                    }
                });
            }
        });
    }


    @Override
    public void OnClick(int position) {

    }


}
