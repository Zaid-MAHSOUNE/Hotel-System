package com.example.demo1;

import com.example.demo1.DBConnect.DBConnectivity;
import com.example.demo1.Modules.Client;
import com.example.demo1.Modules.Room;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AdminDashController implements Initializable {
    private  Connection connection = new DBConnectivity().getConnection();
    private Stage stage;
    private Scene scene;

    @FXML
    Button btnHome;
    @FXML
    TableView tvClients;
    @FXML
    Button btnDashboard;
    @FXML
    TableColumn<Client,Integer> id;
    @FXML
    TableColumn<Client,String> fname;
    @FXML
    TableColumn<Client,String> lname;
    @FXML
    TableColumn<Client,String> titletype;
    @FXML
    TableColumn<Client,String> titleno;
    @FXML
    TableColumn<Client,String> mail;
    @FXML
    Label lbNbrClient;
    @FXML
    Label lbNbrRoom;
    @FXML
    Label lbNbrAvailableRoom;
    @FXML
    TableColumn<Client,Void> tcActions;
    @FXML
    Button btnLogout;
    Client client = new Client();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tvClients.setMaxHeight(26 + (client.nbrClient()*30));
            tvClients.setFixedCellSize(30);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        Room room = new Room();
        tcActions.setCellFactory(column -> {
            TableCell<Client, Void> cell = new TableCell<>();
            HBox buttonBox = new HBox();
            buttonBox.setSpacing(20);
            buttonBox.setPadding(new Insets(0, 0, 0, 12));
            Button edit = new Button("Edit");
            Button delete = new Button("Delete");
            buttonBox.getChildren().addAll(edit, delete);
            cell.setGraphic(buttonBox);
            edit.setOnAction(event -> {
                client = (Client) tvClients.getSelectionModel().getSelectedItem();
                System.out.println(client.getClientID());
            });
            delete.setOnAction(event -> {
                client = (Client) tvClients.getSelectionModel().getSelectedItem();
                try {
                    client.deleteClient(client.getClientID());
                    initialize(null, null);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            return cell;
        });

        ObservableList<Client> data = client.get_Clients();
        try {
            lbNbrRoom.setText(String.valueOf(room.nbrRooms()));
            lbNbrClient.setText(String.valueOf(client.nbrClient()));
            lbNbrAvailableRoom.setText(String.valueOf(room.nbrAvailableRooms()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        populateClientTableView(tvClients,data);

        //EVENT HANDLERS

        btnHome.setOnMouseClicked(event -> {
            Parent group = null;
            try {
                group = FXMLLoader.load(getClass().getResource("adminH.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(group);
            stage.setScene(scene);
            stage.setWidth(912);
            stage.setHeight(520);
            stage.show();
        });
        btnLogout.setOnMouseClicked (event -> {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Parent group = null;
            try {
                group = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(group);
            stage.setScene(scene);
            stage.setWidth(912);
            stage.setHeight(520);
            stage.show();
        });
    }
    public void populateClientTableView(TableView tv,ObservableList<Client> data){


        id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("ClientID"));
        titleno.setCellValueFactory(new PropertyValueFactory<Client,String>("clientTitleNo"));
        titletype.setCellValueFactory(new PropertyValueFactory<Client,String>("clientTitle"));
        fname.setCellValueFactory(new PropertyValueFactory<Client,String>("firstName"));
        lname.setCellValueFactory(new PropertyValueFactory<Client,String>("lastName"));
        mail.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
        tv.setItems(data);
    }
}
