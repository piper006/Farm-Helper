package com.db;
import com.db.MySqlDBCon;
import java.sql.*;
public class MySqlDBInsert {

    private Connection con;
    private Statement st;



    public void InsertIntoMySqlTable(String sqlQuery){

        try {
            st = con.createStatement();
            st.executeUpdate(sqlQuery);
            System.out.println("Executed Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Gets a param Connection type and set this class private Connection type var
    public void setCon(Connection con) {
        this.con = con;
    }
}
