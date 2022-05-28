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
import java.util.*;

import com.example.demo.auth.controllers.AuthenticationController;

@RestController
@RequestMapping("/api")
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
                return new PasswordAuthentication("automatic.imtatiar@gmail.com", "password");
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

    static void sendSms(Reminder reminder, User user) throws AddressException, MessagingException, IOException, IOException {
    String sms;
        if (Objects.equals(user.getCarrier(), "Alltel")) {
            sms = user.getMobile() + "@message.alltel.com";
        } else if (Objects.equals(user.getCarrier(), "AT&T")) {
            sms = user.getMobile() + "@mmode.com";
        } else if (Objects.equals(user.getCarrier(), "Boost Mobile")) {
            sms = user.getMobile() + "@myboostmobile.com";
        } else if (Objects.equals(user.getCarrier(), "Cingular")) {
            sms = user.getMobile() + "@mobile.mycingular.com";
        } else if (Objects.equals(user.getCarrier(), "Nextel")) {
            sms = user.getMobile() + "@messaging.nextel.com";
        } else if (Objects.equals(user.getCarrier(), "T-Mobile")) {
            sms = user.getMobile() + "@messaging.sprintpcs.com";
        } else if (Objects.equals(user.getCarrier(), "Verizon")) {
            sms = user.getMobile() + "@vtext.com";
        } else if (Objects.equals(user.getCarrier(), "Virgin Mobile USA")) {
            sms = user.getMobile() + "@vmobl.com";
        } else if (Objects.equals(user.getCarrier(), "Sprint")) {
            sms = user.getMobile() + "@messaging.sprintpcs.com";
        } else {
            sms = "automatic.imtatiar@gmail.com";
        }
        System.err.println(sms + " Carrier:" + user.getCarrier() + "|");


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
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sms));
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

    @GetMapping("/sendemail/{id}")
    public ResponseEntity<Object> sendReminderEmailbyId(@PathVariable("id") int id, HttpServletRequest request) throws AddressException, MessagingException, IOException, IOException  {

        Optional<Reminder> reminderData = reminderRepository.findById(id);

        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);

        if(reminderData.isPresent()) {
            Reminder emailReminder = reminderData.get();
            CustomTask.runTask(emailReminder, user, "email");
//            sendmail(emailReminder, request);
            return new ResponseEntity<>(emailReminder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sendsms/{id}")
    public ResponseEntity<Object> sendReminderSmsbyId(@PathVariable("id") int id, HttpServletRequest request) throws AddressException, MessagingException, IOException, IOException  {

        Optional<Reminder> reminderData = reminderRepository.findById(id);

        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);

        if(reminderData.isPresent()) {
            Reminder emailReminder = reminderData.get();
            CustomTask.runTask(emailReminder, user, "sms");
//            sendmail(emailReminder, request);
            return new ResponseEntity<>(emailReminder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
