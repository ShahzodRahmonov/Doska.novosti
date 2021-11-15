package com.company.dto;

import com.company.enums.UserRole;
import com.company.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class UsersDTO {
    private Long id;
    private String login;
    private String password;
    private String email;
    private Integer age;
    private UserRole role;
    private LocalDateTime registrationDate;
    private String token;
}
