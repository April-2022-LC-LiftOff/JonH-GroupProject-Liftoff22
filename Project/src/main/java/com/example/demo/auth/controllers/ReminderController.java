package com.example.demo.auth.controllers;

import com.example.demo.auth.data.ReminderRepository;
import com.example.demo.auth.models.Reminder;
import com.example.demo.auth.models.User;
import com.example.demo.auth.models.dto.ReminderFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.example.demo.auth.controllers.AuthenticationController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReminderController {

    @Autowired
    ReminderRepository reminderRepository;

    @Autowired
    AuthenticationController authenticationController;

    @PostMapping("reminder")
    public ResponseEntity<Object> processCreateReminderForm(@RequestBody @Valid ReminderFormDTO reminderFormDTO,
                                                            HttpServletRequest request, Errors errors) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Has Errors");
        }

        Reminder newReminder = new Reminder(reminderFormDTO.getName(), reminderFormDTO.getDescription(), reminderFormDTO.getFrequency(), reminderFormDTO.getTimeToRemind());
        newReminder.setRUserId(user.getId());
        reminderRepository.save(newReminder);
//        try {
//            Files.write(Paths.get("output.txt"), Arrays.asList("Reminder Summary:\nName: " + reminderFormDTO.getName() + "\nDescription: "
//            + reminderFormDTO.getDescription() + "\nFrequency: " + reminderFormDTO.getFrequency()
//            + "\nDate Created: " + reminderFormDTO.getDateCreated() + "\nReminder Time: " + reminderFormDTO.getTimeToRemind()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.ok(newReminder);
    }

    @GetMapping("/reminders/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable("id") int id) {
        Optional<Reminder> reminderData = reminderRepository.findById(id);

        if (reminderData.isPresent()) {
            return new ResponseEntity<>(reminderData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reminders/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable("id") int id, @RequestBody Reminder reminder) {
        Optional<Reminder> reminderData = reminderRepository.findById(id);

        if (reminderData.isPresent()) {
            Reminder _reminder = reminderData.get();
            _reminder.setName(reminder.getName());
            _reminder.setDescription(reminder.getDescription());
            _reminder.setFrequency(reminder.getFrequency());
            _reminder.setTimeToRemind(reminder.getTimeToRemind());
            return new ResponseEntity<>(reminderRepository.save(_reminder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reminders/{id}")
    public ResponseEntity<HttpStatus> deleteReminder(@PathVariable("id") int id) {
        try {
            reminderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("reminders")
    public ResponseEntity<Object> displayAllReminders(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        List<Reminder> userReminders = reminderRepository.findByrUserId(user.getId());
        return ResponseEntity.ok(userReminders);
    }



}
