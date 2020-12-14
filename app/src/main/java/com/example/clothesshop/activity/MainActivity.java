package com.example.clothesshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.example.clothesshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageButton btnCart;
    EditText editTextSearch;
    RelativeLayout RelativeTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCart = findViewById(R.id.shopcart);
        editTextSearch = findViewById(R.id.etSearch);
        RelativeTop = findViewById(R.id.RelativeTop);
        RelativeTop.setVisibility(View.INVISIBLE);

        editTextSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editTextSearch.getRight() - editTextSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast.makeText(MainActivity.this, "Touch", Toast.LENGTH_LONG).show();
                        return true;
                    }
                }
                return false;
            }
        });

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
                    RelativeTop.setVisibility(View.INVISIBLE);
                    editTextSearch.setVisibility(View.VISIBLE);
                    selectFragment = new HomeFragment();
                    break;
                case R.id.fragment_category:
                    RelativeTop.setVisibility(View.INVISIBLE);
                    editTextSearch.setVisibility(View.VISIBLE);
                    selectFragment = new CategoryFragment();
                    break;
                case R.id.fragment_shop:
                    RelativeTop.setVisibility(View.INVISIBLE);
                    editTextSearch.setVisibility(View.VISIBLE);
                    selectFragment = new HomeFragment();
                    break;
                case R.id.fragment_notification:
                    RelativeTop.setVisibility(View.INVISIBLE);
                    editTextSearch.setVisibility(View.INVISIBLE);
                    selectFragment = new NotificationFragment();
                    break;
                case R.id.fragment_user:
                    RelativeTop.setVisibility(View.VISIBLE);
                    editTextSearch.setVisibility(View.INVISIBLE);
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