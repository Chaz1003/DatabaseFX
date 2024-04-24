package com.example.csit228_f1_v2.JDBC;

import java.sql.*;

public class ReadData {
    public static int ifExists(String uname) {
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement("SELECT COUNT(*) FROM tblaccounts WHERE username = ?");
        ) {

            statement.setString(1,uname);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int checkUser(String uname, String pword) {
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM tblaccounts WHERE username = ? AND password = ?");
        ) {

            statement.setString(1,uname);
            statement.setString(2,pword);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
