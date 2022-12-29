package com.example.demo1;

import com.example.demo1.DBConnect.DBConnectivity;
import com.example.demo1.Modules.Room;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    private Connection connection = new DBConnectivity().getConnection();
    private Stage stage;
    private Scene scene;
    @FXML
    ImageView account ;
    @FXML
    Button btnLogout;

    @FXML
    VBox vbListRooms;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Room Room = new Room();
        ObservableList<com.example.demo1.Modules.Room> list = Room.getRooms();
        Node[] nodes = new Node[list.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                if (vbListRooms != null) {
                    nodes[i] = (Node) FXMLLoader.load(getClass().getResource("ItemDesign.fxml"));
                    vbListRooms.setSpacing(15);
                    vbListRooms.getChildren().add(nodes[i]);
                } else {
                    Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        account.setOnMouseClicked(event -> {
            roomBox.index=0;
            Parent group = null;
            try {
                group = FXMLLoader.load(getClass().getResource("UserInfo.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(group);
            stage.setScene(scene);
            stage.setWidth(912);
            stage.setHeight(530);
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

}


