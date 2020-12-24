package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.clothesshop.R;

public class CompleteActivity extends AppCompatActivity {

    public static final int COMPLETE_ACTIVITY_REQUEST_CODE = 5484;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
    }
}