package com.kameleoon.task.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Responce10Quotes {
    private Long id;
    private Integer score;
    private String postedBy;
    private String text;
}
