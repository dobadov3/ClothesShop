package com.example.clothesshop.DAO;

import android.util.Log;

import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.DataProvider;
import com.example.clothesshop.model.Purchased;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseDAO {
    private static PurchaseDAO instance;

    public static PurchaseDAO getInstance() {
        if (instance == null)
            instance = new PurchaseDAO();
        return instance;
    }

    public static void setInstance(PurchaseDAO instance) {
        PurchaseDAO.instance = instance;
    }

    public ArrayList<Integer> getListIdBill(int idAccount){
        ArrayList<Integer> list = new ArrayList<>();
        String query = "SELECT id FROM Bill b WHERE b.idAccount = " + idAccount;

        try{
            ResultSet resultSetBill = DataProvider.getInstance().ExcuteQuery(query);
            while (resultSetBill.next()){
                list.add(resultSetBill.getInt(1));
            }

        }catch (SQLException ex){

        }

        return list;
    }

    public int getCountItem(int idBill){
        String query = "SELECT COUNT(id) FROM Billinfo b WHERE b.idBill = " + idBill;
        int result = 0;

        ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
        try{
            if (resultSet.next())
                result = resultSet.getInt(1);
        }catch (SQLException ex){

        }
        return result;
    }

    public ArrayList<Purchased> getListPurchased(int idAccount){
        ArrayList<Integer> listIdBill = getListIdBill(idAccount);
        ArrayList<Purchased> list = new ArrayList<>();
        String query = "";

        for (int i = 0; i<listIdBill.size();i++){
            try{
                query = String.format("SELECT b.id, b.createDay,p.nameProduct, b1.count, p.price,P.image, b.total  FROM Bill b, Billinfo b1, Product p WHERE b1.idProduct = P.id AND b1.idBill = b.id AND b.id = "+ listIdBill.get(i) +" AND b.idAccount = " + idAccount);
                int countItem = getCountItem(listIdBill.get(i));
                ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
                if (resultSet.next()){
                    Purchased purchased = new Purchased();
                    purchased.setId(resultSet.getInt(1));
                    purchased.setCreateDay(resultSet.getDate(2));
                    purchased.setName(resultSet.getString(3));
                    purchased.setCountProduct(resultSet.getInt(4));
                    purchased.setPrice(resultSet.getInt(5));
                    purchased.setImage(resultSet.getString(6));
                    purchased.setTotal(resultSet.getInt(7));
                    purchased.setShipCost(Cart.SHIP_COST);
                    purchased.setCountItem(countItem);
                    Log.d("Doba", "getListPurchased: ");
                    list.add(purchased);
                }
            }catch (SQLException ex){
            }
        }

        return list;
    }
}
