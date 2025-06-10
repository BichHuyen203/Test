/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import model.Image;


/**
 *
 * @author P15Gen1
 */
public class ImageDAO {

    public static boolean addImg(int UserID, String imgName, InputStream Image, String dis) {
        DBContext db = DBContext.getInstance();
        boolean userAdded = false;
        try {
            String sql = """
                INSERT INTO Images (UserID, image_name, image_data, description) VALUES (?, ?, ?, ?)
            """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, UserID);
            statement.setString(2, imgName);
            statement.setBinaryStream(3, Image);
            statement.setString(4, dis);

            statement.executeUpdate();
            userAdded = true;
            Image.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAdded;
    }

    public static ArrayList<Image> Check(int UserID, int EventID) {
        DBContext db = DBContext.getInstance();
        ArrayList<Image> imageList = new ArrayList<>();
        try {
            String sql = """
                     Select distinct i.UserID, i.image_name, i.image_data, e.EventID
                     From Images i
                     join EventParticipants e on e.UserID = i.UserID
                     Where e.Status = 'Registered' ,i.UserID = ? And e.EventID = ?
                """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, UserID);
            statement.setInt(2, EventID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                int userID = rs.getInt("UserID");
                String imageName = rs.getString("image_name");
                byte[] imageData = rs.getBytes("image_data");
                int eventID = rs.getInt("EventID");

                Image image = new Image(imageName, userID, sql, eventID);
                imageList.add(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageList;
    }

    public static boolean Checknull(int UserID, int EventID) {
        DBContext db = DBContext.getInstance();
        boolean check = false;
        try {
            String sql = """
                 Select * from EventRequests 
                 where Status = 'Accepted' And UserID = ? And EventID = ?
            """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, UserID);
            statement.setInt(2, EventID);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static ArrayList<Image> GetAllMemberWithAcccept() {
        DBContext db = DBContext.getInstance();
        ArrayList<Image> imageList = new ArrayList<>();
        try {
            String sql = """
                 Select distinct i.id,i.UserID, i.image_name, i.image_data, e.EventID ,i.description , u.FullName ,e.Status
                 From Images i
                  join EventParticipants e on e.UserID = i.UserID
                 join Users u on u.UserID = i.UserID
                  Where e.Status = 'Registered' 
            """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                System.out.println("Không có dữ liệu trả về.");
            } else {
                do {
                    Image image = new Image(rs.getInt("id"), rs.getBytes("image_data"), rs.getString("image_name"),
                            rs.getInt("UserID"), rs.getString("description"),
                            rs.getInt("EventID"), rs.getString("FullName"));
                    imageList.add(image);
                } while (rs.next());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra khi truy vấn cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return imageList;
    }
    public static ArrayList<Image> GetAllMemberWithAccceptEmail() {
        DBContext db = DBContext.getInstance();
        ArrayList<Image> imageList = new ArrayList<>();
        try {
            String sql = """
                 Select distinct u.Email from EventParticipants e
                 join Users u on u.UserID = e.UserID
                 Where Status = 'Registered'
            """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {     
                    
            }
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra khi truy vấn cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return imageList;
    }

    public byte[] getImageById(int imageId) {
        byte[] imageBytes = null;
        String sql = "SELECT image_data FROM images WHERE id = ?";

        try (Connection connection = DBContext.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, imageId);  

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    imageBytes = rs.getBytes("image_data"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }
}
