package com.plugra.app;

//paste this into a file called Postgres.java
import java.sql.*;

public class Postgres {

    public static void main(String[] args) {
        try {
            Class.forName("com.example.jdbc.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:postgres://xqicwdtc:Wx9dCpXi9kFp29GogCEiAl6CVb1PKu9j@pellefant.db.elephantsql.com:5432/xqicwdtc";
        String username = "xqicwdtc";
        String password = "Wx9dCpXi9kFp29GogCEiAl6CVb1PKu9j";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM people");
            while (rs.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(2));
                System.out.print("Column 2 returned ");
                System.out.println(rs.getString(3));
            }
            rs.close();
            st.close();
        }
        catch (java.sql.SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}