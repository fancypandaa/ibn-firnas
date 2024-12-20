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
    private String airline;
    private String airlineLogo;
    private String model;
    private Integer totalFlightHours;
    private Integer fuelTankCapacity;
    private Date lastCheckIn;
    private Integer numberOfSeats;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airPlane", cascade = CascadeType.ALL)
    private Set<Flight> flights=new LinkedHashSet<>();
    public void addFlights(Flight flight){
        this.flights.add(flight);
        flight.setAirPlane(this);
    }
    public void removeFlights(Flight flight){
        this.flights.remove(flight);
        flight.setAirPlane(null);
    }
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updateAt;

}
