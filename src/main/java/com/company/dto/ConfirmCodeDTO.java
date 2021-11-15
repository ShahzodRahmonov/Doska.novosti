package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ConfirmCodeDTO {
    @NotBlank(message = "login not inserted!!!")
    private String login;
    @NotNull(message = "code not inserted!!!")
    private Integer code;
}
