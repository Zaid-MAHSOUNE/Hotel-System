package com.example.demo1.Modules;

import com.example.demo1.DBConnect.DBConnectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Booking {
    private Connection connection;
    private int bookingId;
    private int clientId;
    private int roomNo;
    private Date arrivalDate;
    private Date departureDate;
    private int numAdult;
    private int numChildren;

    public void setInvoiceno(int invoiceno) {
        this.invoiceno = invoiceno;
    }

    private int invoiceno;

    public Booking(){
        DBConnectivity connect = new DBConnectivity();
        connection = connect.getConnection();
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getRoomNo() {
        return roomNo;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getNumAdult() {
        return numAdult;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public int getInvoiceno() {
        return invoiceno;
    }

    public boolean updateBooking(int id) {
        try {
            String sql = "UPDATE Booking SET invoiceno = ? WHERE BookingId = ?";
            PreparedStatement preparedStatementt = connection.prepareStatement(sql);
            preparedStatementt.setInt(1, invoiceno);
            preparedStatementt.setInt(2, id);
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
    public boolean addBooking(int bookingId, int clientId, int roomNo, java.sql.Date arrivalDate, java.sql.Date departureDate, int numAdult, int numChildren) {
        try {
            String sql = "INSERT INTO Booking(bookingId,clientId,roomNo,arrivalDate,departureDate,numAdult,numChildren,invoiceno) values(?,?,?,?,?,?,?) ";
            PreparedStatement preparedStatementt = connection.prepareStatement(sql);
            preparedStatementt.setInt(1, bookingId);
            preparedStatementt.setInt(2, clientId);
            preparedStatementt.setInt(3, roomNo);
            preparedStatementt.setDate(4, arrivalDate);
            preparedStatementt.setDate(5, departureDate);
            preparedStatementt.setInt(6, numAdult);
            preparedStatementt.setInt(7, numChildren);
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
}
