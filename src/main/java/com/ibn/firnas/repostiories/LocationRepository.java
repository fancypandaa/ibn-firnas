package com.ibn.firnas.repostiories;

import com.ibn.firnas.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findLocationsByUserDetails_UserIdOrderByCreatedAtDesc(Long userId);
}
