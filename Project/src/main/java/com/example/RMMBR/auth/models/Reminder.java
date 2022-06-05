package com.example.RMMBR.auth.models;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reminder extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotNull
    @NotEmpty
    private String frequency;

    @NotNull
    @NotEmpty
    private LocalDate dateCreated;

    @NotNull
    @NotEmpty(message="Time is required")
    private LocalTime timeToRemind;

    private int rUserId;

    @NotNull(message="Category is required")
    private String reminderCategory;

    @NotNull(message="SendType is required")
    private String sendType;

    public Reminder(String name, String description, String frequency, LocalTime timeToRemind, String reminderCategory, String sendType) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.dateCreated = LocalDate.now();
        this.timeToRemind = timeToRemind;
        this.reminderCategory = reminderCategory;
        this.sendType = sendType;
    }
    public Reminder(String name) {
        this.name = name;
    }

    public Reminder() {}

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getFrequency() { return frequency; }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setFrequency(String frequency) { this.frequency = frequency; }

    public LocalTime getTimeToRemind() { return timeToRemind; }

    public void setTimeToRemind(LocalTime timeToRemind) { this.timeToRemind = timeToRemind; }

    public int getRUserId() {
        return rUserId;
    }

    public void setRUserId(int rUserId) {
        this.rUserId = rUserId;
    }

    public String getReminderCategory() {
        return reminderCategory;
    }

    public void setReminderCategory(String reminderCategory) {
        this.reminderCategory = reminderCategory;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    @Override
    public String toString() {
        return "Name: " + name + "<br>Description: " + description + "<br>Reminder Time: "
                + timeToRemind + "<br>Category: " + reminderCategory + "\n \n";
    }

}
