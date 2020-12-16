package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.TextView;

import com.example.clothesshop.R;

public class PayActivity extends AppCompatActivity {

    TextView tvTotalPrice, tvTotalShip, tvShipCost, tvFinal;
    public static final int PAY_ACTIVITY_REQUEST_CODE = 1542;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvTotalShip = findViewById(R.id.tvTotalShip);
        tvShipCost = findViewById(R.id.tvShipCost);
        tvFinal = findViewById(R.id.tvFinal);

        Shader shader = new LinearGradient(0,0,0,tvFinal.getLineHeight(),
                Color.parseColor("#6F86D6"), Color.parseColor("#48C6EF"), Shader.TileMode.REPEAT);
        tvTotalPrice.getPaint().setShader(shader);
        tvTotalShip.getPaint().setShader(shader);
        tvShipCost.getPaint().setShader(shader);
        tvFinal.getPaint().setShader(shader);
    }
}