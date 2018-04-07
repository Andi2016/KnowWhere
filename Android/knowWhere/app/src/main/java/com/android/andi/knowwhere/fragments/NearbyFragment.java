package com.android.andi.knowwhere.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.andi.knowwhere.adapters.NearbyListAdapter;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.models.Coordinate;
import com.android.andi.knowwhere.models.Post;
import com.android.andi.knowwhere.models.User;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class NearbyFragment extends Fragment implements NearbyListAdapter.NearbyListAdapterListener {

    private String mUsername;

    private NearbyListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private LocationManager locationManager;
    private String provider;

    private double mLatitude;
    private double mLongitude;

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
        View view = inflater.inflate(R.layout.activity_nearby_fragment, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.nearby_recycler_view);

        Bundle bundle = getArguments();
        mUsername = bundle.getString("username");

        setupView();
        getNearbyInfo();
        //getCoordinate();
        mLongitude = -84.783495;
        mLatitude = 32.7632058;

        return view;
    }

    /**
     * This function gets current location
     */
    private void getCoordinate() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);

        if (list.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(getActivity(), "Please check GPS/Network", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        if(location != null){
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();

            Log.e("LOCATION", mLatitude+mLongitude+"");
        }

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                mLongitude = location.getLongitude();
                mLatitude  = location.getLatitude();
                Log.d("jj", "Location longitude:"+ mLongitude +" latitude: "+ mLatitude );
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 1, locationListener);
    }

    /**
     * This function sets up list view
     */
    private void setupView(){

        mAdapter =new NearbyListAdapter(getActivity(), NearbyFragment.this);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * This function fetch nearby posts
     */
    private int mPostCount = 0;
    private void getNearbyInfo(){
        mPostCount = 0;

        ServerAPI.getPostsByUsername(getActivity(), mUsername, new ServerResponseCallback() {
            @Override
            public void onResponse(ServerResponseData response) {
                if(response.statusCode == ServerAPI.STATUS_OK){
                    //postList.clear();
                    try{
                        ArrayList<Post> list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.responseData);
                        mPostCount = jsonArray.length();
                        if(mPostCount == 0){
                            mAdapter.clearItems();
                            mAdapter.notifyDataSetChanged();
                        }

                        for(int i = 0; i < jsonArray.length(); i++){
                            Post post = new Gson().fromJson(jsonArray.get(i).toString(), Post.class);
                            list.add(post);
                            mPostCount--;
                        }


                        if(mPostCount == 0){
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

    private void sendPost(){
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(getActivity());
        }

        builder.setTitle("Sending Post");

        //input set
        final EditText mPostContentView = new EditText(getActivity());
        mPostContentView.setInputType(InputType.TYPE_CLASS_TEXT);
        mPostContentView.setHint("Your post ...");
        builder.setView(mPostContentView);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String post_content = mPostContentView.getText().toString();
                ServerAPI.createPostByUsername(getActivity(), mUsername, post_content, new ServerResponseCallback() {
                    @Override
                    public void onResponse(ServerResponseData response) {
                        if(response.statusCode == ServerAPI.STATUS_OK){
                            ServerAPI.updateCoordinate(getActivity(), mUsername, mLatitude, mLongitude, new ServerResponseCallback() {
                                @Override
                                public void onResponse(ServerResponseData response) {
                                    if(response.statusCode == ServerAPI.STATUS_OK){
                                        getNearbyInfo();
                                    }

                                }
                            });
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
