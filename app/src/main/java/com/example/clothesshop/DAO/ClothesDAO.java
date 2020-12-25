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
                clothes.setImage(resultSet.getString(4));
                clothes.setPrice(resultSet.getInt(5));
                clothes.setImage2(resultSet.getString(6));
                clothes.setImage3(resultSet.getString(7));
                clothes.setImage4(resultSet.getString(8));

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
                clothes.setImage(resultSet.getString(4));
                clothes.setPrice(resultSet.getInt(5));
                clothes.setImage2(resultSet.getString(6));
                clothes.setImage3(resultSet.getString(7));
                clothes.setImage4(resultSet.getString(8));

                list.add(clothes);
            }
        }catch (SQLException ex)
        {

        }

        return list;
    }

    public ArrayList<Clothes> getListClothesSale(){
        String query = "SELECT * FROM Product p WHERE p.idDiscount != 1";
        ArrayList<Clothes> list = new ArrayList<>();

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);

            while (resultSet.next()){
                Clothes clothes = new Clothes();
                clothes.setId(resultSet.getInt(1));
                clothes.setIdCategory(resultSet.getInt(2));
                clothes.setName(resultSet.getString(3));
                clothes.setImage(resultSet.getString(4));
                clothes.setPrice(resultSet.getInt(5));
                clothes.setImage2(resultSet.getString(6));
                clothes.setImage3(resultSet.getString(7));
                clothes.setImage4(resultSet.getString(8));

                list.add(clothes);
            }
        }catch (SQLException ex){

        }

        return list;
    }
}
