package com.homework.blog.blog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor

public class BlogRequestDto {
    private final String username;
    private final String comment;
    private final String password;
    private final String title;

}
