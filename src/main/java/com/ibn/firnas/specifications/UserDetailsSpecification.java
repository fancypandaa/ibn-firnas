package com.ibn.firnas.specifications;
import com.ibn.firnas.domain.UserDetails;
import com.ibn.firnas.domain.UserDetails_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
public class UserDetailsSpecification {
        private static Specification<UserDetails> nameLike(Optional<String> firstName, Optional<String> lastName){
            return (root,query,criteriaBuilder) -> criteriaBuilder.or(
                    criteriaBuilder.like(root.get(UserDetails_.FIRST_NAME),"%"+firstName.get()+"%"),
                    criteriaBuilder.like(root.get(UserDetails_.LAST_NAME),"%"+lastName.get()+"%")
                    );
        }

}
