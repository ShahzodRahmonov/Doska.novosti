package com.company.dto;

import com.company.entity.Users;
import com.company.enums.NewsStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class NewsDTO {
    private Long id;
    @NotNull(message = "user_id not inserted!!!" )
    private Long user_id;
    private Users user;
    @NotEmpty(message = "title not inserted!!!")
    private String title;
    @NotEmpty(message = "text not inserted!!!")
    private String text;
    private NewsStatus status;
    private LocalDateTime createdDate;
}
