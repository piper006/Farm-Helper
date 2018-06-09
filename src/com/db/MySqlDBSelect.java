package com.db;
import java.sql.*;

public class MySqlDBSelect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    /**
     *
     * @param TableName Database's table name that you want to select from
     * @param Fields    Database's fields that you want the query to show...Between them put "," and if you want all put "*"
     * @param Extras    Any other extra sql query code you want to put.. Example(Where field='admin',Group By age,etc)
     * @return          Returns the result of the executed query ResultSet type
     */
    public ResultSet selectFromTable(String TableName , String Fields , String Extras){

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT " +Fields + " FROM " + TableName + " " + Extras );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }


    public void setCon(Connection con) {
        this.con = con;
    }
}
