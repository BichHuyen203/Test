/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author P15Gen1
 */
public class Reports {

    private int ReportID;
    private int ClubID;
    private String Semester;
    private int MemberChange;
    private String EventSummary;
    private String ParticipationStats;
    private String CreatedDate;

    public Reports() {
    }

    public int getMemberChange() {
        return MemberChange;
    }

    public void setMemberChange(int MemberChange) {
        this.MemberChange = MemberChange;
    }

    public int getReportID() {
        return ReportID;
    }

    public void setReportID(int ReportID) {
        this.ReportID = ReportID;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int ClubID) {
        this.ClubID = ClubID;
    }

    public String getSemester() {
        return Semester;
    }

    public Reports(int ReportID, int ClubID, String Semester, int MemberChange, String EventSummary, String ParticipationStats, String CreatedDate) {
        this.ReportID = ReportID;
        this.ClubID = ClubID;
        this.Semester = Semester;
        this.MemberChange = MemberChange;
        this.EventSummary = EventSummary;
        this.ParticipationStats = ParticipationStats;
        this.CreatedDate = CreatedDate;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }

    public Reports(int ClubID, String Semester, int MemberChange, String EventSummary, String ParticipationStats, String CreatedDate) {
        this.ClubID = ClubID;
        this.Semester = Semester;
        this.MemberChange = MemberChange;
        this.EventSummary = EventSummary;
        this.ParticipationStats = ParticipationStats;
        this.CreatedDate = CreatedDate;
    }

    public String getEventSummary() {
        return EventSummary;
    }

    public void setEventSummary(String EventSummary) {
        this.EventSummary = EventSummary;
    }

    public String getParticipationStats() {
        return ParticipationStats;
    }

    public void setParticipationStats(String ParticipationStats) {
        this.ParticipationStats = ParticipationStats;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    @Override
    public String toString() {
        return "Reports{" + "ReportID=" + ReportID + ", ClubID=" + ClubID + ", Semester=" + Semester + ", MemberChange=" + MemberChange + ", EventSummary=" + EventSummary + ", ParticipationStats=" + ParticipationStats + ", CreatedDate=" + CreatedDate + '}';
    }

}
