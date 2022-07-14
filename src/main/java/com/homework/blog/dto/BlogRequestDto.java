package com.homework.blog.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor

public class BlogRequestDto {
    private final String name;
    private final String comment;
    private final String title;
    private final String memo;
}
