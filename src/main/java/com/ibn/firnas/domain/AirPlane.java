package com.ibn.firnas.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.*;
@Entity
@Getter
@Setter
public class AirPlane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planeId;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String planeName;
    @Column(nullable = false)
    private Integer airline;
    private String airlineLogo;
    private String model;
    private Integer totalFlightHours;
    private Integer fuelTankCapacity;
    private Date lastCheckIn;
    private Integer numberOfSeats;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airPlane", cascade = CascadeType.ALL)
    private Set<Flight> flights=new LinkedHashSet<>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updateAt;
}
