package com.FarmHelper.Services;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.User;
import com.db.MySqlDBCon;
import com.db.MySqlDBInsert;
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

        resultSet = mySqlDBSelect.selectFromTable("Users","Username,Password","Where username='"+user.getUsername()+"'");

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
        resultSet = mySqlDBSelect.selectFromTable("Entries","*","Where username='"+user.getUsername()+"' order by VarietyName");
        try {
            while(resultSet.next()){
                dataEntry = new DataEntry();
                dataEntry.setVarietyName(resultSet.getString("varietyName"));
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
        resultSet = mySqlDBSelect.selectFromTable("Entries","varietyname,SUM(amount) as total","Where username='"+user.getUsername()+"' group by varietyname");

        try {
            while (resultSet.next()){
                calcMap.put(resultSet.getString("varietyName"),resultSet.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calcMap;
    }
    @Override
    public void addNewRegistry(DataEntry entry){
        MySqlDBInsert mySqlDBInsert = new MySqlDBInsert();
        String fruitID = " ";
        mySqlDBInsert.setCon(con);
        mySqlDBSelect.setCon(con);
        try {
            resultSet =mySqlDBSelect.selectFromTable("fruits","fruitid","where name='" + entry.getVarietyName()+"'");
            if(resultSet.next()) {
                fruitID = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mySqlDBInsert.InsertIntoMySqlTable("insert into entries values ('"+entry.getEntryID() +"','"+fruitID+ "'," +entry.getAmount() + ",'1','" +entry.getEntryDate() +"')");
    }

    @Override
    public List<String> getFruitsNames(String typeOfFruit) {
        List<String> list = new ArrayList<>();
        MySqlDBSelect mySqlDBSelect = new MySqlDBSelect();
        mySqlDBSelect.setCon(con);

        try {
            resultSet = mySqlDBSelect.selectFromTable("fruits","Name","where type='"+typeOfFruit+"'");
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> getFruitsTypes() {
        List<String> list = new ArrayList<>();

        MySqlDBSelect mySqlDBSelect = new MySqlDBSelect();
        mySqlDBSelect.setCon(con);
        resultSet = mySqlDBSelect.selectFromTable("typeoffruits","type"," ");

        try {
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}