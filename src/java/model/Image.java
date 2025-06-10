/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author P15Gen1
 */
public class Image {

    private int id;
    private byte[] imageData;
    private String imageName;
    private int userId;
    private String description;
    private int EventID;
    private String Fullname;
    private int RequestID;

    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int RequestID) {
        this.RequestID = RequestID;
    }
   

    public Image(String imageName, int userId, String description, int EventID) {
        this.imageName = imageName;
        this.userId = userId;
        this.description = description;
        this.EventID = EventID;
    }

    public Image(byte[] imageData, String imageName, int userId, String description, int EventID, String Fullname) {
        this.imageData = imageData;
        this.imageName = imageName;
        this.userId = userId;
        this.description = description;
        this.EventID = EventID;
        this.Fullname = Fullname;
    }

    public String getFullname() {
        return Fullname;
    }

    public Image(int id, byte[] imageData, String imageName, int userId, String description, int EventID, String Fullname) {
        this.id = id;
        this.imageData = imageData;
        this.imageName = imageName;
        this.userId = userId;
        this.description = description;
        this.EventID = EventID;
        this.Fullname = Fullname;
    }

    public Image(byte[] imageData, String imageName, int userId, int EventID) {
        this.imageData = imageData;
        this.imageName = imageName;
        this.userId = userId;
        this.EventID = EventID;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getEventID() {
        return EventID;
    }

    public void setEventID(int EventID) {
        this.EventID = EventID;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image(int id, byte[] imageData, String imageName, int userId) {
        this.id = id;
        this.imageData = imageData;
        this.imageName = imageName;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", imageData=" + imageData + ", imageName=" + imageName + ", userId=" + userId + '}';
    }
    
   
}
