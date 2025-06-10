/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author P15Gen1
 */
public class User {
   private int UserID;
   private String FullName;
   private String Email;
   private String Password;
   private String Role;
   private int ClubID;
   private String SignupStatus;
   private String ClubName;
    public String getSignupStatus() {
        return SignupStatus;
    }

    public User(int UserID, String FullName, int ClubID, String SignupStatus, String ClubName) {
        this.UserID = UserID;
        this.FullName = FullName;
        this.ClubID = ClubID;
        this.SignupStatus = SignupStatus;
        this.ClubName = ClubName;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String ClubName) {
        this.ClubName = ClubName;
    }

    public void setSignupStatus(String SignupStatus) {
        this.SignupStatus = SignupStatus;
    }

    public User(int UserID, String FullName, int ClubID, String SignupStatus) {
        this.UserID = UserID;
        this.FullName = FullName;
        this.ClubID = ClubID;
        this.SignupStatus = SignupStatus;
    }
   
    public User() {
    }

    public User(int UserID, String FullName, String Email, String Password, String Role, int ClubID) {
        this.UserID = UserID;
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
        this.ClubID = ClubID;
    }

    public User(String FullName, String Email, String Password, String Role, int ClubID) {
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
        this.ClubID = ClubID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int ClubID) {
        this.ClubID = ClubID;
    }

    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", FullName=" + FullName + ", Email=" + Email + ", Password=" + Password + ", Role=" + Role + ", ClubID=" + ClubID + '}';
    }
   
   
}
