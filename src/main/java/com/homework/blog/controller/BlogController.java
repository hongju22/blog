package com.homework.blog.controller;


import com.homework.blog.blog.Blog;
import com.homework.blog.blog.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController

public class BlogController {
    private final com.homework.blog.blog.BlogRepository BlogRepository;
    private final com.homework.blog.service.BlogService BlogService;

//    게시글 작성
    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        Blog Blog = new Blog(requestDto);
        return BlogRepository.save(Blog);
    }
//    게시글 목록
    @GetMapping("/api/blogs")
    public List<Blog> readBlog() {
        return BlogRepository.findAllByOrderByModifiedAtDesc();
    }

//    자료 조회
    @GetMapping("/api/blogs/{id}")
    public List<Blog> showBlog(){
        return BlogRepository.findAllByOrderByModifiedAtDesc();
    }
//  게시글 수정
    @PutMapping("/api/blogs/{id}")
    public Long update(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){

        if(BlogService.checkPassword(id, requestDto.getPassword())){
            BlogService.update(id, requestDto);
        }
        return id;
    }

//내용 삭제
    @DeleteMapping("/api/blogs/{id}")
    public Long deleteblog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){

        if(BlogService.checkPassword(id, requestDto.getPassword())){
            BlogRepository.deleteById(id);
        }
        return id;
    }

}