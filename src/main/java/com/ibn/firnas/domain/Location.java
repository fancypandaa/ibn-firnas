package com.ibn.firnas.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.*;
@Entity
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    @Column(unique = true,nullable = false)
    @Pattern(regexp="^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ipAddress;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String lat;
    @Column(nullable = false)
    private String lng;
    @Column(nullable = false)
    private Boolean isActive=false;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDetails userDetails;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updateAt;
}
