package com.example.demo1.Modules;

import com.example.demo1.DBConnect.DBConnectivity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bill {
    private Connection connection;
    private int invoiceNo;
    private int bookingId;
    private int clientId;
    private Date paymentDate;
    private String paymentMode;
    private float payedCharges;
    private int creditCardNo;
    private String expireDate;

    public boolean addBill(int bookingId,int clientId,Date paymentDate,String paymentMode,float payedCharges,int creditCardNo,String expireDate){
        try{
        String sql = "INSERT INTO Bill(bookingId,clientId,payementDate,payementMode,payedCharges,creditCardNo,expireDate) values(?,?,?,?,?,?,?) ";
        PreparedStatement preparedStatementt = connection.prepareStatement(sql);
        preparedStatementt.setInt(1, bookingId);
        preparedStatementt.setInt(2, clientId);
        preparedStatementt.setDate(3, paymentDate);
        preparedStatementt.setString(4, paymentMode);
        preparedStatementt.setFloat(5, payedCharges);
        preparedStatementt.setInt(6, creditCardNo);
        preparedStatementt.setString(7, expireDate);
        int addr = preparedStatementt.executeUpdate();
        if (addr > 0) {
            return true;
        } else {
            return false;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return false;
    }
    public Bill(){
        DBConnectivity connect = new DBConnectivity();
        connection = connect.getConnection();
    }


}
