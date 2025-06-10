/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author P15Gen1
 */
public class UsersDAO {

    public static ArrayList<User> GetUsers() {
        DBContext db = DBContext.getInstance();
        ArrayList<User> users = new ArrayList<User>();
        try {
            String sql = """
                         select * 
                         from Users
                         Where Role != 'Admin'
                    """;//(2)
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User account = new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID"));
                users.add(account);
            }
        } catch (Exception e) {
            return null;
        }
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

    public static ArrayList<User> GetAllMember() {
        DBContext db = DBContext.getInstance();
        ArrayList<User> users = new ArrayList<User>();
        try {
            String sql = """
                         Select * From Users Where Role = 'Member'
                    """;//(2)
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User account = new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID"));
                users.add(account);
            }
        } catch (Exception e) {
            return null;
        }
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

    public static ArrayList<User> getUsersByUserNameAndPass(String u, String p) {
        DBContext db = DBContext.getInstance();
        ArrayList<User> accounts = new ArrayList<User>();
        try {
            String sql = """
                         Select * from Users
                         Where Email = ? ANd Password = ?
                         AND SignUpStatus = 'ACCEPTED'
                    """;//(2)
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, u);
            statement.setString(2, p);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User account = new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID"));
                accounts.add(account);
            }
        } catch (Exception e) {
            return accounts;
        }
        return accounts;
    }

    public static boolean addUsers(String fullname, String email, String pass, String role, int clubid) {
        DBContext db = DBContext.getInstance();
        boolean userAdded = false;
        try {
            String checkSql = "SELECT * FROM Users WHERE Email = ?";
            PreparedStatement checkStmt = db.getConnection().prepareStatement(checkSql);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return false;
            }
            String sql = """
                         insert into Users(FullName, Email, Password, Role, ClubID) 
                         values (?, ?, ?, ?, ?)
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, fullname);
            statement.setString(2, email);
            statement.setString(3, pass);
            statement.setString(4, role);
            statement.setInt(5, clubid);
            statement.executeUpdate();
            userAdded = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAdded;
    }

    public static boolean updateUser(String fullname, String email, String pass, int id) {
        DBContext db = DBContext.getInstance();
        try {

            String sql = """
                                UPDATE Users
                                SET FullName = ?, Password = ?, Email = ?
                                WHERE UserID = ?
            """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, fullname);
            statement.setString(2, pass);
            statement.setString(3, email);
            statement.setInt(4, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateRole(String Role, int id, int idclub) {
        DBContext db = DBContext.getInstance();
        boolean check = false;

        try {

            if (!Role.equals("Member")) {
                String sql = """
                        SELECT * FROM Clubs c
                        JOIN Users u ON c.ClubID = u.ClubID
                        WHERE c.ClubID = ? AND u.Role = ?
                     """;
                PreparedStatement statement = db.getConnection().prepareStatement(sql);
                statement.setInt(1, idclub);  
                statement.setString(2, Role); 

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return check;
                }
            }

            String sql1 = """
                            UPDATE Users
                            SET Role = ?
                            WHERE UserID = ?
        """;
            PreparedStatement statement1 = db.getConnection().prepareStatement(sql1);
            statement1.setString(1, Role);
            statement1.setInt(2, id); 

            int rowsAffected = statement1.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return check;
        }
    }

    public static ArrayList<User> searchUser(String str, String currentUserRole) {
        DBContext db = DBContext.getInstance();
        ArrayList<User> accounts = new ArrayList<User>();
        try {
            String sql = """
             SELECT * FROM Users WHERE (FullName = ? OR Email = ? OR Role = ?)
        """;

            if (!"Admin".equals(currentUserRole)) {
                sql += " AND Role != 'Admin'";
            }

            if ("Chairman".equals(currentUserRole)) {
                sql += " AND Role != 'Admin'";
            }
            if ("Vicechairman".equals(currentUserRole)) {
                sql += " AND Role != 'Chairman' AND Role != 'Admin'";
            }
            if ("Teamleader".equals(currentUserRole)) {
                sql += " AND Role != 'Chairman' AND Role != 'Vicechairman' AND Role != 'Admin'";
            }
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, str);
            statement.setString(2, str);
            statement.setString(3, str);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User account = new User(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getInt("ClubID")
                );
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return accounts;
        }
        return accounts;
    }

    public static boolean deleteUserById(int userId) {
        DBContext db = DBContext.getInstance();
        boolean result = false;

        try {

            String deleteEventParticipantsSql = """
            DELETE FROM EventParticipants 
            WHERE UserID = ?
            """;
            PreparedStatement statement1 = db.getConnection().prepareStatement(deleteEventParticipantsSql);
            statement1.setInt(1, userId);
            int rowsAffected1 = statement1.executeUpdate();

            String deleteEventRequestsSql = """
            DELETE FROM EventRequests 
            WHERE UserID = ?
            """;
            PreparedStatement statement2 = db.getConnection().prepareStatement(deleteEventRequestsSql);
            statement2.setInt(1, userId);
            int rowsAffected2 = statement2.executeUpdate();

            String deleteImagesSql = """
            DELETE FROM Images 
            WHERE UserID = ?
            """;
            PreparedStatement statement3 = db.getConnection().prepareStatement(deleteImagesSql);
            statement3.setInt(1, userId);
            int rowsAffected3 = statement3.executeUpdate();

            String deleteUserSql = """
            DELETE FROM Users 
            WHERE UserID = ?
            """;
            PreparedStatement statement4 = db.getConnection().prepareStatement(deleteUserSql);
            statement4.setInt(1, userId);
            int rowsAffected4 = statement4.executeUpdate();

            if (rowsAffected1 > 0 || rowsAffected2 > 0 || rowsAffected3 > 0 || rowsAffected4 > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<String> GetAllEmail() {
        DBContext db = DBContext.getInstance();
        ArrayList<String> emails = new ArrayList<>();
        try {
            String sql = "SELECT Email FROM Users WHERE Role = 'Member'";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                emails.add(rs.getString("Email"));
            }
        } catch (Exception e) {
            return null;
        }
        return emails.isEmpty() ? null : emails;
    }

    public static String GetPassByEmail(String Email) {
        DBContext db = DBContext.getInstance();
        String pass = null;
        try {
            String sql = "SELECT Password FROM Users WHERE Email = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, Email);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                pass = rs.getString("Password");
            }
        } catch (Exception e) {
            return null;
        }
        return pass;
    }

    public static boolean updatePass(String email, String Password) {
        DBContext db = DBContext.getInstance();
        try {

            String sql = """
                                UPDATE Users
                                SET Password = ?
                                WHERE Email = ?
            """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, Password);
            statement.setString(2, email);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<User> getUsersBySignUpStatusPending() {
        DBContext db = DBContext.getInstance();
        ArrayList<User> accounts = new ArrayList<>();
        String sql = """
                 SELECT u.UserID, u.FullName, u.ClubID, u.SignUpStatus, c.ClubName
                 FROM Users u
                 JOIN Clubs c ON u.ClubID = c.ClubID
                 WHERE u.SignUpStatus = 'PENDING'
                 """;

        try (PreparedStatement statement = db.getConnection().prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User account = new User(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getInt("ClubID"),
                        rs.getString("SignUpStatus"),
                        rs.getString("ClubName")
                );
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static boolean updateStatusSignUp(String status, int id) {
        DBContext db = DBContext.getInstance();
        try {

            String sql = """
                                Update Users
                                Set SignUpStatus = ?
                                WHERE UserID = ?
            """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
