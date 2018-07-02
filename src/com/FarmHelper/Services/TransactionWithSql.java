package com.FarmHelper.Services;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.Repository.TypeOfFruits;
import com.FarmHelper.User;
import com.db.MySqlDBCon;
import com.db.MySqlDBInsert;
import com.db.MySqlDBSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public DataEntry calculateTotalAmountForExactVariety(String varietyName,String pack) {
        resultSet = mySqlDBSelect.selectFromTable("entries inner join fruits on entries.fruitid=fruits.fruitid","name,SUM(amount) as total","Where username='1' and name='"+varietyName+"' and packagetype='"+pack+"'");
        DataEntry entry = new DataEntry();
        try {
            while (resultSet.next()){
                entry = new DataEntry();
                entry.setAmount(resultSet.getInt("total"));
                entry.setVarietyName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entry;
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
        mySqlDBInsert.InsertIntoMySqlTable("insert into entries values ('"+entry.getEntryID() +"','"+fruitID+ "'," +entry.getAmount() + ",'1','" +entry.getEntryDate() +"','"+entry.getTypeOfPackage() +"')");
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

    @Override
    public List<String> getFruitsPackage() {
        List<String> list = new ArrayList<>();

        try {
            resultSet = mySqlDBSelect.selectFromTable("package","type"," ");
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    public ObservableList<DataEntry> getEntries(String varType,String packType) {
        ObservableList<DataEntry> list = FXCollections.observableArrayList();
        List<DataEntry> stringList = new ArrayList<>();
        DataEntry entry ;
        resultSet = mySqlDBSelect.selectFromTable("entries inner join fruits on entries.fruitid=fruits.fruitid"," name,type ,packagetype ,Amount ,EntryDate","where name='"+varType+"' and packagetype='"+packType+"' order by name");
        try {
            while (resultSet.next()){
                entry = new DataEntry();
                TypeOfFruits typeOfFruits = new TypeOfFruits();
                typeOfFruits.setTypeName(resultSet.getString("type"));
                entry.setVarietyName(resultSet.getString("name"));
                entry.setAmount(resultSet.getInt("amount"));
                entry.setEntryDate((resultSet.getDate("entrydate")).toString());
                entry.setTypeOfPackage(resultSet.getString("packagetype"));
                entry.setTypeOfFruits(typeOfFruits);

                stringList.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list.addAll(stringList);

        return list;
    }
}
