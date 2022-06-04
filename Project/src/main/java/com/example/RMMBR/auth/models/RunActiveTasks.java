package com.example.RMMBR.auth.models;

import com.example.RMMBR.auth.controllers.CustomTask;
import com.example.RMMBR.auth.data.ReminderRepository;
import com.example.RMMBR.auth.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component
public class RunActiveTasks {

    private static ReminderRepository reminderRepository;

    private static UserRepository userRepository;

    @Autowired
    public RunActiveTasks(ReminderRepository reminderRepository, UserRepository userRepository) {
        RunActiveTasks.reminderRepository = reminderRepository;
        RunActiveTasks.userRepository = userRepository;
    }

    @PostConstruct
    public static void runAllActiveTasks() { // Should run all reminders with a status set to active
        List<Reminder> reminders = reminderRepository.findAll();
        for (Reminder reminder : reminders) {
            System.out.println(reminder.getName());
            if (Objects.equals(reminder.getStatus(), "active")) {
                System.err.println("Activated reminder: " + reminder.getName());
                if(userRepository.findById(reminder.getRUserId()).isPresent()) {
                    User user = userRepository.findById(reminder.getRUserId()).get();
                    CustomTask.runTask(reminder, user, reminder.getSendType().toLowerCase(Locale.ROOT));
                }
            }
        }
    }


}
