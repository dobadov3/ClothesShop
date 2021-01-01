package com.example.clothesshop.DAO;

import com.example.clothesshop.model.DataProvider;
import com.example.clothesshop.model.Notification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotiDAO {
    private static NotiDAO instance;

    public static NotiDAO getInstance() {
        if (instance == null)
            instance = new NotiDAO();
        return instance;
    }

    public static void setInstance(NotiDAO instance) {
        NotiDAO.instance = instance;
    }

    public ArrayList<Notification> getListNoti(int idAccount){
        ArrayList<Notification> list = new ArrayList<>();

        String query = "SELECT * FROM Notification WHERE idAccount = " + idAccount;

        try{
            ResultSet resultSet = DataProvider.getInstance().ExcuteQuery(query);
            while(resultSet.next()){
                Notification notification = new Notification();

                notification.setId(resultSet.getInt(1));
                notification.setIdAccount(resultSet.getInt(2));
                notification.setDate(resultSet.getDate(3));
                notification.setTitle(resultSet.getString(4));
                notification.setContent(resultSet.getString(5));
                notification.setImg(resultSet.getString(6));

                list.add(notification);
            }
        }catch (SQLException ex){

        }

        return list;
    }
}
