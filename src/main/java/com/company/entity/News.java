package com.company.entity;

import com.company.enums.NewsStatus;
import com.company.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users users;
    @Column(name = "title")
    private String title;
    @Column(name = "text", length = 500)
    private String text;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private NewsStatus status;
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
