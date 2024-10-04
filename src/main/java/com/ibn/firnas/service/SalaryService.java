package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.exception.CustomNotFoundException;

public interface SalaryService {
    SalaryDTO findSalaryById(Long salaryId);
    SalaryDTO addNewUserSalary(Long userId,SalaryDTO salaryDTO);
    SalaryDTO updateUserSalary(Long salaryId,SalaryDTO salaryDTO);

}
