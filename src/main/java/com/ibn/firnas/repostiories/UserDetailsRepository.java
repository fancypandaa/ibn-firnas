package com.ibn.firnas.repostiories;

import com.ibn.firnas.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    Set<UserDetails> findByUserIdIn(List<Long> userIds);
}
