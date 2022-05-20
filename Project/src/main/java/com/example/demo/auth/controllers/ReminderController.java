package com.example.demo.auth.controllers;

import com.example.demo.auth.data.ReminderRepository;
import com.example.demo.auth.models.Reminder;
import com.example.demo.auth.models.dto.ReminderFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("reminder")
    public ResponseEntity<Object> processCreateReminderForm(@RequestBody @Valid ReminderFormDTO reminderFormDTO,
                                                            HttpServletRequest request, Errors errors, Model model) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Has Errors");
        }

        Reminder newReminder = new Reminder(reminderFormDTO.getName(), reminderFormDTO.getDescription(), reminderFormDTO.getFrequency());
        reminderRepository.save(newReminder);
        try {
            Files.write(Paths.get("output.txt"), Arrays.asList("Reminder Summary:\nName: " + reminderFormDTO.getName() + "\nDescription: "
            + reminderFormDTO.getDescription() + "\nFrequency: " + reminderFormDTO.getFrequency()
            + "\nDate Created: " + reminderFormDTO.getDateCreated()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public ResponseEntity<Object> displayAllReminders() {
        List<Reminder> allReminders = reminderRepository.findAll();
        try {
            Files.write(Paths.get("reminders.txt"), Arrays.asList("All Reminders: " + allReminders));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(allReminders);
    }



//    @PostMapping("delete")
//    public ResponseEntity<Object> processDeleteEventsForm(@RequestParam(required = false) int[] reminderIds) {
//
//        if (reminderIds != null) {
//            for (int id : reminderIds) {
//                reminderRepository.deleteById(id);
//            }
//        }
//
//        return "redirect:";
//    }



}
