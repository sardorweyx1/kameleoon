package com.kameleoon.task.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {

    private String text;

    private Long userId;
}
