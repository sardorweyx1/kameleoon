package com.kameleoon.task.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseRandomQuote {

    private String text;

    private String userName;

    private Integer score;
}
