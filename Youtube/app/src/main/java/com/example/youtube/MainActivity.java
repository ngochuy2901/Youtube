package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.youtube.Acivity.AddCategoryActivity;
import com.example.youtube.Acivity.HomeActivity;
import com.example.youtube.Acivity.LoginActivity;
import com.example.youtube.Acivity.PlayVideoActivity;
import com.example.youtube.Acivity.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    public static String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}