package com.ibn.firnas.specifications;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.domain.UserDetails_;
import org.springframework.data.jpa.domain.Specification;

public class UserDetailsSpecification {
        public static Specification<UserDetails> nameLike(String firstName,String lastName){
            return (root,query,criteriaBuilder) -> criteriaBuilder.or(
                    criteriaBuilder.like(root.get(UserDetails_.FIRST_NAME),"%"+firstName+"%"),
                    criteriaBuilder.like(root.get(UserDetails_.LAST_NAME),"%"+lastName+"%")
                    );
        }

}
