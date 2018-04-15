package com.android.andi.knowwhere.MainFragments;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.andi.knowwhere.Chat.ChatListAdapter;
import com.android.andi.knowwhere.Contact.AddFriendActivity;
import com.android.andi.knowwhere.Contact.ContactListAdapter;
import com.android.andi.knowwhere.R;

public class ChatFragment extends Fragment {

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

        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.chat_recycler_view);

        ChatListAdapter chatListAdapter =new ChatListAdapter();
        recyclerView.setAdapter(chatListAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        return view;
    }
}
