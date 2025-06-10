/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.oracle.wls.shaded.org.apache.xalan.xsltc.util.IntegerArray;
import dao.ClubDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.EventParticipantDAO;
import dao.UsersDAO;
import jakarta.servlet.RequestDispatcher;
import java.util.ArrayList;
import model.EventParticipants;
import model.User;
import dao.EventDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Clubs;
import model.Events;
import util.Email;

/**
 *
 * @author P15Gen1
 */
public class EventP extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EventP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EventP at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<User> userMem = UsersDAO.GetAllMember();
        request.setAttribute("array", userMem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/historyPar.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("event");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (action.equals("totalp")) {
            ArrayList<EventParticipants> stats = EventParticipantDAO.getEventParticipationStats();
            request.setAttribute("event", stats);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/teamleader.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("personp")) {
            String id = request.getParameter("userID");
            ArrayList<EventParticipants> per = EventParticipantDAO.getEventParticipationOne(Integer.parseInt(id));
            if (per != null) {
                request.setAttribute("eventP", per);
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/historyPar.jsp");
                dispatcher.forward(request, response);
            }
        } else if (action.equals("addev")) {
            String en = request.getParameter("EventName");
            String de = request.getParameter("Des");
            String dt = request.getParameter("eventDateTime");
            String ed = request.getParameter("enddate");
            String lo = "FPT";
            String club = request.getParameter("club");

            if (en == null || en.trim().isEmpty()
                    || de == null || de.trim().isEmpty()
                    || dt == null || dt.trim().isEmpty()
                    || ed == null || ed.trim().isEmpty()
                    || lo == null || lo.trim().isEmpty()
                    || club == null || club.trim().isEmpty()) {

                request.setAttribute("message", "Vui lòng nhập đầy đủ thông tin!");
                request.getRequestDispatcher("view/Chairman.jsp").forward(request, response);
                return;
            }

            String startDateFormatted = convertToDesiredFormat(dt);
            String endDateFormatted = convertToDesiredFormat(ed);

            Date eventStartDate = convertStringToDate(startDateFormatted);
            Date eventEndDate = convertStringToDate(endDateFormatted);

            if (eventEndDate.before(eventStartDate)) {
                request.setAttribute("message", "Ngày kết thúc không được trước ngày bắt đầu!");
                request.getRequestDispatcher("view/Chairman.jsp").forward(request, response);
                return;
            }

            ArrayList<Events> allEvents = EventDAO.GetAllEvent();
            boolean isDuplicate = allEvents.stream()
                    .anyMatch(event -> convertStringToDate(event.getEventDate()).equals(eventStartDate));

            if (isDuplicate) {
                request.setAttribute("message", "Sự kiện này đã tồn tại với ngày giờ đã nhập!");
                request.getRequestDispatcher("view/Chairman.jsp").forward(request, response);
                return;
            }

            if (!isEventInFuture(eventStartDate)) {
                request.setAttribute("message", "Sự kiện phải được tạo trong tương lai!");
                request.getRequestDispatcher("view/Chairman.jsp").forward(request, response);
                return;
            }


            ArrayList<Events> addEventSuccess = EventDAO.addEvent(en, de, startDateFormatted, lo, Integer.parseInt(club), endDateFormatted);

            if (addEventSuccess!=null) {
   
                String clubname = ClubDAO.nameClub(Integer.parseInt(club));

                String subject = "Thông Báo Sự Kiện Mới";
                String content = "Mời bạn đến với sự kiện mới của " + clubname
                        + "<br>Sự kiện có tên là: " + en
                        + "<br>Thời gian tổ chức: " + dt
                        + "<br>Kết thúc vào: " + ed
                        + "<br>Mô tả: " + de;

                ArrayList<String> emailList = UsersDAO.GetAllEmail();

                if (emailList != null && !emailList.isEmpty()) {
                    try {
                        Email.sendEmailToAllMember(emailList, subject, content);
                        request.setAttribute("message", "Sự kiện tạo thành công. Email đã được gửi!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute("message", "Sự kiện tạo thành công, nhưng lỗi khi gửi email!");
                    }
                } else {
                    request.setAttribute("message", "Sự kiện tạo thành công. Không có thành viên nào để gửi email.");
                }
            } else {
                request.setAttribute("message", "Tên sự kiện đã tồn tại!");
            }

            request.getRequestDispatcher("view/Chairman.jsp").forward(request, response);
        }

    }

    private static Date convertStringToDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Định dạng thời gian bạn đang sử dụng
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToDesiredFormat(String isoDate) {
        try {

            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = isoFormat.parse(isoDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isEventInFuture(Date eventDate) {
        Date currentDate = new Date();
        return eventDate.after(currentDate);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
