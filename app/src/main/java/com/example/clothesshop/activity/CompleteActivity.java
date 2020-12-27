package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clothesshop.R;

public class CompleteActivity extends AppCompatActivity {

    public static final int COMPLETE_ACTIVITY_REQUEST_CODE = 5484;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompleteActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}