package com.ibn.firnas.dto.airCrew;

import com.ibn.firnas.utils.enums.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDetailsDTO(Long userId,
                             String firstName,
                             String lastName,
                             @NotBlank(message = "The jobTitle shouldn't be Empty")

                             String jobTitle,
                             @NotBlank(message = "The License shouldn't be Empty")
                             String license,
                             Gender gender,
                             @NotBlank(message = "The Address shouldn't be empty")
                             String address,
                             @NotBlank(message = "The Date Of Birth shouldn't be empty")
                             String dateOfBirth,
                             @NotNull(message = "Aircrew Total Flight Hours is mandatory")
                             @Min(1)
                             Long totalFlightsHours) {

    public UserDetailsDTO(Long userId, String firstName, String lastName, String jobTitle, String license, Gender gender, String address, String dateOfBirth, Long totalFlightsHours) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.license = license;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.totalFlightsHours = totalFlightsHours;
    }
}
