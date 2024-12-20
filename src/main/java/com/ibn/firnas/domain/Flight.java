package com.ibn.firnas.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibn.firnas.utils.enums.TripCategory;
import com.ibn.firnas.utils.enums.TripType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.*;
@Entity
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TripCategory tripCategory;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TripType tripType;
    private Boolean showHidden;
    @Column(nullable = false)
    private String departureId;
    @Column(nullable = false)
    private String arrivalId;
    @Column(nullable = false)
    private String duration;
    private String details;
    private String airportTakeOff;
    private String airportTakeOffId;
    private String takeOffTime;
    private String airportLanding;
    private String airportLandingId;
    private String landingTime;
    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<Integer,String> stops=new HashMap<>();
    private List<String> extensions=new ArrayList();
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outbound_date;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date return_date;
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name = "airplaneId")
    private AirPlane airPlane;
    @ManyToMany(mappedBy = "flights",cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.REFRESH})
    private Set<UserDetails> userDetails=new HashSet<>();
    public void addUser(UserDetails user){
        this.userDetails.add(user);
        user.getFlights().add(this);
    }
    public void removeUser(UserDetails user){
        this.userDetails.remove(user);
        user.getFlights().remove(this);
    }
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
}
