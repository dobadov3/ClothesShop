package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.clothesshop.R;
import com.example.clothesshop.adapter.HomeAdapter;
import com.example.clothesshop.fragment.CartFragment;
import com.example.clothesshop.model.Clothes;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}