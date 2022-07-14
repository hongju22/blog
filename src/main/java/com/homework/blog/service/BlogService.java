package com.homework.blog.service;


import com.homework.blog.model.Blog;
import com.homework.blog.dto.BlogRequestDto;
import com.homework.blog.security.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository BlogRepository;

    @Autowired
    public BlogService(BlogRepository BlogRepository) {
        this.BlogRepository = BlogRepository;
    }

    public Blog createBlog(BlogRequestDto requestDto, Long userId) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Blog blog = new Blog(requestDto, userId);

        BlogRepository.save(blog);

        return blog;
    }

    public Blog updateBlog(Long id, BlogRequestDto requestDto) {
        Blog blog = BlogRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        String name = requestDto.getName();
        String comment = requestDto.getComment();
        String title = requestDto.getTitle();

        blog.setComment(comment);
        blog.setName(name);
        blog.setTitle(title);

        BlogRepository.save(blog);

        return blog;
    }

    public Blog updateMemo(Long id, BlogRequestDto requestDto){
        Blog Memo = BlogRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다"));

        String memo = requestDto.getMemo();

        Memo.setMemo(memo);
        BlogRepository.save(Memo);

        return Memo;
    }


    // 회원 ID 로 등록된 상품 조회
    public List<Blog> getBlogs(Long userId) {
        return BlogRepository.findAllByUserId(userId);
    }

    // (관리자용) 상품 전체 조회
    public List<Blog> getAllBlogs() {
        return BlogRepository.findAll();
    }
}
