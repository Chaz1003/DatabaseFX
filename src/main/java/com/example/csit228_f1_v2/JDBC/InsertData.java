package com.example.csit228_f1_v2.JDBC;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertData {
    String username , password;
    public static void main(String[] args) {
        try(Connection c = MySQLConnection.getConnection();
            //dynamic and mas safe compared sa statement
            Scanner sc = new Scanner(System.in);
            PreparedStatement statement = c.prepareStatement("INSERT INTO tblaccounts (username,password) VALUES (?,?)")
            ){
//          di universal, & hassle (ikaw pa magset sa string nga lahi lahi og syntax)
//          String query = "INSERT INTO users (name,email) VALUES ('"+name+"','"+email+"')";

            //statement.setString(1,username);
            //statement.setString(2,password);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
