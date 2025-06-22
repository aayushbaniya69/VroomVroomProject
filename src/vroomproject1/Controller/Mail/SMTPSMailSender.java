/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller.Mail;


import javax*;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail;

/**
 *
 * @author Dell
 */
public class SMTPSMailSender {
     private static final String host = "smtp.gmail.com";

    private static final String port = "587"; 

    private static final String email = "trisharajbanshi06@gmail.com";

    private static String password = "nlfs oodm yavr kfsh";
    
    // Send Email Method

    public static boolean sendMail(String recipient, String subject, String body) {

        // Set up properties for the email session

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);  
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");  // Enable STARTTLS
        properties.put("mail.smtp.starttls.enable", "true");
        

        // Create a session with the properties
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {

            // Create the message

            Message message = new Message(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);
            
            // Send the message

            Transport.send(message);
            System.out.println("Mail sent successfully!"+recipient);
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }
}


