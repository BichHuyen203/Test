/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fpt shop
 */
public class MemberParticipation {
    private int userID;
    private int clubID;
    private String semester;
    private int participatedEvents;
    private int totalEvents;
    private String participationLevel;

    public MemberParticipation(int userID, int clubID, String semester, int participatedEvents, int totalEvents, String participationLevel) {
        this.userID = userID;
        this.clubID = clubID;
        this.semester = semester;
        this.participatedEvents = participatedEvents;
        this.totalEvents = totalEvents;
        this.participationLevel = participationLevel;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getParticipatedEvents() {
        return participatedEvents;
    }

    public void setParticipatedEvents(int participatedEvents) {
        this.participatedEvents = participatedEvents;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(int totalEvents) {
        this.totalEvents = totalEvents;
    }

    public String getParticipationLevel() {
        return participationLevel;
    }

    public void setParticipationLevel(String participationLevel) {
        this.participationLevel = participationLevel;
    }

    
}
