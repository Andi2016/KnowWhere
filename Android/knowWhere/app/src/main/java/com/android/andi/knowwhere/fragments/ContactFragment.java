package com.android.andi.knowwhere.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.activities.ChatActivity;
import com.android.andi.knowwhere.adapters.ContactListAdapter;
import com.android.andi.knowwhere.models.Contact;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ContactFragment extends Fragment implements ContactListAdapter.ContactListAdapterListener{

    //UI references
    private RecyclerView mRecyclerView;

    private String mUsername;
    private ContactListAdapter mAdapter;
    //private ArrayList<Contact> list = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();

    public static Fragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_contact_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler_view);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_contact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFriend();
            }
        });

        Bundle bundle = getArguments();
        mUsername = bundle.getString("username");

        setupView();
        fetchFriends();



        return view;
    }

    /**
     * This function sets up list view
     */
    private void setupView(){

        mAdapter = new ContactListAdapter(getActivity(), ContactFragment.this);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * This function adds new friend by username
     */
    private void addFriend(){
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(getActivity());
        }

        builder.setTitle("Add Friend");

        //input set
        final EditText mFriendContentView = new EditText(getActivity());
        mFriendContentView.setInputType(InputType.TYPE_CLASS_TEXT);
        mFriendContentView.setHint("Username...");
        builder.setView(mFriendContentView);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String friend_name = mFriendContentView.getText().toString();
                ServerAPI.addFriend(getActivity(), mUsername, friend_name, new ServerResponseCallback() {
                    @Override
                    public void onResponse(ServerResponseData response) {
                        if(response.statusCode == ServerAPI.STATUS_OK){
                            list.clear();
                            fetchFriends();
                        }else{
                            Toast.makeText(getActivity(), "No such user", Toast.LENGTH_SHORT).show();
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

    /**
     * fetch all friends
     */
    private int mFriendCount = 0;
    private void fetchFriends(){
        mFriendCount = 0;

        ServerAPI.getFriends(getActivity(), mUsername, new ServerResponseCallback() {
            @Override
            public void onResponse(ServerResponseData response) {
                if(response.statusCode == ServerAPI.STATUS_OK){
                    Log.e("kk", response.responseData);
                    try{
                        JSONArray jsonArray = new JSONArray(response.responseData);
                        mFriendCount = jsonArray.length();
                        if(mFriendCount == 0){
                            mAdapter.clearItems();
                            mAdapter.notifyDataSetChanged();
                        }

                        for(int i = 0; i < jsonArray.length(); i++){
                            //Contact contact = new Gson().fromJson(jsonArray.get(i).toString(), Contact.class);
                            list.add(jsonArray.get(i).toString());
                            mFriendCount--;
                        }


                        if(mFriendCount == 0){
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

    @Override
    public void OnClick(int position) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        String username2 = list.get(position);
        String group_name = mUsername.compareTo(username2) < 0 ? mUsername + "|" + username2 : username2 + "|" + mUsername;
        intent.putExtra("groupname", group_name);
        startActivity(intent);

    }
}
