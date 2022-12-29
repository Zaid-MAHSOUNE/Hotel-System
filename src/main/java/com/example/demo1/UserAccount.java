package com.example.demo1;

import com.example.demo1.DBConnect.DBConnectivity;
import com.example.demo1.Modules.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.demo1.login.Indice;
import static com.example.demo1.login.Indice2;
import static javafx.css.StyleOrigin.USER;

public class UserAccount implements Initializable {
    @FXML
    TextField Firstname,LastName , Email  , Phone , Password , Adress, Country , City  ;


    private Stage stage;
    private Scene scene;
    @FXML
    ImageView BACK;
    @FXML
    Label alert;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Firstname.setStyle("-fx-background-color: white;");
        LastName.setStyle("-fx-background-color: white;");
        Email.setStyle("-fx-background-color: white;");

        Phone.setStyle("-fx-background-color: white;");

        Password.setStyle("-fx-background-color: white;");

        Adress.setStyle("-fx-background-color: white;");


        Country.setStyle("-fx-background-color: white;");
        City.setStyle("-fx-background-color: white;");


        Firstname.setEditable(false);
        LastName.setEditable(false);
        Email.setEditable(false);

        Phone.setEditable(false);

        Password.setEditable(false);

        Adress.setEditable(false);

        Country.setEditable(false);
        City.setEditable(false);
         Client C2 = new Client();
         C2 = C2.GetClientInfo(Indice,Indice2);
        Firstname.setText(C2.getFirstName());
        LastName.setText(C2.getLastName());
        Email.setText(C2.getEmail());
        Phone.setText(String.valueOf(C2.getPhoneNo()));
        Password.setText(C2.getPwd());

        Adress.setText(C2.getAdresse());

        Country.setText(C2.getCountry());
        City.setText(C2.getCity());


        BACK.setOnMouseClicked(event -> {
            Parent group = null;
            try {
                group = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
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




    }
    public void editall(ActionEvent e){
        Firstname.setStyle("-fx-border-color: gray;");
        LastName.setStyle("-fx-border-color: gray;");
        Email.setStyle("-fx-border-color: gray;");

        Phone.setStyle("-fx-border-color: gray;");

        Password.setStyle("-fx-border-color: gray;");

        Adress.setStyle("-fx-border-color: gray;");

        Country.setStyle("-fx-border-color: gray;");
        City.setStyle("-fx-border-color: gray;");


        Firstname.setEditable(true);
        LastName.setEditable(true);
        Email.setEditable(true);

        Phone.setEditable(true);
        Password.setEditable(true);


        Adress.setEditable(true);

        Country.setEditable(true);
        City.setEditable(true);

    }
  /*  public void Cancel(ActionEvent event)throws IOException {
      /*  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Page");
        alert.setHeaderText("You are exiting this page ");
        alert.setContentText("Are you sure ?!");
        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Good Bye");
            Platform.exit();
        }
            Parent group = null;
            try {
                group = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(group);
            stage.setScene(scene);
            stage.setWidth(912);
            stage.setHeight(530);
            stage.show();

    }*/
    public void SaveChanges(ActionEvent event){
        Firstname.setStyle("-fx-background-color: white;");
        LastName.setStyle("-fx-background-color: white;");
        Email.setStyle("-fx-background-color: white;");

        Phone.setStyle("-fx-background-color: white;");

        Password.setStyle("-fx-background-color: white;");

        Adress.setStyle("-fx-background-color: white;");

        Country.setStyle("-fx-background-color: white;");
        City.setStyle("-fx-background-color: white;");
        Client C1 = new Client();
       boolean RT = C1.UpdateClient(Firstname.getText(),LastName.getText(),Integer.valueOf(Phone.getText()),Email.getText(),Adress.getText(),City.getText(),Country.getText(),Password.getText());
       if(RT==true){
           Parent group = null;
           try {
               group = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(group);
           stage.setScene(scene);
           stage.setWidth(912);
           stage.setHeight(530);
           stage.show();
       }
       else{
           alert.setVisible(true);
       }


    }


}
