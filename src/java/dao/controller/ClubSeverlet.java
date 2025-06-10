/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ClubDAO;
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
public class ClubSeverlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ClubSeverlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClubSeverlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String club = request.getParameter("clubname");
        String des = request.getParameter("des");
        String date = request.getParameter("date");
        if(club.isEmpty()){
           request.setAttribute("checkevent1", "Không Được Để ClubName Chống!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/Vicechairman.jsp");
            dispatcher.forward(request, response); 
        }if(des.isEmpty()){
           request.setAttribute("checkevent1", "Vui Lòng Nhập Phần Mô Tả!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/Vicechairman.jsp");
            dispatcher.forward(request, response); 
        }
        boolean c = ClubDAO.addClub(club, des, date);
        if(c){
            request.setAttribute("checkevent1", "Tạo Thành Công!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/Vicechairman.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("checkevent1", "Đã Có Tên Club Vui Lòng Tạo Lại!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/Vicechairman.jsp");
            dispatcher.forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
