package com.example.csit228_f1_v2.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/db2324jdbc";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static Connection getConnection(){
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("DB Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void CreateTable(){
        try(Connection c = MySQLConnection.getConnection();
            Statement statement = c.createStatement()
        ){
            String query = "CREATE TABLE IF NOT EXISTS tblaccounts (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL)";
            statement.execute(query);
            System.out.println("Table created successfully...");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}
