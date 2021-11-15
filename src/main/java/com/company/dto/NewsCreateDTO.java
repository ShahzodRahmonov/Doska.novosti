package com.company.dto;

import com.company.entity.Users;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewsCreateDTO {
    @NotEmpty(message = "title not inserted!!!")
    private String title;
    @NotEmpty(message = "text not inserted!!!")
    private String text;
}
