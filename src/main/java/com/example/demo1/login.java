package com.example.demo1;

import com.example.demo1.Modules.Client;
import com.example.demo1.Modules.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class login
{
    static String Indice;
    static String Indice2;
    private Stage stage;
    private Scene scene;
    @FXML
    Label alert;
    @FXML
    TextField username;
    @FXML
    TextField password;
    public void connexion (ActionEvent event){
        if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert.setVisible(true);
                alert.setText("Fill All The Blanks");
        }
        else{
            Client client = new Client();
            admin Admin = new admin();
            boolean checkClient = client.CheckUser(username.getText(),password.getText());
            boolean checkAdmin = Admin.CheckAdmin(username.getText(),password.getText());
                    if(checkAdmin){
                        Parent group = null;
                        try {
                            group = FXMLLoader.load(getClass().getResource("adminH.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(group);
                        stage.setScene(scene);
                        stage.setTitle("Hotel | Home");
                        stage.setWidth(920);
                        stage.setHeight(525);
                        stage.show();
                    }
                    else if(checkClient){
                        Parent group = null;
                        try {
                            group = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(group);
                        stage.setScene(scene);
                        stage.setTitle("Hotel | Home");
                        stage.setWidth(920);
                        stage.setHeight(525);
                        stage.show();
                        Indice = username.getText();
                        Indice2 = password.getText();
                }else{
                    alert.setVisible(true);
                    alert.setText("Username Or Password Incorrect");
                    username.setText("");
                    password.setText("");
                }
            }
        }
        public void switchToRegister(ActionEvent event){
            Parent group = null;
            try {
                group = FXMLLoader.load(getClass().getResource("register.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(group);
            stage.setScene(scene);
            stage.setTitle("Hotel | Register");
            stage.setWidth(920);
            stage.setHeight(525);
            stage.show();
            }
    }


