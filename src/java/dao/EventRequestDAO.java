/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Clubs;
import model.EventRequest;
import model.User;

/**
 *
 * @author P15Gen1
 */
public class EventRequestDAO {

    public static boolean addEventRequest(int eventID, int userID) {
        DBContext db = DBContext.getInstance();
        boolean result = false;
        try {
            String sql = "INSERT INTO EventRequests (EventID, UserID, Status) VALUES (?, ?, 'Pending')";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, eventID);
            statement.setInt(2, userID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateRequestStatus(int requestID, String status) {
        DBContext db = DBContext.getInstance();
        String sql = "UPDATE EventRequests SET Status = ? WHERE RequestID = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, requestID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<EventRequest> GetAllEvent() {
        DBContext db = DBContext.getInstance();
        ArrayList<EventRequest> event = new ArrayList<EventRequest>();
        try {
            String sql = """
                         Select Distinct e.RequestID, u.UserID, e.EventID,e.Status,u.FullName  from EventRequests e
                         Join Users u on e.UserID = u.UserID 
                         WHERE Status = 'Pending'
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                EventRequest e = new EventRequest(rs.getInt("RequestID"), 
                        rs.getInt("EventID"), 
                        rs.getInt("UserID"), 
                        rs.getString("Status")
                        , rs.getString("FullName"));
                event.add(e);
            }
        } catch (Exception e) {
            return null;
        }
        if (event.isEmpty()) {
            return null;
        } else {
            return event;
        }
    }

    public static ArrayList<String> GetAllEmailAccept() {
        DBContext db = DBContext.getInstance();
        ArrayList<String> emails = new ArrayList<>();
        try {
            String sql = """
                         Select distinct u.Email from EventParticipants e
                         join Users u on u.UserID = e.UserID
                         Where Status = 'Registered'
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                emails.add(rs.getString("Email"));
            }
        } catch (Exception e) {
            return null;
        }
        return emails.isEmpty() ? null : emails;
    }
    
    public static String getUserEventRequestStatus(int userID, int eventID) {
        DBContext db = DBContext.getInstance();
        String status = null;
        try {
            String sql = """
                         SELECT Status FROM EventRequests WHERE UserID = ? AND EventID = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setInt(2, eventID);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                status = rs.getString("Status");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}

