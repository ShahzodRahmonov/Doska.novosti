package com.company.dto;

import com.company.enums.UserRole;
import com.company.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {
    private Long id;
    private UserRole role;
}
