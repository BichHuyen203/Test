/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.MemberParticipation;

import model.Reports;

/**
 *
 * @author P15Gen1
 */
public class ReportsDAO {

    public static int GetMemberChangeByID(int clubid) {
        DBContext db = DBContext.getInstance();
        int m = 0;
        try {
            String sql = """
                         select MemberChange 
                         from Reports
                         Where ClubID = ?
                    """;//(2)
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubid);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                m = rs.getInt("MemberChange");
            }
        } catch (Exception e) {
            return m;
        }
        return m;
    }

    public static boolean addReports(int clubID, int memberchange, String eventSummary, String participationStats) {
        DBContext db = DBContext.getInstance();
        boolean reportAdded = false;
        try {
            String sql = """
                     INSERT INTO Reports (ClubID, Semester, MemberChange, EventSummary, ParticipationStats, CreatedDate) 
                     VALUES (?, 'Spring 2025', ? , ?, ?, GETDATE())
                     """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubID);
            statement.setInt(2, memberchange);
            statement.setString(3, eventSummary);
            statement.setString(4, participationStats);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                reportAdded = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportAdded;
    }

    public static String getMemberParticipationLevel(int clubid) {
        String result = "";
        String sql = """
                 WITH TotalEvents AS (
                            SELECT 
                                r.Semester,
                                e.ClubID,
                                COUNT(e.EventID) AS TotalEvents
                            FROM Events e
                            JOIN Reports r ON e.ClubID = r.ClubID 
                                AND e.EventDate BETWEEN r.CreatedDate AND DATEADD(MONTH, 4, r.CreatedDate)
                            WHERE e.ClubID = ? 
                            GROUP BY r.Semester, e.ClubID
                        ),
                        UserParticipation AS (
                            SELECT 
                                r.Semester,
                                e.ClubID,
                                COUNT(DISTINCT ep.UserID) AS TotalParticipants
                            FROM EventParticipants ep
                            JOIN Events e ON ep.EventID = e.EventID
                            JOIN Reports r ON e.ClubID = r.ClubID 
                                AND e.EventDate BETWEEN r.CreatedDate AND DATEADD(MONTH, 4, r.CreatedDate)
                            WHERE e.ClubID = ?
                            GROUP BY r.Semester, e.ClubID
                        )
                        SELECT 
                            up.Semester,
                            CASE 
                                WHEN up.TotalParticipants * 1.0 / te.TotalEvents >= 0.8 THEN N'Tham gia trên 80% số sự kiện'
                                WHEN up.TotalParticipants * 1.0 / te.TotalEvents >= 0.5 THEN N'Tham gia từ 50% đến 80%'
                                ELSE N'Tham gia dưới 50%'
                            END AS ParticipationLevel
                        FROM UserParticipation up
                        JOIN TotalEvents te ON up.Semester = te.Semester AND up.ClubID = te.ClubID;
                """;

        try (Connection conn = DBContext.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clubid);
            ps.setInt(2, clubid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getString("ParticipationLevel");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static ArrayList<Reports> GetReports() {
        DBContext db = DBContext.getInstance();
        ArrayList<Reports> reports = new ArrayList<Reports>();
        try {
            String sql = """
                         select * 
                         from Reports
                    """;//(2)
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Reports reportRow = new Reports(rs.getInt("ReportID"),rs.getInt("ClubID"),
                        rs.getString("Semester"),
                        rs.getInt("MemberChange"),
                        rs.getString("EventSummary"),
                        rs.getString("ParticipationStats"),
                        rs.getString("CreatedDate")
                );
                reports.add(reportRow);
            }
        } catch (Exception e) {
            return null;
        }
        if (reports.isEmpty()) {
            return null;
        } else {
            return reports;
        }
    }
    
    public static boolean deleteReport(int ReportID){
            DBContext db = DBContext.getInstance();

            try {
                String sql = """
                                Delete From Reports Where ReportID = ?;
                             """;
                PreparedStatement statement = db.getConnection().prepareStatement(sql);
                statement.setInt(1, ReportID);
                int i  = statement.executeUpdate();
                return i > 0;
        } catch (Exception e) {
        }
        return false;
    }
    

}
