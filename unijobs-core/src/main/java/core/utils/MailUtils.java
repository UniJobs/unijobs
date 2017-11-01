package core.utils;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//TODO
//DANGER
//Class not working yet
//Work in progress
public class MailUtils {

    public static void sendMail(String mailSubject, String mailMessage, String recipientMailAddress) {

        final String username = "unijobs17@gmail.com";
        final String password = "unicodersSUCK";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMailAddress));
            message.setSubject(mailSubject);
            message.setText(mailMessage);

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }
}
