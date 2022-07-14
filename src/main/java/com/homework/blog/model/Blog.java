package com.homework.blog.model;

import com.homework.blog.dto.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Blog extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String memo;


    public Blog(BlogRequestDto requestDto,Long userId) {
        this.userId = userId;
        this.name = requestDto.getName();
        this.comment = requestDto.getComment();
        this.title = requestDto.getTitle();
        this.memo = "";

    }

}