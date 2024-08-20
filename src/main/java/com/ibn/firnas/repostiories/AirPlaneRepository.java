package com.ibn.firnas.repostiories;

import com.ibn.firnas.domain.AirPlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirPlaneRepository extends JpaRepository< AirPlane,Long> {
}
