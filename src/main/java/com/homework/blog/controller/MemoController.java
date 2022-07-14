package com.homework.blog.controller;

import com.homework.blog.dto.BlogRequestDto;
import com.homework.blog.model.Blog;
import com.homework.blog.security.UserDetailsImpl;
import com.homework.blog.service.BlogService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


public class MemoController {
    private final com.homework.blog.service.BlogService blogService;

    public MemoController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/api/blogs/memos")
    public Blog updateMemo(@RequestBody BlogRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();

        Blog memo = blogService.updateMemo(userId, requestDto);

        // 응답 보내기
        return memo;
    }

}
