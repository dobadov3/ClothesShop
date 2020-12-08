package com.example.clothesshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.clothesshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    selectFragment = new HomeFragment();
                    break;
                case R.id.fragment_category:
                    selectFragment = new CategoryFragment();
                    break;
                case R.id.fragment_shop:
                    selectFragment = new HomeFragment();
                    break;
                case R.id.fragment_notification:
                    selectFragment = new NotificationFragment();
                    break;
                case R.id.fragment_user:
                    selectFragment = new UserFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, selectFragment).commit();
            return true;
        }
    };

    @SuppressLint("NewApi")
    public Connection connectionclass(String url)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            ConnectionURL = url;

            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}