package com.blog.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class boardDTO implements Serializable {
    private static final long serialVersionUID = 5286660557338476244L;

    private int bno;
    private String title;
    private String uid;
    private LocalDate created;
    private String content;
    private int available;
}
