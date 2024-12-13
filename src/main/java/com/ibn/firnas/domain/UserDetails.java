package com.ibn.firnas.domain;

import com.ibn.firnas.utils.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.*;

@Entity
@Getter
@Setter
public class UserDetails{
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String jobTitle;
    @Column(nullable = false)
    private String license;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    @Column(nullable = false)
    private String dateOfBirth;
    @Column(nullable = false)
    private Long totalFlightsHours;
    @OneToMany( mappedBy = "userDetails", cascade = CascadeType.ALL)
    @OrderBy(value = "created_at DESC")
    private Set<Location> locations=new LinkedHashSet<>();
    @OneToOne
    @MapsId
    @JoinColumn(name="userId")
    private User user;
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable(
            name = "flights_users",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "flightId")
    )
    private Set<Flight> flights=new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salary_id",referencedColumnName = "salaryId")
    private Salary salary;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;


}
