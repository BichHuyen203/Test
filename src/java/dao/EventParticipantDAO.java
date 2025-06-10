/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.EventParticipants;
import model.User;

/**
 *
 * @author P15Gen1
 */
public class EventParticipantDAO {

    public static ArrayList<EventParticipants> getEventParticipants() {
        DBContext db = DBContext.getInstance();
        ArrayList<EventParticipants> participants = new ArrayList<EventParticipants>();

        try {
            String sql = """
                         SELECT 
                        EventParticipantID, EventID, UserID, Status 
                        FROM EventParticipants """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EventParticipants p = new EventParticipants(rs.getInt("EventParticipantID"),
                        rs.getInt("EventID"),
                        rs.getInt("UserID"),
                        rs.getString("Status"));
                participants.add(p);
            }
        } catch (Exception e) {
            return null;
        }
        if (participants.isEmpty()) {
            return null;
        } else {
            return participants;
        }
    }

    public static ArrayList<EventParticipants> getEventParticipationStats() {
        DBContext db = DBContext.getInstance();
        ArrayList<EventParticipants> stats = new ArrayList<>();

        try {
            String sql = """
                         SELECT 
                         e.EventName, 
                         ep.Status, 
                         u.FullName AS ParticipantName,  
                         COUNT(ep.EventParticipantID) AS NumberOfParticipants
                         FROM 
                         dbo.Events e
                         JOIN 
                         dbo.EventParticipants ep ON e.EventID = ep.EventID
                         JOIN 
                         dbo.Users u ON ep.UserID = u.UserID  
                         WHERE 
                         u.Role = 'Member'
                         GROUP BY 
                         e.EventName, ep.Status, u.FullName;  
                         """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
             while (rs.next()) {
            String eventName = rs.getString("EventName");
            String status = rs.getString("Status");
            String participantName = rs.getString("ParticipantName");
            int numberOfParticipants = rs.getInt("NumberOfParticipants");
            
            EventParticipants stat = new EventParticipants(eventName, status, participantName, numberOfParticipants);
            stats.add(stat);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }
    public static ArrayList<EventParticipants> getEventParticipationOne(int UserID) {
        DBContext db = DBContext.getInstance();
        ArrayList<EventParticipants> stats = new ArrayList<>();

        try {
            String sql = """
                         SELECT
                             u.UserID,               
                             u.FullName,             
                             e.EventName,            
                             e.Description,          
                             e.EventDate,            
                             e.Location,             
                             ep.Status               
                         FROM
                             Users u
                         JOIN
                             EventParticipants ep ON u.UserID = ep.UserID
                         JOIN
                             Events e ON ep.EventID = e.EventID
                         WHERE
                             u.UserID = ?        
                         ORDER BY
                             e.EventDate DESC      
                         """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, UserID);
            ResultSet rs = statement.executeQuery();
             while (rs.next()) {
            String eventName = rs.getString("EventName");
            String status = rs.getString("Status");
            String Fullname = rs.getString("FullName");
            String EventDate = rs.getString("EventDate");
            String locaion = rs.getString("Location");
            
            EventParticipants stat = new EventParticipants(status, eventName, Fullname, EventDate, locaion);
            stats.add(stat);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }
    public static boolean ReEvent(int EventID, int UserID, String status) {
        DBContext db = DBContext.getInstance();
        boolean reevent = false;
        
        try {
            
            
            String sql = """
                         insert into EventParticipants(EventID, UserID, Status) 
                         values (?,?,?)
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, EventID);
            statement.setInt(2, UserID);
            statement.setString(3, status);
            statement.executeUpdate();  
            reevent = true; 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reevent;  
    
        }
    
    public static boolean CheckAA(int EventID, int UserID, String status) {
    DBContext db = DBContext.getInstance();
    boolean reevent = false;
    try {
        String sql = """
                     UPDATE EventParticipants
                     SET Status = Registered
                     WHERE EventID = ? AND UserID = ? AND Status = ?;
                """;
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.setInt(1, EventID);    
        statement.setInt(2, UserID);     
        statement.setString(3, status); 
        int rowsAffected = statement.executeUpdate();
        
        if (rowsAffected > 0) {
            reevent = true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return reevent;  
    }
    
    public static boolean updateAorA(String status , int UserID, int EventID) {
        DBContext db = DBContext.getInstance();
        try {

            String sql = """
                                UPDATE EventParticipants
                                SET Status = ?
                                WHERE EventID = ? And UserID = ? And Status = 'Registered'
            """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            
            statement.setString(1, status);  
            statement.setInt(2, EventID);         
            statement.setInt(3, UserID);         
                  
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }
}
