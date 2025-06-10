/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author P15Gen1
 */
public class Clubs {

    private int ClubID;
    private String ClubName;
    private String Description;
    private String Established;

    public Clubs() {
    }

    public Clubs(int ClubID, String ClubName, String Description, String Established) {
        this.ClubID = ClubID;
        this.ClubName = ClubName;
        this.Description = Description;
        this.Established = Established;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int ClubID) {
        this.ClubID = ClubID;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String ClubName) {
        this.ClubName = ClubName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getEstablished() {
        return Established;
    }

    public void setEstablished(String Established) {
        this.Established = Established;
    }

    @Override
    public String toString() {
        return "Clubs{" + "ClubID=" + ClubID + ", ClubName=" + ClubName + ", Description=" + Description + ", Established=" + Established + '}';
    }
}
