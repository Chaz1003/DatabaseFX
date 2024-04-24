package com.example.csit228_f1_v2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.csit228_f1_v2.JDBC.MySQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddArchiveController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtLink;

    Archive archive = null;
    private boolean update;
    int id;
    int userId;
    String query;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void save(MouseEvent event) {

        try(Connection c = MySQLConnection.getConnection()){
            String title = txtTitle.getText();
            String link = txtLink.getText();

            if (title.isEmpty() || link.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All DATA");
                alert.showAndWait();

            } else {
                getQuery();
                insert();
                clean();

            }
        }catch (SQLException e){
            throw new RuntimeException();
        }


    }

    @FXML
    private void clean() {
        txtTitle.setText(null);
        txtLink.setText(null);
    }

    private void getQuery() {
        if (!update) {
            query = "INSERT INTO tbllist (title, link, accountid) VALUES (?, ?, ?)";
        } else {
            query = "UPDATE tbllist SET title=?, link=? WHERE id=?";
        }
    }

    private void insert() {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(query)) {

            statement.setString(1, txtTitle.getText());
            statement.setString(2, txtLink.getText());

            if (!update) {
                statement.setInt(3, LoginController.userId);
            } else {
                // Set other parameters as needed for update operation
                // statement.setXXX(...);
            }

            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }


    void setTextField(int id, String title, String link) {
        this.id = id;
        txtTitle.setText(title);
        txtLink.setText(link);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

}