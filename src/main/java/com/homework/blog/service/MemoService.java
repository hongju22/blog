package com.homework.blog.service;

import com.homework.blog.dto.BlogRequestDto;

import com.homework.blog.model.Blog;
import com.homework.blog.security.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MemoService {
    private final BlogRepository BlogRepository;

    @Autowired
    public MemoService(BlogRepository BlogRepository) {
        this.BlogRepository = BlogRepository;
    }

    public Blog updateMemo(Long id, BlogRequestDto requestDto){
        Blog Memo = BlogRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다"));

        String memo = requestDto.getMemo();

        Memo.setMemo(memo);
        BlogRepository.save(Memo);

        return Memo;
    }

}
