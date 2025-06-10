package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.Email;
import dao.UsersDAO;
import jakarta.servlet.RequestDispatcher;
import java.util.Arrays;

public class Mail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendingEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendingEmail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Lấy email từ request
            String email = request.getParameter("email");
            String tieuDe = request.getParameter("tieuDe");
            String noiDung = request.getParameter("noiDung");

            if (email == null || email.isEmpty()) {
                out.println("<h3 style='color:red;'>Bạn chưa nhập email!</h3>");
                return;
            }

           
            Email.sendEmailToAllMember(Arrays.asList(email), tieuDe, noiDung);

            
            out.println("<h3 style='color:green;'>Email đã được gửi đến " + email + "!</h3>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Đã xảy ra lỗi khi gửi email!</h3>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Gửi email đến một người qua phương thức GET";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String tieuDe = request.getParameter("tieuDe");
            String noiDung = request.getParameter("noiDung");

            List<String> emailList = UsersDAO.GetAllEmail();

            if (emailList == null || emailList.isEmpty()) {
                request.setAttribute("deleteevent", "Không Thành công!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/Chairman.jsp");
                dispatcher.forward(request, response);
                return;  
            }

            Email.sendEmailToAllMember(emailList, tieuDe, noiDung);

            request.setAttribute("deleteevent", "Email đã được gửi thành công đến tất cả thành viên!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/Chairman.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Đã xảy ra lỗi khi gửi email!</h3>");
        } finally {
        
            if (out != null) {
                out.close();
            }
        }
    }

}
