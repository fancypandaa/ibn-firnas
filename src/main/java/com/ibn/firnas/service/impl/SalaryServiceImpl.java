package com.ibn.firnas.service.impl;

import com.ibn.firnas.domain.Salary;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.dto.mapper.SalaryMapper;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.repostiories.SalaryRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import com.ibn.firnas.service.SalaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final static Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);
    private final SalaryRepository salaryRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final SalaryMapper salaryMapper;

    public SalaryServiceImpl(SalaryRepository salaryRepository, UserDetailsRepository userDetailsRepository, SalaryMapper salaryMapper) {
        this.salaryRepository = salaryRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.salaryMapper = salaryMapper;
    }

    @Override
    public SalaryDTO findSalaryById(Long salaryId)  {
        Optional<Salary> optionalSalary = salaryRepository.findById(salaryId);
        if(!optionalSalary.isPresent()){
            throw new CustomNotFoundException("No salary found for this id");
        }
        return salaryMapper.salarytoSalaryDTO(optionalSalary.get());
    }

    @Override
    public SalaryDTO addNewUserSalary(Long userId, SalaryDTO salaryDTO)  {
        Optional<UserDetails> userDetails =userDetailsRepository.findById(userId);
        if(!userDetails.isPresent()){
            throw new CustomNotFoundException("User not found");
        }
        Salary salary = salaryRepository.save(salaryMapper.salaryDTOtoSalary(salaryDTO));
        userDetails.get().setSalary(salary);
        userDetailsRepository.save(userDetails.get());
        return salaryMapper.salarytoSalaryDTO(salary);
    }

    @Override
    public SalaryDTO updateUserSalary(Long salaryId, SalaryDTO salaryDTO) {
        Optional<Salary> optionalSalary = salaryRepository.findById(salaryId);
        if (!optionalSalary.isPresent()) {
            throw new CustomNotFoundException("Salary Not found");
        }
        Salary existSalary = optionalSalary.get();
        Salary newSalary=salaryMapper.salaryDTOtoSalary(salaryDTO);
        newSalary.setSalaryId(existSalary.getSalaryId());
        newSalary.setPenalties(existSalary.getPenalties()+newSalary.getPenalties());
        newSalary.setBonus(existSalary.getBonus()+newSalary.getBonus());
        return salaryMapper.
                salarytoSalaryDTO(salaryRepository.save(newSalary));
    }
}
