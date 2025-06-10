/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Clubs;
import model.User;

/**
 *
 * @author P15Gen1
 */
public class ClubDAO {

    public static ArrayList<Clubs> GetNameClub() {
        DBContext db = DBContext.getInstance();
        ArrayList<Clubs> c = new ArrayList<Clubs>();
        try {
            String sql = """
                         Select * from Clubs
                    """;//(2)
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Clubs clubs = new Clubs(rs.getInt("ClubID"),
                        rs.getString("ClubName"),
                        rs.getString("Description"),
                        rs.getString("EstablishedDate"));
                c.add(clubs);
            }
        } catch (Exception e) {
            return null;
        }
        if (c.isEmpty()) {
            return null;
        } else {
            return c;
        }
    }

    public static String nameClub(int id) {
        DBContext db = DBContext.getInstance();
        String clubName = null;
        try {
            String sql = """
                     Select ClubName from Clubs Where ClubID = ?
                     """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                clubName = rs.getString("ClubName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clubName;
    }

    public static boolean addClub(String Clubname, String Description, String EstablishedDate) {
        DBContext db = DBContext.getInstance();
        boolean userAdded = false;
        try {
            String checkSql = "SELECT * FROM Clubs WHERE ClubName = ?";
            PreparedStatement checkStmt = db.getConnection().prepareStatement(checkSql);
            checkStmt.setString(1, Clubname);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return false;
            }
            String sql = """
                         INSERT INTO Clubs (ClubName, Description, EstablishedDate)
                         VALUES (?,?,?)
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, Clubname);
            statement.setString(2, Description);
            statement.setString(3, EstablishedDate);

            statement.executeUpdate();
            userAdded = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAdded;
    }

}
