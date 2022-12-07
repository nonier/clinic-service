package com.nonier.cliniccore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = {"doctor","userDialogs"})
@ToString(exclude = {"doctor","userDialogs"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Doctor doctor;

    @OneToMany(mappedBy = "user")
    private List<UserDialog> userDialogs = new ArrayList<>();
}