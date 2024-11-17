package com.ibn.firnas.specifications;

import com.ibn.firnas.domain.AirPlane;
import com.ibn.firnas.domain.AirPlane_;
import org.springframework.data.jpa.domain.Specification;
import java.util.*;
public class AirPlaneSpecification {
    public static Specification<AirPlane> dateBetween(Date from,Date to){
        return (root,query,criteriaBuilder) -> criteriaBuilder.between(root.get(AirPlane_.LAST_CHECK_IN),from,to);
    }
}
