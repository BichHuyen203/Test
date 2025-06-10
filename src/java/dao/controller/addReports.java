/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ReportsDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author P15Gen1
 */
public class addReports extends HttpServlet {

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
            out.println("<title>Servlet addReports</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addReports at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("club");
        PrintWriter out = response.getWriter();
        int mem = ReportsDAO.GetMemberChangeByID(Integer.parseInt(id));
        request.setAttribute("memberchange", mem);
        String p = ReportsDAO.getMemberParticipationLevel(Integer.parseInt(id));
        request.setAttribute("ParticipantStats", p);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/teamleader.jsp");
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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("clubID");
        String p = request.getParameter("ParticipantStats");
        String m = request.getParameter("memberchange");
        String e = request.getParameter("eventSummary");

        if (id.isEmpty() || p.isEmpty() || m.isEmpty() || e.isEmpty()) {
            request.setAttribute("addreport", "Hãy Điền Đầy Đủ Thông Tin!!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/teamleader.jsp");
            dispatcher.forward(request, response);
            return; 
        }

        try {
            int clubID = Integer.parseInt(id); 
            int memberChange = Integer.parseInt(m); 

   
            boolean c = ReportsDAO.addReports(clubID, memberChange, e, p);

            if (c) {
                request.setAttribute("addreport", "Add Thành Công!");
            } else {
                request.setAttribute("addreport", "Add Thất Bại!");
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("addreport", "ID hoặc MemberChange không hợp lệ!");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/teamleader.jsp");
        dispatcher.forward(request, response);
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