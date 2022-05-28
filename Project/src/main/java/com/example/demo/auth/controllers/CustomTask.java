package com.example.demo.auth.controllers;

import com.example.demo.auth.models.Reminder;
import com.example.demo.auth.models.User;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class CustomTask extends TimerTask {

    private Reminder reminder;

    private User user;

    public CustomTask() {
    }

    public CustomTask(Reminder reminder, User user) {
        this.reminder = reminder;
        this.user = user;
    }

    public void run() {
        try {

            // Your task process
            System.err.println("User id:" + this.user.getId() + " Reminder Name:" + this.reminder.getName());
            EmailController.sendMail(this.reminder, this.user);

        } catch (Exception ex) {
            System.out.println("error running thread " + ex.getMessage());
        }
    }

    public static void runTask(Reminder reminder, User user) {

        //Trasforms time to millis
        List times = List.of(reminder.getTimeToRemind().toString().split(":"));
        int hours = parseInt(times.get(0).toString());
        int minutes =  parseInt(times.get(1).toString());

        //Trasforms frequency to millis
        long alertTime = 1;
        if (reminder.getFrequency() == "Daily") {
            alertTime = 1;
        } else if (reminder.getFrequency() == "Weekly") {
            alertTime = 7;
        } else if (reminder.getFrequency() == "Monthly") {
            alertTime = 30;
        }

        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println(calendar);

        Timer time = new Timer(); // Instantiate Timer Object

        // Start running the task on Monday at 15:40:00, period is set to 8 hours
        // if you want to run the task immediately, set the 2nd parameter to 0
        time.schedule(new CustomTask(reminder, user), calendar.getTime(), TimeUnit.DAYS.toMillis(alertTime));
    }

//    private String toString(LocalTime timeToRemind) {
//        return
//    }
}
