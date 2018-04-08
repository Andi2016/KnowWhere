package com.android.andi.knowwhere.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.activities.MainActivity;
import com.android.andi.knowwhere.models.User;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerInterface;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.gson.Gson;


public class HomeFragment extends Fragment {

    //UI references
    private TextView mUsernameView;
    private Switch mStatusView;

    private ImageView mHeadView;

    private String mUsername;
    private User mUser;
    private String mStatus = "";

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_home_fragment, container, false);

        mUsernameView = view.findViewById(R.id.username_profile);
        mStatusView = view.findViewById(R.id.switch_view);
        mHeadView = view.findViewById(R.id.head_profile);
        Bundle bundle = getArguments();
        mUsername = bundle.getString("username");

        mUsernameView.setText(mUsername);
        mHeadView.setImageResource(MainActivity.map.get(mUsername));

        getStatus();

        mStatusView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mStatusView.setChecked(true);
                    postStatus("available");
                }else{
                    mStatusView.setChecked(false);
                    postStatus("offline");
                }
            }
        });

        return view;
    }

    private void getStatus(){
        ServerAPI.getUserByUsername(getActivity(), mUsername, new ServerResponseCallback() {
            @Override
            public void onResponse(ServerResponseData response) {
                if(response.statusCode == ServerAPI.STATUS_OK){

                    Log.e("sd",response.responseData+" ");
                    mUser = new Gson().fromJson(response.responseData, User.class);

                    if(mUser != null){
                        mStatus = mUser.getStatus();
                    }

                    if(mStatus.equals("offline")){
                        mStatusView.setChecked(false);
                    }else{
                        mStatusView.setChecked(true);
                    }
                }
            }
        });

    }

    private void postStatus(final String status){
        ServerAPI.updateStatus(getActivity(), mUsername, status, new ServerResponseCallback() {
            @Override
            public void onResponse(ServerResponseData response) {

            }
        });
    }



}
