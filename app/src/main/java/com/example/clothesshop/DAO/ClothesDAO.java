package com.example.clothesshop.DAO;

import com.example.clothesshop.model.Clothes;
import com.example.clothesshop.model.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClothesDAO {
    private static ClothesDAO instance;
    public static ClothesDAO getInstance()
    {
        if (instance == null)
            instance = new ClothesDAO();
        return instance;
    }
    private void setInstance(ClothesDAO instance)
    {
        this.instance = instance;
    }

    public ArrayList<Clothes> getListClothes(){
        ArrayList<Clothes> list = new ArrayList<>();

        String query = "SELECT * FROM Clothes";
        try
        {
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            while (resultSet.next())
            {
                Clothes clothes = new Clothes();
                clothes.setName(resultSet.getString(2));
                clothes.setPrice(resultSet.getInt(3));
                clothes.setImage(resultSet.getString(4));

                list.add(clothes);
            }
        }catch (SQLException ex)
        {

        }

        return list;
    }
}
