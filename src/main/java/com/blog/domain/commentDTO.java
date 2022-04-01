package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class commentDTO {
    private int bno;
    private String uid;
    private String comment;
}
