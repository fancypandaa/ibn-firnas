package com.ibn.firnas.service;

import com.ibn.firnas.dto.airCrew.SalaryDTO;
import java.util.*;
public interface SalaryService {
    SalaryDTO findSalaryById(Long salaryId);
    SalaryDTO addNewUserSalary(Long userId,SalaryDTO salaryDTO);
    SalaryDTO updateUserSalary(Long salaryId,SalaryDTO salaryDTO);
    List<SalaryDTO> findAllBasicSalaryBetween(Double min,Double max);
    List<SalaryDTO> findAllPenaltiesGreaterThan(Double penalty);
    List<SalaryDTO> findAllBonusGreaterThan(Double bonus);
    List<SalaryDTO> findAllBonusLessThan(Double bonus);
}
