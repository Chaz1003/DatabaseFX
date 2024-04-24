package com.example.csit228_f1_v2.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

    public static void addData(String username, String password) {
        try(Connection c = MySQLConnection.getConnection();
            //dynamic and mas safe compared sa statement
            PreparedStatement statement = c.prepareStatement("INSERT INTO tblaccounts (username,password) VALUES (?,?)")
        ){

//          di universal, & hassle (ikaw pa magset sa string nga lahi lahi og syntax)
//          String query = "INSERT INTO users (name,email) VALUES ('"+name+"','"+email+"')";
            if(ReadData.ifExists(username) == 1){
                throw new IllegalArgumentException();
            }
            statement.setString(1,username);
            statement.setString(2,password);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
