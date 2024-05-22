package com.flights.book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name="users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String userId;
    @Column(name ="user_name",unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String email;
    @NonNull
    @JsonIgnore
    private String password;
    @NonNull
    private String role;
}
