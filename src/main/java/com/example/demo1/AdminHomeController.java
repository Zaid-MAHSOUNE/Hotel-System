package com.example.demo1;

import com.example.demo1.DBConnect.DBConnectivity;
import com.example.demo1.Modules.Room;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Connection connection = new DBConnectivity().getConnection();
    public static Boolean click = false;

    public static int selected = -1;
    @FXML
    Button btnDashboard;
    @FXML
    ComboBox<String> choose;
    private String[] itm = {"double","simple"};
    @FXML
    TextField txtid ;
    @FXML
    TextField txtprice;
    @FXML
    Label alert;
    @FXML
    TextArea DESCRIPTION;
    @FXML
    TableView tvhomeView;
    @FXML
    TableColumn<Room,Void> tcActions;
    @FXML
    TableColumn<Room,String> tcRoomType;
    @FXML
    TableColumn<Room,Float> tcRoomPrice;
    @FXML
    TableColumn<Room,Integer> tcRoomNo;
    @FXML
    TableColumn<Room,Integer> tcRoomOccupancy;
    @FXML
    Button btnLogout;
    Room room = new Room();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!AdminHomeController.click)
        choose.getItems().addAll(itm);
        try {
            tvhomeView.setMaxHeight(26 + (room.nbrRooms()*30));
            tvhomeView.setFixedCellSize(30);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tcActions.setCellFactory(column -> {
            TableCell<Room, Void> cell = new TableCell<>();
            HBox buttonBox = new HBox();
            buttonBox.setSpacing(20);
            buttonBox.setPadding(new Insets(0, 0, 0, 12));
            Button edit = new Button("Edit");
            Button delete = new Button("Delete");
            buttonBox.getChildren().addAll(edit, delete);
            cell.setGraphic(buttonBox);
            edit.setOnAction(event -> {
                room = (Room) tvhomeView.getSelectionModel().getSelectedItem();
                AdminHomeController.selected = room.getRoomNo();
                Stage st = new Stage();
                st.setTitle("Edit Room");
                st.show();
                st.setWidth(500);
                st.setHeight(550);
                Image icon = new Image("file:src/main/resources/images/hotel.png");
                st.getIcons().add(icon);
                st.setResizable(false);
                Parent group = null;
                try {
                    group = FXMLLoader.load(getClass().getResource("editClient.fxml"));
                    Scene scene = new Scene(group);
                    st.setScene(scene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            delete.setOnAction(event -> {
                room = (Room) tvhomeView.getSelectionModel().getSelectedItem();
                try {
                    room.deleteRoom(room.getRoomNo());
                    initialize(null, null);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            return cell;
        });

        ObservableList<Room> data = room.get_Rooms();

        populateRoomTableView(tvhomeView,data);

        AdminHomeController.click = true;


        btnDashboard.setOnMouseClicked(event -> {
            AdminHomeController.click = false;
            Parent group = null;
            try {
                group = FXMLLoader.load(getClass().getResource("adminD.fxml"));
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
    public void populateRoomTableView(TableView tv,ObservableList<Room> data){
        tcRoomNo.setCellValueFactory(new PropertyValueFactory<Room,Integer>("roomNo"));
        tcRoomType.setCellValueFactory(new PropertyValueFactory<Room,String>("roomType"));
        tcRoomPrice.setCellValueFactory(new PropertyValueFactory<Room,Float>("roomPrice"));
        tcRoomOccupancy.setCellValueFactory(new PropertyValueFactory<Room,Integer>("occupancy"));
        tv.setItems(data);
    }
    public void clear (ActionEvent e){
            txtprice.setText("");
            txtid.setText("");
            DESCRIPTION.setText("");
    }
    public void add (ActionEvent e){
        Room room = new Room();
        if(txtid.getText().isEmpty() || txtprice.getText().isEmpty() || choose.getValue().isEmpty() || DESCRIPTION.getText().isEmpty()){
                alert.setVisible(true);
                alert.setText("FILL ALL THE BLANKS");
                alert.setStyle("-fx-background-color: #FF5C5C;");
        }
        else{
            boolean rst= room.addRoom(Integer.valueOf(txtid.getText()),choose.getValue(),Float.valueOf(txtprice.getText()),DESCRIPTION.getText());
            if(rst==true){
                initialize(null,null);
                alert.setVisible(true);
                alert.setText("YOU ADDED A ROOM");
                alert.setStyle("-fx-background-color: #90EE90;");
                txtprice.setText("");
                txtid.setText("");
                DESCRIPTION.setText("");
            }
            else{
                alert.setVisible(true);
                alert.setText("SOMETHING WRONG TRY AGAIN");
                alert.setStyle("-fx-background-color: #FF5C5C;");
                txtprice.setText("");
                txtid.setText("");
                DESCRIPTION.setText("");
            }
        }
    }
}
