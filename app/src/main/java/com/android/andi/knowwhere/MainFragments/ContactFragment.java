package com.android.andi.knowwhere.MainFragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.andi.knowwhere.Contact.AddFriendActivity;
import com.android.andi.knowwhere.Contact.ContactListAdapter;
import com.android.andi.knowwhere.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {

    public static String friends="";
    ImageView _addFriend;


    public static ContactFragment newInstance() {
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

        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.my_recycler_view);

        ContactListAdapter contactListAdapter =new ContactListAdapter();
        recyclerView.setAdapter(contactListAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);




        _addFriend=view.findViewById(R.id.add_friend_button);
        _addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddFriendActivity.class));

            }
        });

        return view;
    }

}
