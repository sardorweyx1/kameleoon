package com.kameleoon.task.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponceGetAllQuote {
    private Long id;
    private String text;
    private Integer score;
    private String userName;
    private Timestamp createdAt;
}
