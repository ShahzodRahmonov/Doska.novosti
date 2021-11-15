package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO {
    @NotBlank(message = "login not inserted!!!")
    private String login;
    @NotBlank(message = "password not inserted!!!")
//    @Size(min = 5, max = 10, message = "parol uzunligi 5 dan kam bo'lmasligi va 10 dan ko'p bo'lmasligi kerak!!!")
    private String password;
    @NotBlank(message = "email not inserted!!!")
    @Email(message = "email error inserted")
    private String email;
    @NotNull(message = "age not inserted!!!")
    private Integer age;

}
