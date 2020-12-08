package com.example.clothesshop.model;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProvider {
    private static String url = "jdbc:jtds:sqlserver://cnpmk24.database.windows.net:1433/ClothesShop;user=taengoo@cnpmk24;password=Taeyeon2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;;";
    static Connection con = Connect();

    private static DataProvider instance;
    public static DataProvider getInstance(){
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }
    private void setInstance(DataProvider instance)
    {
        this.instance = instance;
    }
    public static Connection Connect()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
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
    public ResultSet ExcuteQuery(String query)
    {
        ResultSet resultSet = null;
        try
        {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(query);

        }
        catch (SQLException ex)
        {

        }

        return resultSet;
    }
}
