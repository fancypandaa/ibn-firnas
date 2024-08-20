package com.ibn.firnas.dto.airCrew;

import java.math.BigDecimal;
import java.util.Date;

public record SalaryDTO(Long salaryId, String degree, BigDecimal basic, BigDecimal bonus,
                        BigDecimal penalties, Boolean availability, Date createdAt,Date updatedAt) {
}
