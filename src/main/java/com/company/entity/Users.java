package com.company.entity;

import com.company.enums.UserRole;
import com.company.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login",unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Integer age;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @CreationTimestamp
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
}
