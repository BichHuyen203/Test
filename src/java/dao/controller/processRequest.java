/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.EventParticipantDAO;
import dao.EventRequestDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.EventRequest;

/**
 *
 * @author P15Gen1
 */
public class processRequest extends HttpServlet {

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
            out.println("<title>Servlet processRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet processRequest at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String requestID = request.getParameter("requestID");
        String action = request.getParameter("action");
        String eventID = request.getParameter("eventID");
        String userID = request.getParameter("userID");

        EventRequestDAO dao = new EventRequestDAO();
        boolean result = false;

        if (action != null && requestID != null) {
            if (action.equals("Accept")) {

                result = EventParticipantDAO.ReEvent(Integer.parseInt(eventID), Integer.parseInt(userID), "Registered");
                if (result) {
                    result = dao.updateRequestStatus(Integer.parseInt(requestID), "Accepted");
                }
            } else if (action.equals("Reject")) {
                result = dao.updateRequestStatus(Integer.parseInt(requestID), "Rejected");
            }
        }

        if (result) {
            request.setAttribute("notification", "Yêu cầu đã được xử lý.");
        } else {
            request.setAttribute("notification", "Xử lý yêu cầu thất bại.");
        }


        ArrayList<EventRequest> eventRequests = EventRequestDAO.GetAllEvent();
        request.setAttribute("eventRequests", eventRequests);


        RequestDispatcher dispatcher = request.getRequestDispatcher("view/Chairman.jsp");
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
