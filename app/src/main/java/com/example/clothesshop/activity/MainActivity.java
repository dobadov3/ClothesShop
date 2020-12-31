package com.example.clothesshop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.fragment.CartFragment;
import com.example.clothesshop.fragment.CategoryFragment;
import com.example.clothesshop.fragment.HomeFragment;
import com.example.clothesshop.fragment.NotificationFragment;
import com.example.clothesshop.fragment.UserFragment;


import com.example.clothesshop.R;
import com.facebook.FacebookSdk;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageButton btnCart;
    EditText editTextSearch;
    RelativeLayout RelativeTop;
    public static TextView tvTitle;
    public static final int REQUEST_MAIN_ACTIVITY = 2596;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        btnCart = findViewById(R.id.shopcart);
        editTextSearch = findViewById(R.id.etSearch);
        RelativeTop = findViewById(R.id.RelativeTop);
        RelativeTop.setVisibility(View.INVISIBLE);
        tvTitle = findViewById(R.id.tvTitle);

        editTextSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editTextSearch.getRight() - editTextSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        Bundle bundle = new Bundle();
                        if (!editTextSearch.getText().toString().equals(""))
                        {
                            bundle.putString("search",editTextSearch.getText().toString());
                            intent.putExtra("searchBundle", bundle);
                            startActivityForResult(intent, SearchActivity.REQUEST_SEARCH);
                        }


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
                    tvTitle.setText("CÁ NHÂN");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SearchActivity.REQUEST_SEARCH){
            editTextSearch.setText("");
            try{
                Bundle bundle = data.getBundleExtra("clothesInfo");
                if (bundle != null){
                    CartFragment cartFragment = new CartFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.layout_container, cartFragment);
                    cartFragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Doba", e.toString());
            }
        }
    }
}