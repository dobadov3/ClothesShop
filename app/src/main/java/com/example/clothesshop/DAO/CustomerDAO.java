package com.example.clothesshop.DAO;

import com.example.clothesshop.model.CustomerInfo;
import com.example.clothesshop.model.DataProvider;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {
    private static CustomerDAO instance;

    public static CustomerDAO getInstance() {
        if(instance == null)
            instance = new CustomerDAO();
        return instance;
    }

    public static void setInstance(CustomerDAO instance) {
        CustomerDAO.instance = instance;
    }

    public CustomerInfo getListCustomerByID(int id){
        CustomerInfo customer = new CustomerInfo();
        String query = "SELECT * FROM CustomerInfo WHERE id = "+id;

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);

            if(resultSet.next())
            {
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setGender(resultSet.getString(3));
                customer.setTel(resultSet.getString(4));
                customer.setEmail(resultSet.getString(5));
                customer.setAddress(resultSet.getString(6));
            }
        }catch (SQLException ex)
        {

        }
        return customer;
    }

    public CustomerInfo getListCustomerByInfo(String name, String gender, String tel, String email, String address){
        CustomerInfo customer = new CustomerInfo();
        String query = String.format("SELECT * FROM dbo.CustomerInfo WHERE nameCus = N'%s' AND gender = N'%s' AND tel = %s AND email = '%s' AND address = '%s'", name, gender, tel, email, address);

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);

            if(resultSet.next())
            {
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setGender(resultSet.getString(3));
                customer.setTel(resultSet.getString(4));
                customer.setEmail(resultSet.getString(5));
                customer.setAddress(resultSet.getString(6));
            }
        }catch (SQLException ex)
        {

        }
        return customer;
    }

    public boolean UpdateInfo(String name, String gender, String tel, String email, String address){
        String query = String.format("UPDATE dbo.CustomerInfo SET nameCus = N'%s', gender = N'%s', tel = %s, email = '%s', address = '%s'", name, gender, tel, email, address);
        int resultSet = DataProvider.getInstance().ExcuteNonQuery(query);
        return resultSet > 0;
    }
}
