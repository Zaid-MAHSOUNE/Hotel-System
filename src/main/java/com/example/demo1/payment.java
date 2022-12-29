package com.example.demo1;

import com.example.demo1.Modules.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.w3c.dom.Document;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.demo1.roomBox.clicked;

public class payment implements Initializable {
    @FXML
    TextField info;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Room room = new Room();
        try {
            room = room.getRoom(clicked);
            info.setText("     You are about to book a "+room.getroomType()+" for "+room.getRoomPrice());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void generate(ActionEvent e) throws IOException {


    }
}
