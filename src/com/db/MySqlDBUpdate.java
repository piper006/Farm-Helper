package com.db;
import java.sql.*;
public class MySqlDBUpdate {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    /**
     *
     * @param TableName the name of the table you want to update
     * @param FieldSandValues the fields you want to change and the values you want to put exp(Username='admin' , Password='123456')
     * @param Extra Any other extra sql query code you want to put.. Example(Where field='admin',Group By age,etc)
     */
    public void UpdateSqlTable(String TableName , String FieldSandValues ,String Extra )
    {
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE " +TableName+ " SET " + FieldSandValues + " " + Extra );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setCon(Connection con) {
        this.con = con;
    }
}
