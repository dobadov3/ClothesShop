package com.example.clothesshop.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.CartAdapter;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Clothes;
import com.example.clothesshop.model.CustomerInfo;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvCus_Name, tvCus_Tel, tvCus_Address;
    public static ArrayList<Clothes> mClothes = new ArrayList<>();
    public static ArrayList<Cart> mCart = new ArrayList<>();
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
        tvCus_Name = findViewById(R.id.tvCus_Name);
        tvCus_Tel = findViewById(R.id.tvCus_Tel);
        tvCus_Address = findViewById(R.id.tvCus_Address);
        elegantNumberButton = findViewById(R.id.elegantNumberCart);
        btnPay = findViewById(R.id.btnPay);

        SharedPreferences sharedPreferences = getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cusInfo", "");
        CustomerInfo customerInfo = gson.fromJson(json, CustomerInfo.class);

        if (customerInfo != null)
        {
            tvCus_Name.setText(customerInfo.getName());
            tvCus_Tel.setText("0"+customerInfo.getTel());
            tvCus_Address.setText(customerInfo.getAddress());
        }
        
        btnPay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onClickBtnPay(v);
            }
        });

        tv1.setText(getString(R.string.count_item_cart_1) + " " + mClothes.size() + " " + getString(R.string.count_item_cart_2));

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        tvTotal.setText(getString(R.string.total) + " " + currencyFormatter.format(Cart.price));

        mNumber = new String[mClothes.size()];

        adapter = new CartAdapter(mClothes, mCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    private void onClickBtnPay(View view){
        if (mClothes.size() != 0){
            Intent intent = new Intent(CartActivity.this, PayActivity.class);
            intent.putExtra("ListClothes", mClothes);
            intent.putExtra("ListCart", mCart);
            startActivityForResult(intent, PayActivity.PAY_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PayActivity.PAY_ACTIVITY_REQUEST_CODE){
            recyclerView.setAdapter(adapter);
        }
    }
}