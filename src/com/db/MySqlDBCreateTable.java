package com.db;
import java.sql.*;
import com.db.MySqlDBCon;

public class MySqlDBCreateTable {

        private Connection con;
        private Statement st;

    /**
     *
     * @param TableName the name of the table you want to create
     * @param Fields    the fields of the table you want to create
     */
    public void CreateMySqlTable(String TableName , String Fields )
        {
            try {
                st = con.createStatement();
                st.executeUpdate("CREATE TABLE " + TableName + " ( " + Fields + ");");
            }catch (Exception ex){
                System.out.println(ex);
            }
        }


        public void setCon(Connection con) {
            this.con = con;
        }
}
