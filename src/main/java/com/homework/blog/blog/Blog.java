package com.homework.blog.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Blog extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String  password;

    public Blog(String username, String contents, String password,String title) {
        this.username = username;
        this.comment = contents;
        this.password = password;
        this.title = title;
    }

    public Blog(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.comment = requestDto.getComment();
        this.title = requestDto.getTitle();
        this.password= requestDto.getPassword();
    }

    public void update(BlogRequestDto requestDto){
        this.username=requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.comment=requestDto.getComment();
    }
}