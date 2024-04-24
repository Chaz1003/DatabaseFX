package com.example.csit228_f1_v2;

import com.example.csit228_f1_v2.JDBC.CreateTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    String[] args;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,500);
        stage.setTitle("Register NaNaNa!");
        stage.setScene(scene);
        scene.setFill(Color.CORNFLOWERBLUE);
        stage.show();
        CreateTable.main(args);
    }

    public static void main(String[] args) {
        launch();
    }
}