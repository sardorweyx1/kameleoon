package com.kameleoon.task.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VoteResponce {

    private Long id;
    private String text;
    private String authorName;
    private Integer score;
}
