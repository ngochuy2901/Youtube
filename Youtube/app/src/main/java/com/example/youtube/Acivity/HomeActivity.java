package com.example.youtube.Acivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.youtube.Fragment.ChannelFragment;
import com.example.youtube.Fragment.HomeFragment;
import com.example.youtube.Fragment.ProfileFragment;
import com.example.youtube.Fragment.ShortVideoFragment;
import com.example.youtube.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }
    private void initView() {
        actionBar = getSupportActionBar();
        loadFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                if(item.getItemId() == R.id.icon_home) {
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                } else if(item.getItemId() == R.id.icon_profile) {
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                }else if(item.getItemId() == R.id.icon_channel) {
                    fragment = new ChannelFragment();
                    loadFragment(fragment);
                    return true;
                }else if(item.getItemId() == R.id.icon_short) {
                    fragment = new ShortVideoFragment();
                    loadFragment(fragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}