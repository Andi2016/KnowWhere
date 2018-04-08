package com.android.andi.knowwhere.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.activities.ChatActivity;
import com.android.andi.knowwhere.adapters.ChatListAdapter;
import com.android.andi.knowwhere.models.Chat;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements ChatListAdapter.ChatListAdapterListener{

    private String mUsername;

    private Handler handler = new Handler();

    private Runnable task =new Runnable() {
        public void run() {
            handler.postDelayed(this,5*1000);
            list.clear();
            fetchAllGroups();
        }
    };

    private ChatListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private ArrayList<Chat> list = new ArrayList<>();

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_chat_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_chat);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createGroup();
            }
        });

        Bundle bundle = getArguments();
        mUsername = bundle.getString("username");

        setupView();
        fetchAllGroups();

        handler.postDelayed(task, 5000);

        return view;
    }

    /**
     * This function sets up list view
     */
    private void setupView(){

        mAdapter = new ChatListAdapter(getActivity(), ChatFragment.this);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * This function fetches Groups
     */
    private int mGroupCount = 0;
    private void fetchAllGroups(){
        mGroupCount = 0;
        ServerAPI.getGroupsByUsername(getActivity(), mUsername, new ServerResponseCallback() {
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
                            list.add(chat);
                            mGroupCount--;
                        }

                        if(mGroupCount == 0){
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

    private void createGroup(){
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(getActivity());
        }

        builder.setTitle("Creating a Group");

        //input set
        final EditText mGroupContentView = new EditText(getActivity());
        mGroupContentView.setInputType(InputType.TYPE_CLASS_TEXT);
        mGroupContentView.setHint("Group name");
        builder.setView(mGroupContentView);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String group_name = mGroupContentView.getText().toString();
                Log.e("here", group_name);
                ServerAPI.createGroup(getActivity(), group_name, mUsername, new ServerResponseCallback() {
                    @Override
                    public void onResponse(ServerResponseData response) {
                        Log.e("here", response.responseData+"  ");
                        if(response.statusCode == ServerAPI.STATUS_OK){
                            Log.e("h..", response.responseData+"  ");

                            Intent intent = new Intent(getActivity(), ChatActivity.class);
                            intent.putExtra("groupname", group_name);
                            Log.e("nearbysend", group_name);
                            startActivity(intent);
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
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        String group_name = list.get(position).getGroupname();
        intent.putExtra("groupname", group_name);
        Log.e("nearbysend", group_name);
        startActivity(intent);
    }




}
