package com.ibn.firnas.dto.airCrew;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record SalaryDTO(Long salaryId,
                        @NotBlank(message = "The degree is required")
                        String degree,
                        @NotNull(message = "The Basic is required")
                        BigDecimal basic,
                        @Min(0)
                        BigDecimal bonus,
                        @Min(0)
                        BigDecimal penalties,
                        Boolean availability, Date createdAt,Date updatedAt) {
    public SalaryDTO(Long salaryId, @NotBlank(message = "The degree is required")
    String degree, @NotNull(message = "The Basic is required")
                     BigDecimal basic, @Min(0)
                     BigDecimal bonus, @Min(0)
                     BigDecimal penalties, Boolean availability, Date createdAt, Date updatedAt) {
        this.salaryId = salaryId;
        this.degree = degree;
        this.basic = basic;
        this.bonus = bonus;
        this.penalties = penalties;
        this.availability = availability;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
