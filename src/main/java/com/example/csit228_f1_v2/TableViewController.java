package com.example.csit228_f1_v2;

import com.example.csit228_f1_v2.JDBC.MySQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    public Button btnLogout;
    public VBox paneLogout;
    @FXML
    private TableView<Archive> archiveTable;
    @FXML
    private TableColumn<Archive,String> titleCol;
    @FXML
    private TableColumn<Archive,String> linkCol;
    @FXML
    private TableColumn<Archive, String> editCol;
    ObservableList<Archive> ArchiveList = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Archive archive = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        archiveTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        loadDate();
    }


    @FXML
    private void refreshTable(){
        try(Connection c = MySQLConnection.getConnection();
            Statement statement = c.createStatement()
        ){
            ArchiveList.clear();
            String query = "SELECT * FROM tbllist";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                ArchiveList.add(new Archive(
                        resultSet.getString("title"),
                        resultSet.getString("link")));
                archiveTable.setItems(ArchiveList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDate() {
        try(Connection c = MySQLConnection.getConnection()) {
            refreshTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        linkCol.setCellValueFactory(new PropertyValueFactory<>("link"));

        Callback<TableColumn<Archive, String>, TableCell<Archive, String>> cellFoctory = (TableColumn<Archive, String> param) -> {
            final TableCell<Archive, String> cell = new TableCell<Archive, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);

                    } else {

                        Button deleteIcon = new Button("DELETE");
                        Button editIcon = new Button("EDIT");

                        deleteIcon.setOnMouseClicked(event -> {
                            try(Connection c  = MySQLConnection.getConnection();
                                PreparedStatement statement = c.prepareStatement("DELETE FROM tbllist where title = ?")) {
                                Archive archive = archiveTable.getSelectionModel().getSelectedItem();
                                statement.setString(1, archive.getTitle());
                                statement.executeUpdate();
                                refreshTable();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        editIcon.setOnMouseClicked(event-> {
                            archive = archiveTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("addArchive.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                throw new RuntimeException();
                            }

                            AddArchiveController add = loader.getController();
                            add.setUpdate(true);
                            add.setTextField(archive.getId(), archive.getTitle(),
                                    archive.getLink());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                    }
                    setText(null);
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
        archiveTable.setItems(ArchiveList);


    }
    @FXML
    private void onClickDisplayAdd() {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addArchive.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException();
        }

    }
    @FXML
    public void onClickLogout(ActionEvent actionEvent) {
        try {
            AnchorPane p = (AnchorPane) paneLogout.getParent();
            Parent scene = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            p.getChildren().clear();
            p.getChildren().add(scene);
        } catch (IOException ex) {
            throw new RuntimeException();
        }

    }
}

