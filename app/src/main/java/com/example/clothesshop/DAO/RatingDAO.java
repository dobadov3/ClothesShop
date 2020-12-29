package com.example.clothesshop.DAO;

import com.example.clothesshop.model.DataProvider;
import com.example.clothesshop.model.Rating;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RatingDAO implements Serializable {
    private static RatingDAO instance;

    public static RatingDAO getInstance() {
        if (instance == null)
            instance = new RatingDAO();
        return instance;
    }

    public static void setInstance(RatingDAO instance) {
        RatingDAO.instance = instance;
    }

    public Rating getListRatingByIDProduct(int idProduct){
        Rating rating = new Rating();

        String query = "SELECT * FROM Rating r WHERE r.id = " + idProduct;

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);

            if (resultSet.next()){
                rating.setId(resultSet.getInt(1));
                rating.setIdProduct(resultSet.getInt(2));
                rating.setPoint(resultSet.getFloat(3));
            }
        }catch (SQLException ex){

        }

        return rating;
    }

    public float getCurrentPoint(int idProduct){
        float result = 0;

        String query = "SELECT point FROM Rating r WHERE r.id = " + idProduct;

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            if (resultSet.next())
                result = resultSet.getFloat(1);
        }catch (SQLException ex){

        }

        return result;
    }

    public boolean UpdatePoint(int idProduct, int newPoint){
        int result = 0;

        double currentPoint = getCurrentPoint(idProduct);
        double point = (newPoint + currentPoint)/2;

        String query = "UPDATE Rating SET point = "+ point +" WHERE id = " + idProduct;

        result = DataProvider.getInstance().ExcuteNonQuery(query);

        return result > 0;
    }
}
