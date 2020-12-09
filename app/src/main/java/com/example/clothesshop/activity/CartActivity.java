package com.example.clothesshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.clothesshop.DAO.ClothesDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.CartAdapter;
import com.example.clothesshop.model.Clothes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.widget.LinearLayout.*;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static ArrayList<Clothes> mClothes = new ArrayList<>();
    CartAdapter adapter;
    public static TextView tvTotal;
    public static TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerviewCart);
        tvTotal = findViewById(R.id.tvTotal);
        tv1 = findViewById(R.id.tv1);

        tv1.setText("Bạn đang có " + mClothes.size() + " trong giỏ hàng");

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        tvTotal.setText("Thành tiền: " + currencyFormatter.format(CartFragment.price));

        adapter = new CartAdapter(mClothes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

}