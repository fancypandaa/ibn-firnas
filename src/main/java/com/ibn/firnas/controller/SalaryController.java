package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.service.SalaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SalaryController.SALARY_URI)
public class SalaryController {
    private final static Logger logger = LoggerFactory.getLogger(UserDetailsController.class);
    public static final String SALARY_URI="/api/aircrew/salary";
    private final SalaryService salaryService;
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }
    @GetMapping("/{salaryId}")
    public ResponseEntity<? super SalaryDTO> getSalaryById(@PathVariable Long salaryId){
            SalaryDTO salaryDTO= salaryService.findSalaryById(salaryId);
            return new ResponseEntity<>(salaryDTO,HttpStatus.OK);

    }
    @PostMapping("/{userId}")
    public ResponseEntity<? super SalaryDTO> createNewUserDetails(@PathVariable Long userId, @RequestBody SalaryDTO salaryDTO){
            SalaryDTO savedSalary = salaryService.addNewUserSalary(userId,salaryDTO);
            return new ResponseEntity<>(savedSalary, HttpStatus.CREATED);
    }
    @PutMapping("/{salaryId}")
    public ResponseEntity<? super SalaryDTO> updateUserSalary(@PathVariable Long salaryId, @RequestBody SalaryDTO salaryDTO){
            SalaryDTO updatedSalary = salaryService.updateUserSalary(salaryId,salaryDTO);
            return new ResponseEntity<>(updatedSalary, HttpStatus.OK);
    }
}
