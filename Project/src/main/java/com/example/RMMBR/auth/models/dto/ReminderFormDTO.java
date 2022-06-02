package com.example.RMMBR.auth.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReminderFormDTO {

    @NotNull
    private int id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid name. Must be between 3 and 20 characters.")
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
    @NotEmpty
    private LocalTime timeToRemind;

    private int rUserId;

    @NotNull
    private String reminderCategory;

    @NotNull
    private String sendType;

    @NotNull
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getId() { return id; }

    public LocalDate getDateCreated() {

        if(dateCreated==null) {
            dateCreated = LocalDate.now();
        }
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalTime getTimeToRemind() {
        return timeToRemind;
    }

    public void setTimeToRemind(LocalTime timeToRemind) {
        this.timeToRemind = timeToRemind;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


