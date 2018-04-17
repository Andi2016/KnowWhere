package com.android.andi.knowwhere.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.fragments.ChatFragment;
import com.android.andi.knowwhere.fragments.ContactFragment;
import com.android.andi.knowwhere.fragments.HomeFragment;
import com.android.andi.knowwhere.fragments.NearbyFragment;
import com.android.andi.knowwhere.models.User;
import com.android.andi.knowwhere.servers.ServerAPI;
import com.android.andi.knowwhere.servers.ServerResponseCallback;
import com.android.andi.knowwhere.servers.ServerResponseData;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private KnowWhere mApp;
    private User mUser;

    /**
     * Temp method to manage local-saved head images
     */
    public static Map<String, Integer> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApp = (KnowWhere) getApplicationContext();
        mUser = mApp.getPreference().getUser(mApp.getApplicationContext());

        map.put("Alice", R.drawable.head_alice);
        map.put("Joe", R.drawable.head_joe);
        map.put("Tina", R.drawable.head_tina);
        map.put("Bob", R.drawable.head_bob);
        map.put("Smith", R.drawable.head_smith);
        map.put("Jane", R.drawable.head_jane);

        final Bundle bundle = new Bundle();
        bundle.putString("username", mUser.getUsername());
        Fragment fragment = HomeFragment.newInstance();
        fragment.setArguments(bundle);

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
        setupActionBar(1);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = HomeFragment.newInstance();
                                selectedFragment.setArguments(bundle);
                                setupActionBar(1);
                                break;
                            case R.id.action_item2:
                                selectedFragment = ContactFragment.newInstance();
                                selectedFragment.setArguments(bundle);
                                setupActionBar(2);
                                break;
                            case R.id.action_item3:
                                selectedFragment = NearbyFragment.newInstance();
                                selectedFragment.setArguments(bundle);
                                setupActionBar(3);
                                break;
                            case R.id.action_item4:
                                selectedFragment = ChatFragment.newInstance();
                                selectedFragment.setArguments(bundle);
                                setupActionBar(4);
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });


    }

    private void setupActionBar(int i){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            switch (i){
                case 1:
                    getSupportActionBar().setTitle("Home");
                    break;
                case 2:
                    getSupportActionBar().setTitle("Contact");
                    break;
                case 3:
                    getSupportActionBar().setTitle("Nearby Friends");
                    break;
                case 4:
                    getSupportActionBar().setTitle("Chat");
                    break;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void logout(){
        mApp.getPreference().setAppState(MainActivity.this,false);
        mApp.getPreference().storeUser(MainActivity.this,null);
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
        finish();
    }





}
