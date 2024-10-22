package com.ibn.firnas.dto.airCrew;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;
@Builder
public record SalaryDTO(Long salaryId,
                        @NotBlank(message = "The degree is required")
                        String degree,
                        @NotNull(message = "The Basic is required")
                        Double basic,
                        @Min(0)
                        Double bonus,
                        @Min(0)
                        Double penalties,
                        Boolean availability, Date createdAt,Date updatedAt) {

}
