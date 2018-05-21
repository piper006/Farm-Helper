package com.db;
import java.sql.*;
public class MySqlDBDelete {


        private Connection con;
        private Statement st;
        /**
         *
         * @param TableName Database's table name that you want to select from
         * @param Extras    Any other extra sql query code you want to put.. Example(Where field='admin',Group By age,etc)
         * @return          Returns the result of the executed query ResultSet type
         */
        public void DeleteFromTable(String TableName , String Extras){

            try {
                st = con.createStatement();
                st.executeUpdate("DELETE FROM " + TableName + " " + Extras );
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


        public void setCon(Connection con) {
            this.con = con;
        }
    }

