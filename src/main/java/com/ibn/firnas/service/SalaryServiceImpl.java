package com.ibn.firnas.service;

import com.ibn.firnas.domain.Salary;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.dto.airCrew.SalaryDTO;
import com.ibn.firnas.dto.mapper.SalaryMapper;
import com.ibn.firnas.exception.CustomException;
import com.ibn.firnas.repostiories.SalaryRepository;
import com.ibn.firnas.repostiories.UserDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService{
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
    public SalaryDTO findSalaryById(Long salaryId) throws CustomException {
        Optional<Salary> optionalSalary = salaryRepository.findById(salaryId);
        if(!optionalSalary.isPresent()){
            throw new CustomException("No salary found for this id");
        }
        return salaryMapper.salarytoSalaryDTO(optionalSalary.get());
    }

    @Override
    public SalaryDTO addNewUserSalary(Long userId, SalaryDTO salaryDTO) throws CustomException {
        Optional<UserDetails> userDetails =userDetailsRepository.findById(userId);
        if(!userDetails.isPresent()){
            throw new CustomException("User not found");
        }
        if(salaryDTO.basic() == null  || salaryDTO.degree().isEmpty()){
            throw new CustomException("Make Sure Basic, Degree not empty...", HttpStatus.BAD_REQUEST);
        }
        Salary salary = salaryRepository.save(salaryMapper.salaryDTOtoSalary(salaryDTO));
        userDetails.get().setSalary(salary);
        userDetailsRepository.save(userDetails.get());
        return salaryMapper.salarytoSalaryDTO(salary);
    }

    @Override
    public SalaryDTO updateUserSalary(Long salaryId, SalaryDTO salaryDTO) throws CustomException {
        Optional<Salary> optionalSalary = salaryRepository.findById(salaryId);
        if (!optionalSalary.isPresent()) {
            throw new CustomException("Salary Not found");
        }
        Salary salary = optionalSalary.get();
        if(salaryDTO.degree()!=null && !salaryDTO.degree().equals(salary.getDegree())){
            salary.setDegree(salaryDTO.degree());
        }
        if(salaryDTO.basic() != null &&salaryDTO.basic()!= salary.getBasic()){
            salary.setBasic(salaryDTO.basic());
        }
        if(salaryDTO.availability()!=null && salaryDTO.availability()!= salary.isAvailability()){
            salary.setAvailability(salaryDTO.availability());
        }
        if(salaryDTO.penalties()!=null){
            BigDecimal newPenalties=salary.getPenalties().add(salaryDTO.penalties());
            if((newPenalties.longValue()) >=0){
                salary.setPenalties(newPenalties);
            }
        }
        if(salaryDTO.bonus()!=null){
            BigDecimal newBonus=salary.getBonus().add(salaryDTO.bonus());
            if((newBonus.longValue()) >=0) {
                salary.setBonus(newBonus);
            }
        }
        return salaryMapper.
                salarytoSalaryDTO(salaryRepository.save(salary));
    }
}