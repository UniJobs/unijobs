package core.utils;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//TODO
//DANGER
//Class not working yet
//Work in progress
public class MailUtils {

    public static void sendMail(String mailSubject, String mailMessage, String recipientMailAddress) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        final String username = "unijobs17@gmail.com";
        final String password = "unicodersSUCK";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientMailAddress,false));
            msg.setSubject(mailSubject);
            msg.setText(mailMessage);
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent to " + recipientMailAddress);
        }catch (MessagingException e){
            System.out.println("MAIL ERROR: " + e);
        }
    }
}
