package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.DAO.AccountDAO;
import com.example.clothesshop.DAO.CustomerDAO;
import com.example.clothesshop.R;

public class SignUpActivity extends AppCompatActivity {

    TextView tvSignUp;
    EditText etUserNameSignUp, etEmailSignUp, etPasswordSignUp;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvSignUp = findViewById(R.id.tvSignIn);
        etUserNameSignUp = findViewById(R.id.etUserNameSignUp);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp(v);
            }
        });
    }

    private void onClickSignUp(View view){
        String name = etUserNameSignUp.getText().toString();
        String username = etEmailSignUp.getText().toString();
        String password = etPasswordSignUp.getText().toString();

        if (name.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(SignUpActivity.this, "Thông tin tài khoản không được để trống", Toast.LENGTH_SHORT).show();
        }
        else if (CustomerDAO.getInstance().InsertCusInfo(name)){
            if (AccountDAO.getInstance().InsertAccount(username, password)){
                finish();
                Toast.makeText(SignUpActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(SignUpActivity.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        }
    }
}