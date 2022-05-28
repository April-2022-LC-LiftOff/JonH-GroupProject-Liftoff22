package com.example.demo.auth.controllers;

import com.example.demo.auth.data.ReminderRepository;
import com.example.demo.auth.models.Reminder;
import com.example.demo.auth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import com.example.demo.auth.controllers.AuthenticationController;

@RestController
@RequestMapping("/api/sendemail")
public class EmailController {

    @Autowired
    ReminderRepository reminderRepository;

    @Autowired
    AuthenticationController authenticationController;


    static void sendMail(Reminder reminder, User user) throws AddressException, MessagingException, IOException, IOException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                //Email username and Email password
                //Ensure Less secure app access is on in Google Account > Security
                return new PasswordAuthentication("automatic.imtatiar@gmail.com", "Backup123Power4P@$$w0rDH@h@");
            }
        });
        Message msg = new MimeMessage(session);
        //Email username
        msg.setFrom(new InternetAddress("automatic.imtatiar@gmail.com", false));

        //Recipient email
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
        //Subject of email
        msg.setSubject("ReminderApp: " + reminder.getName());
        //Content of email
        msg.setContent(reminder.toString(), "text/html");
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

//    static void sendSms(Reminder reminder, User user) throws AddressException, MessagingException, IOException, IOException {
//    String sms;
//        if (user.getCarrier() == "") {
//            sms = user.getMobile() + "";
//        } else if (user.getCarrier() == "") {
//            sms = user.getMobile() + "";
//        }
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                //Email username and Email password
//                //Ensure Less secure app access is on in Google Account > Security
//                return new PasswordAuthentication("automatic.imtatiar@gmail.com", "Backup123Power4P@$$w0rDH@h@");
//            }
//        });
//        Message msg = new MimeMessage(session);
//        //Email username
//        msg.setFrom(new InternetAddress("automatic.imtatiar@gmail.com", false));
//
//        //Recipient email
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sms));
//        //Subject of email
//        msg.setSubject("ReminderApp: " + reminder.getName());
//        //Content of email
//        msg.setContent(reminder.toString(), "text/html");
//        //Sets email date
//        msg.setSentDate(new Date());
//
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("TestEst?", "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//
////        Incase we decide to send a file
////        MimeBodyPart attachPart = new MimeBodyPart();
////        attachPart.attachFile("/file/path/file.name");
////        multipart.addBodyPart(attachPart);
////        msg.setContent(multipart);
//        Transport.send(msg);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> sendReminderbyId(@PathVariable("id") int id, HttpServletRequest request) throws AddressException, MessagingException, IOException, IOException  {

        Optional<Reminder> reminderData = reminderRepository.findById(id);

        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);

        if(reminderData.isPresent()) {
            Reminder emailReminder = reminderData.get();
            CustomTask.runTask(emailReminder, user);
//            sendmail(emailReminder, request);
            return new ResponseEntity<>(emailReminder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
