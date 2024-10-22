package com.ibn.firnas.dto.airCrew;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ibn.firnas.utils.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserDetailsDTO(Long userId,
                             String firstName,
                             String lastName,
                             @NotBlank(message = "The jobTitle shouldn't be Empty")

                             String jobTitle,
                             @NotBlank(message = "The License shouldn't be Empty")
                             String license,
                             @Enumerated(EnumType.STRING)
                             Gender gender,
                             @NotBlank(message = "The Address shouldn't be empty")
                             String address,
                             @NotBlank(message = "The Date Of Birth shouldn't be empty")
                             String dateOfBirth,
                             @NotNull(message = "Aircrew Total Flight Hours is mandatory")
                             @Min(1)
                             Long totalFlightsHours,
                             @JsonInclude(value = JsonInclude.Include.NON_NULL)
                             Long salaryId,
                             @JsonInclude(value = JsonInclude.Include.NON_NULL)
                             Long locationId
                             ) {


}
