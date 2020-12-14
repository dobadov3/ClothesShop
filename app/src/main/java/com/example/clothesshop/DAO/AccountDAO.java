package com.example.clothesshop.DAO;

import com.example.clothesshop.model.Account;
import com.example.clothesshop.model.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private static AccountDAO instance;

    public static AccountDAO getInstance() {
        if (instance == null)
            instance = new AccountDAO();
        return instance;
    }

    public static void setInstance(AccountDAO instance) {
        AccountDAO.instance = instance;
    }

    public Account getAccountByUsernamePassword(String username, String password)
    {
        Account account = new Account();
        String query = "SELECT * FROM Account WHERE username = '" + username + "' AND password = '" +password + "'";
        try {
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            if (resultSet.next())
            {
                account.setId(resultSet.getInt(1));
                account.setIdCustomer(resultSet.getInt(2));
                account.setUsername(resultSet.getString(3));
                account.setType(resultSet.getInt(4));
                account.setPassword(resultSet.getString(5));
            }
        }catch (SQLException ex){

        }
        return account;
    }
    public boolean Login(String username, String password){
        String query = "SELECT * FROM Account WHERE username = '" + username + "' AND password = '" +password+ "'";
        ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
        try{
            if (resultSet.next() == false)
            {
                return false;
            }
        }catch (SQLException ex){

        }
        return true;
    }
}