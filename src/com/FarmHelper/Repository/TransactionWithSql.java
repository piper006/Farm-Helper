package com.FarmHelper.Repository;

import com.FarmHelper.Services.UserDataTransaction;
import com.FarmHelper.User;
import com.db.MySqlDBCon;
import com.db.MySqlDBSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionWithSql implements UserDataTransaction {

    private Connection con;
    private MySqlDBSelect mySqlDBSelect = new MySqlDBSelect();
    private ResultSet resultSet;
    public TransactionWithSql(){
        MySqlDBCon mySqlDBCon = new MySqlDBCon();
        mySqlDBCon.ConnectToMySqlDB("Farm-Helper","root","sky1997");
        con=mySqlDBCon.getCon();
        mySqlDBSelect.setCon(con);
    }
    @Override
    public boolean verifyUserLoginData(User user) {
        boolean verified = false;

        resultSet = mySqlDBSelect.SelectFromTable("Users","Username,Password","Where username='"+user.getUsername()+"'");

        try {
            if(resultSet.next()){
                if(resultSet.getString("Password").equals(user.getPassword())){
                    verified = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was a problem finding your account...Try again!!!");
        }
        resultSet = null;
        return verified;
    }

    @Override
    public List<DataEntry> getAllEntries(User user) {
        List<DataEntry> allEntries = new ArrayList<>();

        resultSet = mySqlDBSelect.SelectFromTable("Entries","*","Where username='"+user.getUsername()+"'");
        try {
            if(resultSet.next()){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEntries;
    }
}
