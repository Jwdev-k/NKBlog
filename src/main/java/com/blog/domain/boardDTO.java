package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class boardDTO {
    private int bno;
    private String title;
    private String uid;
    private LocalDate created;
    private String content;
    private int available;
}
