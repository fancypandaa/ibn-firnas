package com.ibn.firnas.specifications;

import com.ibn.firnas.domain.Salary;
import com.ibn.firnas.domain.Salary_;
import org.springframework.data.jpa.domain.Specification;

public class SalarySpecification {
    private static Specification<Salary> basicAreBetween(Double min,Double max){
        return (root,query,criteriaBuilder) -> criteriaBuilder.between(root.get(Salary_.BASIC),min,max);
    }
    private static Specification<Salary> bonusGreaterThan(Double bouns){
        return (root,query,criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Salary_.BONUS),bouns);
    }
    private static Specification<Salary> bonusLessThan(Double bouns){
        return (root,query,criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Salary_.BONUS),bouns);
    }
    private static Specification<Salary> PenaltiesGreaterThan(Double penalty){
        return (root,query,criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Salary_.PENALTIES),penalty);
    }

}
