package com.example.demo.auth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class EmailController {
    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws MessagingException, IOException {
        sendmail("youremail@gmail.com");
        return "Email sent successfully";
    }

    private void sendmail(String email) throws AddressException, MessagingException, IOException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                //Email username and Email password
                //Ensure Less secure app access is on in Google Account > Security
                return new PasswordAuthentication("automatic.imtatiar@gmail.com", "password");
            }
        });
        Message msg = new MimeMessage(session);
        //Email username
        msg.setFrom(new InternetAddress("automatic.imtatiar@gmail.com", false));

        //Recipient email
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        //Subject of email
        msg.setSubject("This is a test");
        //Content of email
        msg.setContent("Haha it worked!", "text/html");
        //Sets email date
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("TestEst?", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

//        Incase we decide to send a file
//        MimeBodyPart attachPart = new MimeBodyPart();
//        attachPart.attachFile("/file/path/file.name");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
        Transport.send(msg);
    }

}
