package com.example.clothesshop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.DAO.AccountDAO;
import com.example.clothesshop.DAO.CustomerDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.LoadingDialog;
import com.example.clothesshop.fragment.UserFragment;
import com.example.clothesshop.model.Account;
import com.example.clothesshop.model.CustomerInfo;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {

    TextView tvSignIn;
    EditText etUser, etPass;
    Button btnSignIn;
    LoginButton loginButton;
    CallbackManager callbackManager;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    LoadingDialog dialog;
    public static final int RC_SIGN_IN = 5134;
    String TAG = "Doba";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_in);


        tvSignIn = findViewById(R.id.tvSignUp);
        etUser = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        loginButton = findViewById(R.id.btn_login_fb);
        signInButton = findViewById(R.id.btn_login_gg);
        dialog = new LoadingDialog(SignInActivity.this);


        SharedPreferences sharedPreferences = getSharedPreferences("checklogin", MODE_PRIVATE);
        String login = sharedPreferences.getString("login", "");

        if (login.equals("false")){
            LoginManager.getInstance().logOut();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });



        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess: ");
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError: ");
            }
        });

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

        SharedPreferences sharedPreferences2 = getSharedPreferences("customerInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        if (customerInfo.getAvatar() == null)
            editor2.putString("avatar", customerInfo.getAvatar());
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(customerInfo);

        editor2.putString("cusInfo", json1);

        editor1.putString("accountInfo", json);
        editor.putString("login", "true");
        editor.apply();
        editor1.apply();
        editor2.apply();

        intent.putExtra("CustomerInfo", customerInfo);
        setResult(UserFragment.SIGN_IN_REQUEST_CODE, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        //Google
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        //Facebook
        if (AccessToken.getCurrentAccessToken() != null)
        {
            handleGraphRequest();
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            if (account != null) {
                String name = account.getDisplayName();
                String email = account.getEmail();
                String id = account.getId();
                Uri personPhoto = account.getPhotoUrl();

                Log.d(TAG, "DisplayName: "+name);
                Log.d(TAG, "Email: "+email);
                Log.d(TAG, "Id: "+id);
                Log.d(TAG, "PhotoUrl: "+personPhoto);

                if (!AccountDAO.getInstance().Login(id, "gg" + id)){
                    CustomerDAO.getInstance().InsertCusInfo(name,email);
                    AccountDAO.getInstance().InsertAccount(id, "gg"+id);
                    Log.d(TAG, "Insert Info by google: Successful ");
                }
                Account acct = AccountDAO.getInstance().getAccountByUsernamePassword(id, "gg"+id);
                CustomerInfo customerInfo = CustomerDAO.getInstance().getListCustomerByID(acct.getIdCustomer());

                SharedPreferences sharedPreferences = getSharedPreferences("checklogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                SharedPreferences sharedPreferences1 = getSharedPreferences("account", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                Gson gson = new Gson();
                String json = gson.toJson(acct);

                SharedPreferences sharedPreferences2 = getSharedPreferences("customerInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                if (!personPhoto.equals(""))
                    editor2.putString("avatar", personPhoto.toString());
                Gson gson1 = new Gson();
                String json1 = gson1.toJson(customerInfo);

                editor2.putString("cusInfo", json1);
                editor1.putString("accountInfo", json);
                editor.putString("login", "true");
                editor.apply();
                editor1.apply();
                editor2.apply();

                dialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        setResult(UserFragment.SIGN_IN_REQUEST_CODE, intent);
                        finish();
                    }
                }, 4000);
            }

        } catch (ApiException e) {
            Log.d(TAG, e.toString());
        }
    }

    private void handleGraphRequest(){
        Intent intent = new Intent();
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d(TAG, object.toString());
                        try {
                            Log.d(TAG, object.toString());
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String email = object.getString("email");
                            String gender = object.getString("gender");
                            JSONObject location = object.getJSONObject("location");
                            String location_name = location.getString("name");
                            JSONObject picture = object.getJSONObject("picture");
                            JSONObject data = picture.getJSONObject("data");
                            String url = data.getString("url");

                            if (!AccountDAO.getInstance().Login(id, "fb" + id)){
                                CustomerDAO.getInstance().InsertCusInfo(name, gender, email, location_name);
                                AccountDAO.getInstance().InsertAccount(id, "fb"+id);
                                Log.d(TAG, "onCompleted: ");
                            }

                            Account account = AccountDAO.getInstance().getAccountByUsernamePassword(id, "fb"+id);
                            CustomerInfo customerInfo = CustomerDAO.getInstance().getListCustomerByID(account.getIdCustomer());

                            SharedPreferences sharedPreferences = getSharedPreferences("checklogin", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            SharedPreferences sharedPreferences1 = getSharedPreferences("account", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(account);

                            SharedPreferences sharedPreferences2 = getSharedPreferences("customerInfo", MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                            if(!url.equals(""))
                                editor2.putString("avatar", url);
                            Gson gson1 = new Gson();
                            String json1 = gson1.toJson(customerInfo);

                            editor2.putString("cusInfo", json1);
                            editor1.putString("accountInfo", json);
                            editor.putString("login", "true");
                            editor.apply();
                            editor1.apply();
                            editor2.apply();
                            Log.d("Doba", location_name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, e.toString());
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,gender,email,location,picture");
        request.setParameters(parameters);
        request.executeAsync();
        setResult(UserFragment.SIGN_IN_REQUEST_CODE, intent);
        finish();
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null)
                LoginManager.getInstance().logOut();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }
}