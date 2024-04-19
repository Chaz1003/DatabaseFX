package com.example.csit228_f1_v2.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "DELETE FROM users WHERE id=? RETURNING *"
             )) {
            int id = 3;
            statement.setInt(1,id);
            int rowsDeleted = statement.executeUpdate();
            ResultSet res = statement.getResultSet();
            if(res.next()){
                System.out.println("Name: " + res.getString("name"));
                System.out.println("Email: " + res.getString("email"));
            }
            System.out.println("DATA DELETED: " + rowsDeleted);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
