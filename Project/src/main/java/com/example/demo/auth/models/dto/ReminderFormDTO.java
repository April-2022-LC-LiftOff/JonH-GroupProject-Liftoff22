package com.example.demo.auth.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ReminderFormDTO {

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

    public LocalDate getDateCreated() {

        if(dateCreated==null) {
            dateCreated = LocalDate.now();
        }
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
