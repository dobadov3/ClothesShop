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

        String query = "SELECT * FROM Product";
        try
        {
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            while (resultSet.next())
            {
                Clothes clothes = new Clothes();
                clothes.setId(resultSet.getInt(1));
                clothes.setIdCategory(resultSet.getInt(2));
                clothes.setName(resultSet.getString(3));
                clothes.setPrice(resultSet.getInt(4));
                clothes.setImage(resultSet.getString(5));

                list.add(clothes);
            }
        }catch (SQLException ex)
        {

        }

        return list;
    }

    public ArrayList<Clothes> getListClothesByIDCategory(int idCategory){
        ArrayList<Clothes> list = new ArrayList<>();

        String query = "SELECT * FROM Product WHERE idCategory = " + idCategory;
        try
        {
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            while (resultSet.next())
            {
                Clothes clothes = new Clothes();
                clothes.setId(resultSet.getInt(1));
                clothes.setIdCategory(resultSet.getInt(2));
                clothes.setName(resultSet.getString(3));
                clothes.setPrice(resultSet.getInt(4));
                clothes.setImage(resultSet.getString(5));

                list.add(clothes);
            }
        }catch (SQLException ex)
        {

        }

        return list;
    }
}
