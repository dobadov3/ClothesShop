package com.example.clothesshop.DAO;

import com.example.clothesshop.model.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAO {
    private static BillDAO instance;

    public static BillDAO getInstance() {
        if (instance == null)
            instance = new BillDAO();
        return instance;
    }

    public static void setInstance(BillDAO instance) {
        BillDAO.instance = instance;
    }

    public boolean InsertBill(int idAccount, int discount, int total){
        int result = 0;

        String query = "INSERT INTO Bill (idAccount, createDay, discount, total) VALUES (" + idAccount +", GETDATE(), " + discount +", " + total + ")";

        result = DataProvider.getInstance().ExcuteNonQuery(query);

        return result > 0;
    }

    public int getLastID(){
        String query = "SELECT TOP 1 id FROM Bill b ORDER BY id DESC";
        int result = 0;
        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);

            if (resultSet.next())
            {
                result = resultSet.getInt(1);
            }
        }catch (SQLException ex){

        }

        return result;
    }
}
