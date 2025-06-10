/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ClubDAO;
import dao.UsersDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import model.Clubs;
import model.User;

/**
 *
 * @author P15Gen1
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher rs = request.getRequestDispatcher("/view/login.jsp");
        rs.forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        if (action.equals("login")) {
            String username = request.getParameter("usernamelogin");
            String password = request.getParameter("passwordlogin");
            String hashedPassword = PasswordUtils.hashPassword(password);
            ArrayList<User> users = UsersDAO.getUsersByUserNameAndPass(username, hashedPassword);
            if (users.isEmpty()) {
                String error = "Not match user and password!";
                request.setAttribute("error", error);
                RequestDispatcher rs = request.getRequestDispatcher("view/login.jsp");
                rs.forward(request, response);
            } else {
                String role = users.get(0).getRole();
                ArrayList<User> loggedInUser = UsersDAO.getUsersByUserNameAndPass(username, hashedPassword);
                HttpSession session = request.getSession();
                session.setAttribute("user", loggedInUser);
                if (role.equals("Admin")) {
                    RequestDispatcher rs = request.getRequestDispatcher("view/admin.jsp");
                    rs.forward(request, response);
                } else if (role.equals("Teamleader")) {
                    RequestDispatcher rs = request.getRequestDispatcher("/view/teamleader.jsp");
                    rs.forward(request, response);
                } else if (role.equals("Chairman")) {
                    RequestDispatcher rs = request.getRequestDispatcher("/view/Chairman.jsp");
                    rs.forward(request, response);
                } else if (role.equals("ViceChairman")) {
                    RequestDispatcher rs = request.getRequestDispatcher("view/Vicechairman.jsp");
                    rs.forward(request, response);
                } else {
                    RequestDispatcher rs = request.getRequestDispatcher("view/member.jsp");
                    rs.forward(request, response);
                }
            }

        } else if (action.equals("signup")) {
            RequestDispatcher rs = request.getRequestDispatcher("view/SignUp.jsp");
            rs.forward(request, response);
        } else if (action.equals("Signup")) {
            String usersignup = request.getParameter("usernamesignup");
            String passsignup = request.getParameter("passwordsignup");
            String emailsignup = request.getParameter("email");
            String clubid = request.getParameter("club");
            String passC = request.getParameter("psa");
            String role = "Member";

            String emailRegex = "^[\\w-\\.]+@(gmail\\.com|fpt\\.edu\\.vn)$";
            String passRegex = "^(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{6,}$";

            if (passC.equals(passsignup)) {
                if (passsignup.matches(passRegex)) {
                    if (emailsignup.matches(emailRegex)) {
                        ArrayList<User> users = UsersDAO.GetUsers();
                        boolean emailExists = false;
                        if (users != null) {
                            for (User user : users) {
                                if (user.getEmail().equals(emailsignup)) {
                                    emailExists = true;
                                    break;
                                }
                            }
                        }
                        if (emailExists) {
                            String emailExistsMessage = "Email đã tồn tại. Vui lòng thử với email khác.";
                            request.setAttribute("emailExistsMessage", emailExistsMessage);
                            RequestDispatcher rs = request.getRequestDispatcher("view/SignUp.jsp");
                            rs.forward(request, response);
                        } else {
                            String hashedPassword = PasswordUtils.hashPassword(passsignup);
                            boolean isUserAdded = UsersDAO.addUsers(usersignup, emailsignup, hashedPassword, role, Integer.parseInt(clubid));
                            if (!isUserAdded) {
                                String check = "Có lỗi xảy ra khi thêm người dùng mới.";
                                request.setAttribute("check", check);
                                RequestDispatcher rs = request.getRequestDispatcher("view/SignUp.jsp");
                                rs.forward(request, response);
                            } else {
                                String successMessage = "Bạn đã đăng ký thành công! Vui Lòng Chờ Xét Duyệt!";
                                request.setAttribute("successMessage", successMessage);
                                RequestDispatcher rs = request.getRequestDispatcher("view/login.jsp");
                                rs.forward(request, response);
                            }
                        }
                    } else {
                        String email = "Hãy nhập đúng theo form @fpt.edu.vn hoặc là @gmail.com";
                        request.setAttribute("email", email);
                        RequestDispatcher rs = request.getRequestDispatcher("view/SignUp.jsp");
                        rs.forward(request, response);
                    }
                } else {
                    String passError = "Mật khẩu phải có ít nhất 6 ký tự và chứa ít nhất 1 ký tự đặc biệt";
                    request.setAttribute("passError", passError);
                    RequestDispatcher rs = request.getRequestDispatcher("view/SignUp.jsp");
                    rs.forward(request, response);
                }
            } else {
                String pass = "Mật khẩu và nhập lại mật khẩu không giống nhau";
                request.setAttribute("pass", pass);
                RequestDispatcher rs = request.getRequestDispatcher("view/SignUp.jsp");
                rs.forward(request, response);
            }

        } else if (action.equals("search")) {
            String searchTerm = request.getParameter("searchTerm");
            String rolese = request.getParameter("userList");
            ArrayList<User> users = UsersDAO.searchUser(searchTerm, rolese);
            request.setAttribute("arr", users);
            if (users == null || users.isEmpty()) {
                String pp = "Không tìm thấy gì trong danh sách cả, chắc bạn chưa đủ thẩm quyền rồi";
                request.setAttribute("i", pp);
            }
            RequestDispatcher rs = request.getRequestDispatcher("view/Chairman.jsp");
            rs.forward(request, response);
        } else if (action.equals("update")) {
            String username = request.getParameter("usernameup");
            String password = request.getParameter("passwordup");
            String email = request.getParameter("emailup");

            String specialCharPattern = ".*[!@#$%^&*(),.?\":{}|<>].*";
            if (!password.matches(specialCharPattern)) {
                request.setAttribute("update", "Password phải chứa ít nhất một ký tự đặc biệt.");
                request.setAttribute("us", username);
                request.setAttribute("pss", password);
                request.setAttribute("es", email);
                request.getRequestDispatcher("/view/member.jsp").forward(request, response);
                return;
            }

            String emailPattern = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|fpt\\.edu\\.vn)$";
            if (!email.matches(emailPattern)) {
                request.setAttribute("update", "Email phải có đuôi @gmail.com hoặc @fpt.edu.vn.");
                request.setAttribute("us", username);
                request.setAttribute("pss", password);
                request.setAttribute("es", email);
                request.getRequestDispatcher("/view/member.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            ArrayList<User> user = (ArrayList<User>) session.getAttribute("user");

            if (user == null || user.isEmpty()) {
                request.setAttribute("update", "Không tìm thấy thông tin người dùng trong phiên.");
                request.getRequestDispatcher("/view/member.jsp").forward(request, response);
                return;
            }

            String hashedPassword = PasswordUtils.hashPassword(password);

            boolean ch1 = UsersDAO.updateUser(username, email, hashedPassword, user.get(0).getUserID());
            if (ch1) {
                request.setAttribute("update", "Cập nhật thành công.");
            } else {
                request.setAttribute("update", "Cập nhật thất bại. Vui lòng kiểm tra lại.");
            }

            request.setAttribute("us", username);
            request.setAttribute("pss", password);
            request.setAttribute("es", email);
            request.getRequestDispatcher("/view/member.jsp").forward(request, response);
        }
    }

    public class PasswordUtils {

        public static String hashPassword(String password) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashedBytes = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedBytes) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
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

}
