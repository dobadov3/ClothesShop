package com.example.clothesshop.DAO;

import com.example.clothesshop.model.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {
    private static CategoryDAO instance;
    public static CategoryDAO getInstance()
    {
        if (instance == null)
            instance = new CategoryDAO();
        return instance;
    }
    private void setInstance(CategoryDAO instance)
    {
        this.instance = instance;
    }

    public String getCategoryNameByID(int id)
    {
        String query = "SELECT name FROM Category WHERE id = "+id;
        String categoryName = "";
        ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
        try{
            categoryName = resultSet.getString(1);
        }
        catch (SQLException ex)
        {

        }
        return categoryName;
    }
}
