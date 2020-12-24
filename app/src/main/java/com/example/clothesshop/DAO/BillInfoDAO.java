package com.example.clothesshop.DAO;

import com.example.clothesshop.model.BillInfo;
import com.example.clothesshop.model.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillInfoDAO {
    private static BillInfoDAO instance;

    public static BillInfoDAO getInstance() {
        if (instance == null)
            instance = new BillInfoDAO();
        return instance;
    }

    public static void setInstance(BillInfoDAO instance) {
        BillInfoDAO.instance = instance;
    }

    public ArrayList<BillInfo> getListBillInfo(int idBill){
        ArrayList<BillInfo> list = new ArrayList<>();

        String query = "SELECT b.id, P.nameProduct, P.image, P.price, b.count FROM Billinfo b, Product p WHERE b.idProduct = P.id AND b.idBill = " +idBill;

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            while (resultSet.next()){
                BillInfo billInfo = new BillInfo();

                billInfo.setId(resultSet.getInt(1));
                billInfo.setNameProduct(resultSet.getString(2));
                billInfo.setImage(resultSet.getString(3));
                billInfo.setPrice(resultSet.getInt(4));
                billInfo.setCount(resultSet.getInt(5));

                list.add(billInfo);
            }
        }catch (SQLException ex){

        }
        return  list;
    }

    public boolean InsertBillInfo(int idBill, int idProduct, int count){
        int result = 0;

        String query = "INSERT INTO Billinfo (idBill, idProduct, count) VALUES ("+ idBill +", "+ idProduct +", "+ count +")";

        result = DataProvider.getInstance().ExcuteNonQuery(query);

        return result > 0;
    }
}
