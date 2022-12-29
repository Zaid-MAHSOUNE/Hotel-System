package com.example.demo1.Modules;

import com.example.demo1.DBConnect.DBConnectivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class Room {
    private Connection connection;
    private int roomNo;
    private String roomType;

    private float roomPrice;
    private String details;
    private int occupancy;

    public String getDetails() {
        return details;
    }
    public int getRoomNo() {
        return roomNo;
    }
    public float getRoomPrice() {
        return roomPrice;
    }

    public Room(){
        DBConnectivity connect = new DBConnectivity();
        connection = connect.getConnection();
    }
    public Room(int ROOMNO, String ROOMTYPE, float ROOMPRICE, int OCCUPANCY, String DETAILS){
        DBConnectivity connect = new DBConnectivity();
        connection = connect.getConnection();
        this.roomNo=ROOMNO;
        this.roomType=ROOMTYPE;
        this.occupancy=OCCUPANCY;
        this.roomPrice=ROOMPRICE;
        this.details=DETAILS;
    }
    public Room(int ROOMNO, String ROOMTYPE, float ROOMPRICE, int OCCUPANCY){
        DBConnectivity connect = new DBConnectivity();
        connection = connect.getConnection();
        this.roomNo=ROOMNO;
        this.roomType=ROOMTYPE;
        this.occupancy=OCCUPANCY;
        this.roomPrice=ROOMPRICE;
    }
    public String getroomType(){
       return  this.roomType;
    }
    public int getroomname(){
        return  this.roomNo;
    }
    public String getroomDesc(){
        return  this.details;
    }
    public ObservableList getRooms() {
        ObservableList<Room> data = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM room");
            while (resultSet.next()) {
                int roomnbr =  resultSet.getInt("roomNo");
                String roomTP = resultSet.getString("roomType");
                int OCCU = resultSet.getInt("occupancy");
                float price = resultSet.getFloat("roomPrice");
                String details = resultSet.getString("details");
                data.add(new Room(roomnbr,roomTP,price,OCCU,details));
            }
            return data;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    public ObservableList get_Rooms() {
        ObservableList<Room> data = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT RoomNo,RoomType,RoomPrice,occupancy FROM room");
            while (resultSet.next()) {
                int roomno = resultSet.getInt("RoomNo");
                String roomtype = resultSet.getString("RoomType");
                float roomprice = resultSet.getFloat("RoomPrice");
                int roomoccupancy = resultSet.getInt("occupancy");
                data.add(new Room(roomno, roomtype, roomprice, roomoccupancy));
            }
            return data;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    public boolean  changeOccupancy(int id,int occ){
        try{
            String sql = "UPDATE room set occupancy = ? where(roomNo = ?) ";
            PreparedStatement preparedStatementt = connection.prepareStatement(sql);
            preparedStatementt.setInt(1,id);
            preparedStatementt.setInt(2,occ);
            int addr = preparedStatementt.executeUpdate();
            if(addr>0){
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    public Room getRoom(int id) throws SQLException {
        Room room = new Room();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM Room WHERE roomNo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        room.roomType = resultSet.getString("roomType");
        room.roomPrice = resultSet.getFloat("roomPrice");
        room.details = resultSet.getString("details");
        return room;
    }
    public boolean  addRoom(int ROOMNO,String ROOMTYPE,float ROOMPRICE,String DETAILS){
        try{
            String sql = "INSERT INTO room(roomNo,roomType,roomPrice,occupancy,details) values(?,?,?,?,?)";
            PreparedStatement preparedStatementt = connection.prepareStatement(sql);
            preparedStatementt.setInt(1,ROOMNO);
            preparedStatementt.setString(2,ROOMTYPE);
            preparedStatementt.setFloat(3,ROOMPRICE);
            preparedStatementt.setInt(4,0);
            preparedStatementt.setString(5,DETAILS);
            int addr = preparedStatementt.executeUpdate();
            if(addr>0){
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    public int nbrRooms() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) count FROM Room");
        resultSet.next();
        int nbrRoom = resultSet.getInt("count");
        return nbrRoom;
    }



    public int nbrAvailableRooms() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) count FROM Room WHERE occupancy = 0");
        resultSet.next();
        int nbrRoom = resultSet.getInt("count");
        return nbrRoom;
    }

    public void deleteRoom(int id) throws  SQLException{
        String sql = "DELETE FROM `room` WHERE roomNo = ?";
        PreparedStatement preparedStatementt = connection.prepareStatement(sql);
        preparedStatementt.setInt(1,id);
        preparedStatementt.executeUpdate();
    }


    public static void main(String[] args) {
        Room ne = new Room();
        ObservableList<Room> data = ne.getRooms();
    }

    public int getOccupancy() {
        return occupancy;
    }
}
