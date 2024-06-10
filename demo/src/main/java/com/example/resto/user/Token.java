package com.example.resto.user;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private  Integer id;
    private String token;
    private LocalDateTime createdat;
    private LocalDateTime expiresat;
    private LocalDateTime validatedat;
    @Column(nullable = true)
    private boolean expired;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}
