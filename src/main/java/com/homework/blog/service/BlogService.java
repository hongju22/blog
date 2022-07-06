package com.homework.blog.service;

import com.homework.blog.blog.Blog;
import com.homework.blog.blog.BlogRequestDto;
import com.homework.blog.blog.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository BlogRepository;


//   update시 사용
    @Transactional
    public Long update(Long id, BlogRequestDto requestDto) {
        Blog blog = BlogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        blog.update(requestDto);
        return id;
    }
    @Transactional
    public boolean checkPassword(Long id,String password) {
        return ( BlogRepository.findById(id).equals(password) );
    }

}