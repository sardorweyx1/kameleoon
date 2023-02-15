package com.kameleoon.task.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String text;

    @Column
    private Integer score;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}
