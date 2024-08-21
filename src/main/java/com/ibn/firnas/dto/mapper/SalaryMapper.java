package com.ibn.firnas.dto.mapper;

import com.ibn.firnas.domain.Salary;
import com.ibn.firnas.dto.airCrew.SalaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SalaryMapper {
    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);
    SalaryDTO salarytoSalaryDTO(Salary salary);
    Salary salaryDTOtoSalary(SalaryDTO salaryDTO);
}