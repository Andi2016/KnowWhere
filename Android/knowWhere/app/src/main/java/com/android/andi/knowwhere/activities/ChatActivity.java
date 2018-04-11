package com.android.andi.knowwhere.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.adapters.ChatListAdapter;
import com.android.andi.knowwhere.adapters.MessageAdapter;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.fragments.ChatFragment;
import com.android.andi.knowwhere.fragments.HomeFragment;
import com.android.andi.knowwhere.models.Chat;
import com.android.andi.knowwhere.models.Message;
import com.android.andi.knowwhere.models.User;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChatActivity extends AppCompatActivity implements MessageAdapter.MessageAdapterListener {

    private String group_name;
    private ArrayList<Chat> groupList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private MessageAdapter mAdapter;
    private EditText mChatboxView;
    private Button mSendButtonView;

    private KnowWhere mApp;
    private User mUser;

    private Handler handler = new Handler();

    private Runnable task =new Runnable() {
        public void run() {
            handler.postDelayed(this,5*1000);
            fetchMessages();
        }
    };

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
        //getFireBaseData();
        setupView();
        fetchMessages();
        sendMessage();

        //temp
       //handler.postDelayed(task, 5000);
    }

    /**
     * This function listens to new message
     */
//    private void getFireBaseData(){
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference firebaseUser = mDatabase.child("test");
//        firebaseUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("change", "changed");
//                fetchMessages();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }



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
            if(group_name.contains("|")){
                String name_display = group_name;
                name_display =  name_display.replaceAll("\\|", "");
                Log.e("dfs", name_display);
                name_display = name_display.replaceAll(mUser.getUsername(), "");
                getSupportActionBar().setTitle(name_display);
                Log.e("dfs", name_display);
            }else if(group_name.contains("%7C")){
                String name_display = group_name;
                name_display =  name_display.replaceAll("%7C", "").replaceAll(mUser.getUsername(), "");
                getSupportActionBar().setTitle(name_display);
            } else{
                getSupportActionBar().setTitle(group_name);
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //handler.removeCallbacks(task);
                finish();
                break;
            case R.id.action_invite:
                addFriend();
                break;
        }
        return super.onOptionsItemSelected(item);
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
                            //Toast.makeText(mApp, list.size()+"", Toast.LENGTH_SHORT).show();
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
                if(group_name.contains("|")){
                    group_name = group_name.replaceAll("\\|", "%7C");
                }
                if(content.equals("")){
                    return;
                }
                final Message message = new Message(mUser.getUsername(), group_name ,content);
                ServerAPI.sendMessage(mApp, message, new ServerResponseCallback() {
                    @Override
                    public void onResponse(ServerResponseData response) {
                        if(response.statusCode == ServerAPI.STATUS_OK){
                            fetchMessages();
                            mChatboxView.setText("");
                        }
                    }
                });

            }
        });
    }

    /**
     * This function fetches Groups
     */
    private int mGroupCount = 0;
    private void fetchAllGroups(){
        mGroupCount = 0;
        ServerAPI.getGroupsByUsername(mApp, mUser.getUsername(), new ServerResponseCallback() {
            @Override
            public void onResponse(ServerResponseData response) {
                if(response.statusCode == ServerAPI.STATUS_OK){
                    Log.e("ddd", response.responseData+" ");
                    try{
                        JSONArray jsonArray = new JSONArray(response.responseData);
                        mGroupCount = jsonArray.length();
                        if(mGroupCount == 0){
                            mAdapter.clearItems();
                            mAdapter.notifyDataSetChanged();
                        }

                        for(int i = 0; i < jsonArray.length(); i++){
                            Chat chat = new Gson().fromJson(jsonArray.get(i).toString(), Chat.class);
                            groupList.add(chat);
                            mGroupCount--;
                        }

                        if(mGroupCount == 0){
                            mAdapter.addItems(groupList);
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
     * This function add user to group
     */
    private void addFriend(){
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(ChatActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(ChatActivity.this);
        }

        builder.setTitle("Inviting friend");

        //input set
        final EditText mUsernameContentView = new EditText(ChatActivity.this);
        mUsernameContentView.setInputType(InputType.TYPE_CLASS_TEXT);
        mUsernameContentView.setHint("Username ...");
        builder.setView(mUsernameContentView);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String user_name = mUsernameContentView.getText().toString();
                Log.e("here", group_name);
                ServerAPI.addFriendtoGroup(ChatActivity.this, group_name, user_name, new ServerResponseCallback() {
                   @Override
                   public void onResponse(ServerResponseData response) {
                       if(response.statusCode == ServerAPI.STATUS_OK){

                       }
                   }
               });
            }
        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }



    @Override
    public void OnClick(int position) {

    }


}
