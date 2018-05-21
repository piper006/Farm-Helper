package com.db;
import com.db.MySqlDBCon;
import java.sql.*;
public class MySqlDBInsert {

    private Connection con;
    private Statement st;


    /**
     *
     * @param TableName the table's name you want to insert values into it
     * @param FieldNames the names of the fields you want to insert into talbe between them put ,
     * @param Values    the values for the fields and between them put "," and the values inside "'example1','example2'"
     */
    public void InsertIntoMySqlTable(String TableName , String FieldNames , String Values   ){

        try {
            st = con.createStatement();
            st.executeUpdate("INSERT INTO " +TableName+ " (" +FieldNames+ ") VALUES (" +Values +" );");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Gets a param Connection type and set this class private Connection type var
    public void setCon(Connection con) {
        this.con = con;
    }
}
