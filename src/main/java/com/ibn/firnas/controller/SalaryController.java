package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.exception.CustomException;
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
        try {
            SalaryDTO salaryDTO= salaryService.findSalaryById(salaryId);
            return new ResponseEntity<>(salaryDTO,HttpStatus.OK);
        }
        catch (CustomException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/{userId}")
    public ResponseEntity<? super SalaryDTO> createNewUserDetails(@PathVariable Long userId, @RequestBody SalaryDTO salaryDTO){
        try {
            SalaryDTO savedSalary = salaryService.addNewUserSalary(userId,salaryDTO);
            return new ResponseEntity<>(savedSalary, HttpStatus.CREATED);
        }
        catch (CustomException exception){
            logger.error(exception.getStackTrace().toString());
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{salaryId}")
    public ResponseEntity<? super SalaryDTO> updateUserSalary(@PathVariable Long salaryId, @RequestBody SalaryDTO salaryDTO){
        try {
            SalaryDTO updatedSalary = salaryService.updateUserSalary(salaryId,salaryDTO);
            return new ResponseEntity<>(updatedSalary, HttpStatus.OK);
        }
        catch (CustomException exception){
            logger.error(exception.getStackTrace().toString());
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
