package com.example.clothesshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.clothesshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageButton btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCart = findViewById(R.id.shopcart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShopCartClick(v);
            }
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(ngView);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new HomeFragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener ngView = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectFragment = null;
            switch (item.getItemId())
            {
                case R.id.fragment_home:
                    selectFragment = new HomeFragment();
                    break;
                case R.id.fragment_category:
                    selectFragment = new CategoryFragment();
                    break;
                case R.id.fragment_shop:
                    selectFragment = new HomeFragment();
                    break;
                case R.id.fragment_notification:
                    selectFragment = new NotificationFragment();
                    break;
                case R.id.fragment_user:
                    selectFragment = new UserFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, selectFragment).commit();
            return true;
        }
    };
    private void setAdapterViewPager(){

    }
    private void onShopCartClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);

        this.startActivity(intent);
    }
}