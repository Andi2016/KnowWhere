package com.android.andi.knowwhere.MainFragments;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.andi.knowwhere.Contact.AddFriendActivity;
import com.android.andi.knowwhere.Contact.ContactListAdapter;
import com.android.andi.knowwhere.Nearby.NearbyListAdapter;
import com.android.andi.knowwhere.R;


public class NearbyFragment extends Fragment {

    public static NearbyFragment newInstance() {
        NearbyFragment fragment = new NearbyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_nearby_fragment, container, false);

        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.nearby_recycler_view);

        NearbyListAdapter nearbyListAdapter =new NearbyListAdapter();
        recyclerView.setAdapter(nearbyListAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
