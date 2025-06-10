/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author P15Gen1
 */
public class Events {
    private int EventID;
    private String EventName;
    private String Description;
    private String EventDate;
    private String Location;
    private int ClubID;

    public Events() {
    }

    public Events(int EventID, String EventName, String Description, String EventDate, String Location, int ClubID) {
        this.EventID = EventID;
        this.EventName = EventName;
        this.Description = Description;
        this.EventDate = EventDate;
        this.Location = Location;
        this.ClubID = ClubID;
    }

    
    public int getEventID() {
        return EventID;
    }

    public void setEventID(int EventID) {
        this.EventID = EventID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String EventDate) {
        this.EventDate = EventDate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int ClubID) {
        this.ClubID = ClubID;
    }

    @Override
    public String toString() {
        return "Events{" + "EventID=" + EventID + ", EventName=" + EventName + ", Description=" + Description + ", EventDate=" + EventDate + ", Location=" + Location + ", ClubID=" + ClubID + '}';
    }
}
