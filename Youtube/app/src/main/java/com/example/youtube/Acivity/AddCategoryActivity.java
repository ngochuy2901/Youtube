package com.example.youtube.Acivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.youtube.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddCategoryActivity extends AppCompatActivity {
    private TextInputEditText title, description;
    private Button btnAttached, btnAddCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        initView();
    }

    private void initView() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        btnAddCategory = findViewById(R.id.btn_add_category);
        btnAttached = findViewById(R.id.btn_attached_image);

        btnAttached.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}