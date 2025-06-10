/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ImageDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import model.Image;
import model.User;

/**
 *
 * @author P15Gen1
 */
@MultipartConfig
public class uploadImageSeverlet extends HttpServlet {

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
            out.println("<title>Servlet uploadImage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadImage at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        ArrayList<User> user = (ArrayList<User>) session.getAttribute("user");
        Part filePart = request.getPart("image");
        String feedback = request.getParameter("feedback");
        String imgName = request.getParameter("nameimg");
        String EventID = request.getParameter("event");


        if (user == null || user.isEmpty()) {
            request.setAttribute("eventcheck", "Người dùng không tồn tại trong phiên.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/login.jsp");
            dispatcher.forward(request, response);
        } else {
           
            if (filePart == null || filePart.getSize() == 0) {
                request.setAttribute("eventcheck", "Hãy Lựa Chọn Ảnh Phù Hợp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/member.jsp");
                dispatcher.forward(request, response);
            } else {
              
                if (feedback == null || feedback.isEmpty()) {
                    request.setAttribute("eventcheck", "Hãy Viết Nội Dung");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("view/member.jsp");
                    dispatcher.forward(request, response);
                } else {

                    if (imgName == null || imgName.isEmpty()) {
                        request.setAttribute("eventcheck", "Hãy Nhập Tên Ảnh");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("view/member.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        
                        boolean img = ImageDAO.Checknull(user.get(0).getUserID(), Integer.parseInt(EventID));
                        if (img == false) {
                            request.setAttribute("eventcheck", "Sự Kiện Bạn Chưa Đăng Kí");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("view/member.jsp");
                            dispatcher.forward(request, response);
                        } else {
                           
                            InputStream inputStream = filePart.getInputStream();
                            boolean c = ImageDAO.addImg(user.get(0).getUserID(), imgName, inputStream, feedback);
                            if (c) {
                                request.setAttribute("eventcheck", "Gửi Thành Công");
                                RequestDispatcher dispatcher = request.getRequestDispatcher("view/member.jsp");
                                dispatcher.forward(request, response);
                            } else {
                                request.setAttribute("eventcheck", "Gửi Không Thành Công");
                                RequestDispatcher dispatcher = request.getRequestDispatcher("view/member.jsp");
                                dispatcher.forward(request, response);
                            }
                        }
                    }
                }
            }
        }

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

    public String encodeImageToBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
