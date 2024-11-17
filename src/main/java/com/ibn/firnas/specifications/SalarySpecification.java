package com.ibn.firnas.specifications;

import com.ibn.firnas.domain.Salary;
import com.ibn.firnas.domain.Salary_;
import org.springframework.data.jpa.domain.Specification;

public class SalarySpecification {
    public static Specification<Salary> basicAreBetween(Double min,Double max){
        return (root,query,criteriaBuilder) -> criteriaBuilder.between(root.get(Salary_.BASIC),min,max);
    }
    public static Specification<Salary> bonusGreaterThan(Double bonus){
        return (root,query,criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Salary_.BONUS),bonus);
    }
    public static Specification<Salary> bonusLessThan(Double bonus){
        return (root,query,criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Salary_.BONUS),bonus);
    }
    public static Specification<Salary> penaltiesGreaterThan(Double penalty){
        return (root,query,criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Salary_.PENALTIES),penalty);
    }

}
