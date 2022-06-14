package com.example.RMMBR.auth.controllers;

import com.example.RMMBR.auth.models.Reminder;
import com.example.RMMBR.auth.models.User;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class CustomTask extends TimerTask {

    private Reminder reminder;

    private User user;

    private String reminderType;

    public CustomTask() {
    }

    public CustomTask(Reminder reminder, User user, String reminderType) {
        this.reminder = reminder;
        this.user = user;
        this.reminderType = reminderType;
    }

    public void run() {
        try {

            // Your task process
            if(this.reminderType.contains("email")) {
                EmailController.sendMail(this.reminder, this.user);
                System.err.println("User id:" + this.user.getId() + " Reminder Name:" + this.reminder.getName() + " Sms");
            }
            if(this.reminderType.contains("sms")) {
                EmailController.sendSms(this.reminder, this.user);
                System.err.println("User id:" + this.user.getId() + " Reminder Name:" + this.reminder.getName() + " Sms");
            }


        } catch (Exception ex) {
            System.out.println("error running thread " + ex.getMessage());
        }
    }

    public static void runTask(Reminder reminder, User user, String reminderType) {

        //Transforms time to millis
        List times = List.of(reminder.getTimeToRemind().toString().split(":"));
        int hours = parseInt(times.get(0).toString());
        int minutes =  parseInt(times.get(1).toString());

        //Transforms frequency to millis
        long alertTime = 1;
        if (Objects.equals(reminder.getFrequency(), "Daily")) {
            alertTime = 1;
        } else if (Objects.equals(reminder.getFrequency(), "Weekly")) {
            alertTime = 7;
        } else if (Objects.equals(reminder.getFrequency(), "Monthly")) {
            alertTime = 30;
        }

        Calendar calendarNow = Calendar.getInstance();
        long calendarNowTime = calendarNow.getTimeInMillis();
//        String[] currentTimeArr = List.of(calendarNowTime.toString().split(" ")).get(3).split(":");


        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long calendarReminderTime = calendar.getTimeInMillis();

        long reminderDelay = 0;
        if (calendarNowTime < calendarReminderTime) {
            reminderDelay = calendarReminderTime - calendarNowTime;
        } else {
            reminderDelay = TimeUnit.DAYS.toMillis(1) - (calendarNowTime - calendarReminderTime);
        }

        System.out.println(calendar);

        Timer time = new Timer(); // Instantiate Timer Object

        // Start running the task on Monday at 15:40:00, period is set to 8 hours
        // if you want to run the task immediately, set the 2nd parameter to 0
        // calendar.getTime() returns a date which needs
        System.err.println(calendar.getTime());
        time.schedule(new CustomTask(reminder, user, reminderType), reminderDelay, TimeUnit.DAYS.toMillis(alertTime));
    }
}
