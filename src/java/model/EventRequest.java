/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author P15Gen1
 */

    public class EventRequest {
    private int requestID;
    private int eventID;
    private int userID;
    private String status;
    private String FullName;
    private String EventName;
    


    public EventRequest(int requestID, int eventID, int userID, String status) {
        this.requestID = requestID;
        this.eventID = eventID;
        this.userID = userID;
        this.status = status;
    }

    public EventRequest(int requestID, int eventID, int userID, String status, String FullName) {
        this.requestID = requestID;
        this.eventID = eventID;
        this.userID = userID;
        this.status = status;
        this.FullName = FullName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }
    
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}


