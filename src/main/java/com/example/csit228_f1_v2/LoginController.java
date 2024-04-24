package com.example.csit228_f1_v2;

import com.example.csit228_f1_v2.JDBC.ReadData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {
    public TextField txtusername;
    public PasswordField txtpassword;
    public Button btnsignIn;
    public Button btnsignUp;
    public VBox paneSignin;
    public Label txterror;
    public static int userId;

    public void onClickGoSignin(ActionEvent actionEvent) throws IOException {
        String uname = txtusername.getText();
        String pword = txtpassword.getText();
        int id = ReadData.checkUser(uname, pword);

        if (id != 0) {
            AnchorPane p = (AnchorPane) paneSignin.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            userId = id;
            p.getChildren().clear();
            p.getChildren().add(scene);
        } else {
            txterror.setText("Who u? register sa O.o");
        }
    }



    public void onClickGoRegister(ActionEvent actionEvent) throws IOException {
        AnchorPane p = (AnchorPane) paneSignin.getParent();
        Parent scene =  FXMLLoader.load(getClass().getResource("register-view.fxml"));
        p.getChildren().clear();
        p.getChildren().add(scene);
    }
}
