package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.DAO.AccountDAO;
import com.example.clothesshop.DAO.CustomerDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.fragment.UserFragment;
import com.example.clothesshop.model.Account;
import com.example.clothesshop.model.CustomerInfo;
import com.google.gson.Gson;

public class SignInActivity extends AppCompatActivity {

    TextView tvSignIn;
    EditText etUser, etPass;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tvSignIn = findViewById(R.id.tvSignUp);
        etUser = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNull())
                {
                    if (AccountDAO.getInstance().Login(etUser.getText().toString(), etPass.getText().toString()))
                    {
                        onClickSignIn(v);
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Sai mật khẩu hoặc tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    boolean CheckNull()
    {
        if (etUser.getText().toString().matches("") && etPass.getText().toString().matches(""))
        {
            Toast.makeText(SignInActivity.this, "Email và mật khẩu không được để trống!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etUser.getText().toString().matches(""))
        {
            Toast.makeText(SignInActivity.this, "Email không được để trống!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            if (etPass.getText().toString().matches(""))
            {
                Toast.makeText(SignInActivity.this, "Mật khẩu không được để trống!!", Toast.LENGTH_SHORT).show();
                return false;
            }
            else
            {
               return true;
            }
        }
    }
    private void onClickSignIn(View view)
    {
        Intent intent = new Intent();
        Account account = AccountDAO.getInstance().getAccountByUsernamePassword(etUser.getText().toString(), etPass.getText().toString());
        CustomerInfo customerInfo = CustomerDAO.getInstance().getListCustomerByID(account.getIdCustomer());

        SharedPreferences sharedPreferences = getSharedPreferences("checklogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        SharedPreferences sharedPreferences1 = getSharedPreferences("account", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        Gson gson = new Gson();
        String json = gson.toJson(account);

        editor1.putString("accountInfo", json);
        editor.putString("login", "true");
        editor.apply();
        editor1.apply();

        intent.putExtra("CustomerInfo", customerInfo);
        setResult(UserFragment.SIGN_IN_REQUEST_CODE, intent);
        finish();
    }
}