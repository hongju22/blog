package com.homework.blog.controller;


import com.homework.blog.dto.BlogRequestDto;
import com.homework.blog.model.Blog;
import com.homework.blog.model.UserRoleEnum;
import com.homework.blog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController

public class BlogController {
    private final com.homework.blog.service.BlogService blogService;


    // 신규 상품 등록
    @PostMapping("/api/blogs")
    public Blog createblog(@RequestBody BlogRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();

        Blog blog = blogService.createBlog(requestDto, userId);

        // 응답 보내기
        return blog;
    }

    // 로그인한 회원이 등록한 관심 상품 조회
    @GetMapping("/api/blogs")
    public List<Blog> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();

        return blogService.getBlogs(userId);
    }

    @PutMapping("/api/blogs/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        Blog blog = blogService.updateBlog(id, requestDto);

        return blog.getId();
    }

    // (관리자용) 전체 상품 조회
    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/blogs")
    public List<Blog> getAllProducts() {
        return blogService.getAllBlogs();
    }
}
