/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author P15Gen1
 */
public class EventParticipants {

    private int EventPartivipantID;
    private int EventID;
    private int UserID;
    private String Status;
    private String participantName;
    private int numberOfParticipants;
    private String eventName;
    private String FullName;
    private String EventDate;
    private String Location;

    public EventParticipants(String Status, String eventName, String FullName, String EventDate, String Location) {
        this.Status = Status;
        this.eventName = eventName;
        this.FullName = FullName;
        this.EventDate = EventDate;
        this.Location = Location;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String EventDate) {
        this.EventDate = EventDate;
    }
    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }
    
    public int getEventPartivipantID() {
        return EventPartivipantID;
    }

    public void setEventPartivipantID(int EventPartivipantID) {
        this.EventPartivipantID = EventPartivipantID;
    }

    public int getEventID() {
        return EventID;
    }

    public void setEventID(int EventID) {
        this.EventID = EventID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public EventParticipants() {
    }

    public EventParticipants(int EventPartivipantID, int EventID, int UserID, String Status) {
        this.EventPartivipantID = EventPartivipantID;
        this.EventID = EventID;
        this.UserID = UserID;
        this.Status = Status;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    

    public EventParticipants(String eventName, String status, String participantName, int numberOfParticipants) {
        this.eventName = eventName;
        this.Status = status;
        this.participantName = participantName;
        this.numberOfParticipants = numberOfParticipants;
    }

    
    @Override
    public String toString() {
        return "EventParticipants{" + "EventPartivipantID=" + EventPartivipantID + ", EventID=" + EventID + ", UserID=" + UserID + ", Status=" + Status + '}';
    }
    

}
