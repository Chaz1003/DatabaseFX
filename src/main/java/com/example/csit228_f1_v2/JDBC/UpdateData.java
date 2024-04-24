package com.example.csit228_f1_v2.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void main(String[] args) {
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "UPDATE users SET name=? WHERE id=?"
            )){
            String name = "Someone";
            int id = 2;
            statement.setInt(2,id);
            statement.setString(1,name);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows Updated: " + rowsUpdated);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
