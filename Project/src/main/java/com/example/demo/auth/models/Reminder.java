package com.example.demo.auth.models;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
    @NotEmpty
    private LocalTime timeToRemind;


    public Reminder(String name, String description, String frequency, LocalTime timeToRemind) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.dateCreated = LocalDate.now();
        this.timeToRemind = timeToRemind;
    }

    public Reminder() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFrequency() {
        return frequency;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setFrequency(String frequency) { this.frequency = frequency; }

    public LocalTime getTimeToRemind() { return timeToRemind; }

    public void setTimeToRemind(LocalTime timeToRemind) { this.timeToRemind = timeToRemind; }

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: " + description + "\nFrequency: " + frequency + "\nDate Created: " + dateCreated
                + "Reminder Time: " + timeToRemind + "\n \n";
    }

}
