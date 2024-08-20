package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.exception.CustomException;

public interface SalaryService {
    SalaryDTO findSalaryById(Long salaryId) throws CustomException;
    SalaryDTO addNewUserSalary(Long userId,SalaryDTO salaryDTO) throws CustomException;
    SalaryDTO updateUserSalary(Long salaryId,SalaryDTO salaryDTO) throws CustomException;

}
