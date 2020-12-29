package com.example.clothesshop.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clothesshop.DAO.BillDAO;
import com.example.clothesshop.DAO.BillInfoDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.LoadingDialog;
import com.example.clothesshop.adapter.PaymentAdapter;
import com.example.clothesshop.model.Account;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Clothes;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PayActivity extends AppCompatActivity {

    TextView tvTotalPrice, tvTotalShip, tvShipCost, tvFinal, tvCountProducts;
    public static final int PAY_ACTIVITY_REQUEST_CODE = 1542;
    static final int SHIP_COST = 30000;
    RecyclerView recyclerView;
    PaymentAdapter adapter;
    ArrayList<Clothes> mClothes;
    ArrayList<Cart> mCart;
    RelativeLayout relativePay;
    LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvTotalShip = findViewById(R.id.tvTotalShip);
        tvShipCost = findViewById(R.id.tvShipCost);
        tvCountProducts = findViewById(R.id.tvCountProducts);
        tvFinal = findViewById(R.id.tvFinal);
        relativePay = findViewById(R.id.relativePay);
        recyclerView = findViewById(R.id.recyclerviewPay);
        dialog = new LoadingDialog(PayActivity.this);

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        relativePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRelativePay(v);
            }
        });

        setTextColor();
        addList();
        adapter = new PaymentAdapter(mClothes, mCart);

        tvTotalPrice.setText("" + currencyFormatter.format(getTotalPrice()));
        tvShipCost.setText("" + currencyFormatter.format(SHIP_COST));
        tvTotalShip.setText("" + currencyFormatter.format(getTotalPrice() + SHIP_COST));
        tvFinal.setText("" + currencyFormatter.format(getTotalPrice() + SHIP_COST));
        tvCountProducts.setText(getString(R.string.totalPrice) + " (" + mClothes.size() + " " + getString(R.string.product) + ")");

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
    private void onClickRelativePay(View view){
        dialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (InsertBill()){
                    InsertBillInfo();
                    Log.d("Doba", "run: ");
                    dialog.dismissDialog();
                    Intent intent = new Intent(PayActivity.this, CompleteActivity.class);
                    startActivityForResult(intent, CompleteActivity.COMPLETE_ACTIVITY_REQUEST_CODE);
                    CartActivity.mClothes.clear();
                    CartActivity.mCart.clear();
                }
            }
        }, 4000);
    }

    private boolean InsertBill(){
        SharedPreferences sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("accountInfo", "");

        Account account = gson.fromJson(json, Account.class);

        return BillDAO.getInstance().InsertBill(account.getId(), 0, (getTotalPrice() + SHIP_COST));
    }

    private void InsertBillInfo(){
        int idBill = BillDAO.getInstance().getLastID();

        for (int i = 0; i<mCart.size();i++){
            BillInfoDAO.getInstance().InsertBillInfo(idBill, mClothes.get(i).getId(), Integer.parseInt(mCart.get(i).getCount()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CompleteActivity.COMPLETE_ACTIVITY_REQUEST_CODE){
            mClothes.clear();
            mCart.clear();
            recyclerView.setAdapter(adapter);
        }
    }
}