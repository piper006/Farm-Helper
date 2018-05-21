package com.FarmHelper.Repository;

import com.FarmHelper.Services.UserDataTransaction;
import com.FarmHelper.User;
import com.db.MySqlDBCon;
import com.db.MySqlDBSelect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionWithSql implements UserDataTransaction {

    private Connection con;
    private MySqlDBSelect mySqlDBSelect = new MySqlDBSelect();
    private ResultSet resultSet;
    private User user;
    public TransactionWithSql(User user){
        MySqlDBCon mySqlDBCon = new MySqlDBCon();
        mySqlDBCon.ConnectToMySqlDB("Farm-Helper","root","sky1997");
        con=mySqlDBCon.getCon();
        mySqlDBSelect.setCon(con);
        this.user = user;
    }
    @Override
    public boolean verifyUserLoginData() {
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
    public List<DataEntry> getAllEntries() {
        List<DataEntry> allEntries = new ArrayList<>();
        DataEntry dataEntry;
        resultSet = mySqlDBSelect.SelectFromTable("Entries","*","Where username='"+user.getUsername()+"'");
        try {
            while(resultSet.next()){
                dataEntry = new DataEntry();
                dataEntry.setEntryID(resultSet.getString("entryID"));
                dataEntry.setVarietyName(resultSet.getString("varietyName"));
                dataEntry.setEntryDate(resultSet.getDate("entryDate"));
                dataEntry.setAmount(resultSet.getInt("amount"));
                allEntries.add(dataEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEntries;
    }

    @Override
    public HashMap<String, Integer> calculateTotalAmountPerVariety() {
        HashMap<String,Integer> calcMap = new HashMap<>();
        resultSet = mySqlDBSelect.SelectFromTable("Entries","varietyname,SUM(amount) as total","Where username='"+user.getUsername()+"' group by varietyname");

        try {
            while (resultSet.next()){
                calcMap.put(resultSet.getString("varietyName"),resultSet.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calcMap;
    }
}
