package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewsUpdateDTO {
    @NotEmpty(message = "title not inserted!!!")
    private String title;
    @NotEmpty(message = "text not inserted!!!")
    private String text;
    @NotNull(message = "news id not inserted!!!")
    Long news_id;
}
