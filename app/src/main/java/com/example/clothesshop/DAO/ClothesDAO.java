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

        String query = "SELECT * FROM Product WHERE idCategory = " + idCategory + "AND idDiscount = 1";
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
                clothes.setPriceSale(clothes.getPrice());

                list.add(clothes);
            }
        }catch (SQLException ex)
        {

        }

        return list;
    }

    public ArrayList<Clothes> getListClothesSale(){
        String query = "SELECT p.id\n" +
                "\t  ,p.idCategory\n" +
                "\t  ,p.nameProduct\n" +
                "\t  ,p.image\n" +
                "\t  ,p.price\n" +
                "\t  ,p.image2\n" +
                "\t  ,p.image3\n" +
                "\t  ,p.image4\n" +
                "\t  ,d.value FROM Product p, Discount d WHERE p.idDiscount = d.id AND d.id != 1";
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
                clothes.setPriceSale(clothes.getPrice() - resultSet.getInt(9));

                list.add(clothes);
            }
        }catch (SQLException ex){

        }

        return list;
    }

    public ArrayList<Clothes> getListHotClothes(){
        String query = "SELECT TOP 10 * FROM Product p WHERE p.countBuy > 0 ORDER BY p.countBuy DESC";
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
                clothes.setPriceSale(clothes.getPrice());

                list.add(clothes);
            }
        }catch (SQLException ex){

        }

        return list;
    }

    public ArrayList<Clothes> searchByName(String name){
        String query = "SELECT p.id, p.idCategory, p.nameProduct, p.image, p.price, p.image2, p.image3, p.image4, d.value FROM Product p, Discount d WHERE p.idDiscount = d.id AND p.nameProduct LIKE N'%"+name+"%'";
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
                clothes.setPriceSale(clothes.getPrice() - resultSet.getInt(9));

                list.add(clothes);
            }
        }catch (SQLException ex){

        }

        return list;
    }
}
