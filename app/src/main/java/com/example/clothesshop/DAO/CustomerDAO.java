package com.example.clothesshop.DAO;

import com.example.clothesshop.model.CustomerInfo;
import com.example.clothesshop.model.DataProvider;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Queue;

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

    public boolean UpdateInfo(int id, String name, String gender, String tel, String email, String address){
        String query = String.format("UPDATE dbo.CustomerInfo SET nameCus = N'%s', gender = N'%s', tel = %s, email = '%s', address = '%s' WHERE id = " + id, name, gender, tel, email, address);
        int resultSet = DataProvider.getInstance().ExcuteNonQuery(query);
        return resultSet > 0;
    }

    public int getLastID(){
        int result = 0;
        String query = "SELECT TOP 1 id FROM CustomerInfo ci ORDER BY id DESC";

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            if (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException ex){

        }

        return result;
    }

    public boolean InsertCusInfo(String name){
        String query = "INSERT INTO CustomerInfo (nameCus) VALUES (N'"+ name +"')";
        int result = 0;

        result = DataProvider.getInstance().ExcuteNonQuery(query);

        return result > 0;
    }

    public boolean InsertCusInfo(String name, String gender,String email, String address){
        String query = "INSERT INTO CustomerInfo (nameCus, gender, email, address, tel) VALUES (N'"+name+"', '"+gender+"', '"+email+"', '"+address+"', '0')";
        int result = 0;

        result = DataProvider.getInstance().ExcuteNonQuery(query);

        return result > 0;
    }

    public CustomerInfo getLastInfo(){
        CustomerInfo customerInfo = new CustomerInfo();

        String query = "SELECT TOP 1 * FROM CustomerInfo ci ORDER BY id DESC";

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);

            if (resultSet.next()){
                customerInfo.setId(resultSet.getInt(1));
                customerInfo.setName(resultSet.getString(2));
                customerInfo.setGender(resultSet.getString(3));
                customerInfo.setTel(resultSet.getString(4));
                customerInfo.setEmail(resultSet.getString(5));
                customerInfo.setAddress(resultSet.getString(6));
            }
        }catch (SQLException ex){

        }

        return customerInfo;
    }
}
