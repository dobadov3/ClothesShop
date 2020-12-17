package com.example.clothesshop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.clothesshop.DAO.ClothesDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.CartAdapter;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Clothes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.widget.LinearLayout.*;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static ArrayList<Clothes> mClothes = new ArrayList<>();
    static ArrayList<Cart> mCart = new ArrayList<>();
    CartAdapter adapter;
    String[] mNumber;
    ElegantNumberButton elegantNumberButton;
    Button btnPay;
    public static TextView tvTotal;
    public static TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerviewCart);
        tvTotal = findViewById(R.id.tvTotal);
        tv1 = findViewById(R.id.tv1);
        elegantNumberButton = findViewById(R.id.elegantNumberCart);
        btnPay = findViewById(R.id.btnPay);

        btnPay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onClickBtnPay(v);
            }
        });

        tv1.setText("Bạn đang có " + mClothes.size() + " trong giỏ hàng");

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        tvTotal.setText("Thành tiền: " + currencyFormatter.format(Cart.price));

        mNumber = new String[mClothes.size()];

        adapter = new CartAdapter(mClothes, mCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    private void onClickBtnPay(View view){
        Intent intent = new Intent(CartActivity.this, PayActivity.class);
        intent.putExtra("ListClothes", mClothes);
        intent.putExtra("ListCart", mCart);
        startActivityForResult(intent, PayActivity.PAY_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}