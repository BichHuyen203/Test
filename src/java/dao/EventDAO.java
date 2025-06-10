/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Events;
import model.User;

/**
 *
 * @author P15Gen1
 */
public class EventDAO {

    public static ArrayList<Events> addEvent(String eventName, String description, String eventDate, String location, int clubID, String EndDate) {
        DBContext db = DBContext.getInstance();
        ArrayList<Events> eventsList = new ArrayList<>();
        try {
            String checkSql = "SELECT * FROM Events WHERE EventName = ?";
            PreparedStatement checkStmt = db.getConnection().prepareStatement(checkSql);
            checkStmt.setString(1, eventName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return eventsList;
            }

            String sql = """
                 INSERT INTO Events (EventName, Description, EventDate, Location, ClubID, EndDate)
                 VALUES (?, ?, ?, ?, ?, ?)
                """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, eventName);
            statement.setString(2, description);
            statement.setString(3, eventDate);
            statement.setString(4, location);
            statement.setInt(5, clubID);
            statement.setString(6, EndDate);
            statement.executeUpdate();

            String getAllSql = "SELECT * FROM Events";
            PreparedStatement getAllStmt = db.getConnection().prepareStatement(getAllSql);
            ResultSet rsAll = getAllStmt.executeQuery();

            while (rsAll.next()) {
                Events event = new Events(
                        rsAll.getInt("EventID"),
                        rsAll.getString("EventName"),
                        rsAll.getString("Description"),
                        rsAll.getString("EventDate"),
                        rsAll.getString("Location"),
                        rsAll.getInt("ClubID")
                );
                eventsList.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventsList;
    }

    public static ArrayList<Events> GetAllEvent() {
        DBContext db = DBContext.getInstance();
        ArrayList<Events> ev = new ArrayList<Events>();
        try {
            String sql = "SELECT * FROM Events where StatusEvent = 'Active'";

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int eventId = rs.getInt("EventID");
                String eventName = rs.getString("EventName");
                String description = rs.getString("Description");
                String eventDate = rs.getString("EventDate");
                String location = rs.getString("Location");
                int clubId = rs.getInt("ClubID");

                Events event = new Events(eventId, eventName, description, eventDate, location, clubId);
                ev.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (ev.isEmpty()) {
            return null;
        } else {
            return ev;
        }
    }
    public static ArrayList<Events> GetEventByID(int eventID) {
        DBContext db = DBContext.getInstance();
        ArrayList<Events> ev = new ArrayList<Events>();
        try {
            String sql = "SELECT * FROM Events Where EventID = ?";

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1,eventID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int eventId = rs.getInt("EventID");
                String eventName = rs.getString("EventName");
                String description = rs.getString("Description");
                String eventDate = rs.getString("EventDate");
                String location = rs.getString("Location");
                int clubId = rs.getInt("ClubID");

                Events event = new Events(eventId, eventName, description, eventDate, location, clubId);
                ev.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (ev.isEmpty()) {
            return null;
        } else {
            return ev;
        }
    }
    
    

    public static boolean deleteEventbyID(int eventID) {
        DBContext db = DBContext.getInstance();
        boolean result = false;
        try {
            String deleteEventParticipantsSql = """
            Delete from EventParticipants where EventID = ?
            """;
            PreparedStatement statement1 = db.getConnection().prepareStatement(deleteEventParticipantsSql);
            statement1.setInt(1, eventID);
            int rowsAffected1 = statement1.executeUpdate();

            String deleteEventRequestsSql = """
            Delete from EventRequests where EventID = ?
            """;
            PreparedStatement statement2 = db.getConnection().prepareStatement(deleteEventRequestsSql);
            statement2.setInt(1, eventID);
            int rowsAffected2 = statement2.executeUpdate();

            String deleteEventSql = """
            Delete from Events where EventID = ?
            """;
            PreparedStatement statement3 = db.getConnection().prepareStatement(deleteEventSql);
            statement3.setInt(1, eventID);
            int rowsAffected3 = statement3.executeUpdate();

            if (rowsAffected1 > 0 || rowsAffected2 > 0 || rowsAffected3 > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
