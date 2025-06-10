/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Transport; 
import javax.mail.internet.MimeMessage;

/**
 *
 * @author fpt shop
 */
public class Email {
    //email: trongstudy@gmail.com
    //password: hssloudfpatkbgns
        static final String from = "baolong2000k4@gmail.com";
        static final String password = "yyntftfoglwowmkt";
        

    public static boolean sendEmail(String to,String tieuDe,String noiDung) {
        //properties: khai bao cac thuoc tinh 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");//SMTP HOST
        props.put("mail.smtp.port", "587"); //TLS 587  SSL 456
        props.put("mail.smtp.auth", "true"); //TLS 587  SSL 456
        props.put("mail.smtp.starttls.enable", "true"); //TLS 587  SSL 456
        
        //Authenthicator
            Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password); 
            }
        };
            
            Session session = Session.getInstance(props,auth );

            MimeMessage msg = new MimeMessage(session);
            try {
                msg.addHeader("Content-Type", "text/HTML; charset=UTF-8"); 
                
                //nguoi gui
                msg.setFrom(from);
                
                msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to,false));
                              
                msg.setSubject(tieuDe);
                
                //Quy dinh ngay gui
                msg.setSentDate(new Date());
                
                    // Nếu muốn nhận thư trả lời bằng 1 Email khác thì dùng 
//                    msg.setReplyTo(.....);
            
                //Noi dung Email muon gui(Dung .setText chỉ khi gửi văn bản thuần)
//                msg.setText("<!DOCTYPE html>\n" +
//                                "<html>\n" +
//                                "<head>\n" +
//                                "<title>Page Title</title>\n" +
//                                "</head>\n" +
//                                "<body>\n" +
//                                "\n" +
//                                "<h1>My First Heading</h1>\n" +
//                                "<p>My first paragraph.</p>\n" +
//                                "\n" +
//                                "</body>\n" +
//                                "</html>","UTF-8");
                

                //Nếu muốn gửi dạng HTML thì dùng .setContent và thay đổi charset thành text/html
//                msg.setContent("<!DOCTYPE html>\n" +
//                                "<html>\n" +
//                                "<head>\n" +
//                                "<title>Page Title</title>\n" +
//                                "</head>\n" +
//                                "<body>\n" +
//                                "\n" +
//                                "<h1>My First Heading</h1>\n" +
//                                "<p>My first paragraph.</p>\n"
//                                + "<img src=\"https://scontent.fhan5-2.fna.fbcdn.net/v/t1.6435-9/56521051_2381863042059965_3675710390047604736_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=833d8c&_nc_ohc=7ExTcAvwbIoQ7kNvgGeentm&_nc_oc=AdgZvOE63-nnjtMTmgsUefOPIoyVVNKEYf3D1G2mTK1hJ2fvGVwRcEsHK4ZkejW9jJ4&_nc_zt=23&_nc_ht=scontent.fhan5-2.fna&_nc_gid=8R-vR4xxR8_Ih-rtrs8IGw&oh=00_AYEvFQMS6uV5BLkUVJw3PofJWWuzcRnmGpJpPYYbsz8hRg&oe=67FC8D03" alt=\"NgoBaKha\" >" +
//                                "\n" +
//                                "</body>\n" +
//                                "</html>","text/html");
                
                //Nếu muốn tái sử dụng thì 
                msg.setContent(
                    "<html><body>" +
                    "<h1>" + tieuDe + "</h1>" +
                    "<p>" + noiDung + "</p>" +
                    "<img src='' alt='Hình ảnh'>" +
                    "</body></html>",
                    "text/html; charset=UTF-8"
                );


                Transport.send(msg);
                System.out.println("Da Gui Mail Thanh Cong");
                return true;
            } catch (Exception e) {
                System.out.println("Gap loi trong qua trinh gui email");
                e.printStackTrace();
                return false;    
            }   
    }
    
    public static void sendEmailToAllMember(List<String> emailList, String tieuDe, String noiDung) {
        for (String email : emailList) {
            boolean success = sendEmail(email, tieuDe, noiDung);
            if (!success) {
                System.out.println("⚠️ Không thể gửi email đến: " + email);
            }
        }
    }
    
     public static void sendEmailPass(String email, String tieuDe, String noiDung) { 
            boolean success = sendEmail(email, tieuDe, noiDung);
            if (!success) {
                System.out.println("⚠️ Không thể gửi email đến: " + email);
            }
        }
   
}
