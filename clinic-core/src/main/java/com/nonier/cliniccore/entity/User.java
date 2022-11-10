package com.nonier.cliniccore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = "doctor")
@ToString(exclude = "doctor")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private Doctor doctor;
}