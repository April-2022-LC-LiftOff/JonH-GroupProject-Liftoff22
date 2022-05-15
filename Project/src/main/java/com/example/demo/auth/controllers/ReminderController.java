package com.example.demo.auth.controllers;

import com.example.demo.auth.data.ReminderRepository;
import com.example.demo.auth.models.Reminder;
import com.example.demo.auth.models.dto.ReminderFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/reminder")
public class ReminderController {

    @Autowired
    ReminderRepository reminderRepository;

    @PostMapping("")
    public ResponseEntity<Object> processCreateReminderForm(@RequestBody @Valid ReminderFormDTO reminderFormDTO,
                                                            HttpServletRequest request, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
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
