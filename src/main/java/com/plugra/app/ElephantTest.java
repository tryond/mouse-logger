package com.plugra.app;

import com.plugra.model.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.exit;

public class ElephantTest {

    public static void main(String[] args) {

//        import java.sql.*;

//        public class Postgres {
//
//            public static void main(String[] args) {
//                try {
//                    Class.forName("org.postgresql.Driver");
//                }
//                catch (java.lang.ClassNotFoundException e) {
//                    System.out.println(e.getMessage());
//                }
//
//                String url = "jdbc:postgresql://host:port/database";
//                String username = "database";
//                String password = "password";
//
//                try {
//                    Connection db = DriverManager.getConnection(url, username, password);
//                    Statement st = db.createStatement();
//                    ResultSet rs = st.executeQuery("SELECT * FROM people");
//                    while (rs.next()) {
//                        System.out.print("Column 1 returned ");
//                        System.out.println(rs.getString(2));
//                        System.out.print("Column 2 returned ");
//                        System.out.println(rs.getString(3));
//                    }
//                    rs.close();
//                    st.close();
//                }
//                catch (java.sql.SQLException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//
//        }

        // Connection Variables
        String instanceConnectionName = "jdbc:postgres://xqicwdtc:Wx9dCpXi9kFp29GogCEiAl6CVb1PKu9j@pellefant.db.elephantsql.com:5432/xqicwdtc";
        // postgres://xqicwdtc:Wx9dCpXi9kFp29GogCEiAl6CVb1PKu9j@pellefant.db.elephantsql.com:5432/xqicwdtc

        String databaseName = "xqicwdtc";
        // xqicwdtc
        String username = "xqicwdtc";
        // xqicwdtc

        String password = "Wx9dCpXi9kFp29GogCEiAl6CVb1PKu9j";
        // Wx9dCpXi9kFp29GogCEiAl6CVb1PKu9j

        // Establish a Database for use with the GUI
        Database database = new Database(
                instanceConnectionName,
                databaseName,
                username,
                password
        );

        Statement statement = database.getStatement();

        String createTable = "create table users (username varchar(45), password varchar(45));";

        String insert = "insert into users values ('tryond', 'tigrina2');";

        String query = "select * from users";

        try {

            statement.executeUpdate(createTable);

            statement.executeUpdate(insert);

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.toString());
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            exit(1);
        }

    }
}
