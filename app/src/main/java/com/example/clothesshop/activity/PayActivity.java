package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.TextView;

import com.example.clothesshop.R;
import com.example.clothesshop.adapter.PaymentAdapter;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Clothes;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PayActivity extends AppCompatActivity {

    TextView tvTotalPrice, tvTotalShip, tvShipCost, tvFinal;
    public static final int PAY_ACTIVITY_REQUEST_CODE = 1542;
    static final int SHIP_COST = 30000;
    RecyclerView recyclerView;
    PaymentAdapter adapter;
    ArrayList<Clothes> mClothes;
    ArrayList<Cart> mCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvTotalShip = findViewById(R.id.tvTotalShip);
        tvShipCost = findViewById(R.id.tvShipCost);
        tvFinal = findViewById(R.id.tvFinal);
        recyclerView = findViewById(R.id.recyclerviewPay);

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        setTextColor();
        addList();
        adapter = new PaymentAdapter(mClothes, mCart);

        tvTotalPrice.setText("" + currencyFormatter.format(getTotalPrice()));
        tvShipCost.setText("" + currencyFormatter.format(SHIP_COST));
        tvTotalShip.setText("" + currencyFormatter.format(getTotalPrice() + SHIP_COST));
        tvFinal.setText("" + currencyFormatter.format(getTotalPrice() + SHIP_COST));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    void setTextColor(){
        Shader shader = new LinearGradient(0,0,0,tvFinal.getLineHeight(),
                Color.parseColor("#6F86D6"), Color.parseColor("#48C6EF"), Shader.TileMode.REPEAT);
        tvTotalPrice.getPaint().setShader(shader);
        tvTotalShip.getPaint().setShader(shader);
        tvShipCost.getPaint().setShader(shader);
        tvFinal.getPaint().setShader(shader);
    }

    void addList(){
        if (getIntent().getSerializableExtra("ListClothes") != null && getIntent().getSerializableExtra("ListCart") != null)
        {
            mClothes = (ArrayList<Clothes>) getIntent().getSerializableExtra("ListClothes");
            mCart = (ArrayList<Cart>) getIntent().getSerializableExtra("ListCart");
        }
        else{
            if (getIntent().getSerializableExtra("Clothes") != null && getIntent().getSerializableExtra("Cart") != null){
                Clothes clothes = (Clothes) getIntent().getSerializableExtra("Clothes");
                Cart cart = (Cart) getIntent().getSerializableExtra("Cart");
                mClothes = new ArrayList<>();
                mCart = new ArrayList<>();
                mClothes.add(clothes);
                mCart.add(cart);
            }
        }
    }
    int getTotalPrice(){
        int total = 0;
        for (int i = 0; i< mClothes.size();i++){
            total += mClothes.get(i).getPrice() * Integer.parseInt(mCart.get(i).getCount());
        }
        return total;
    }
}