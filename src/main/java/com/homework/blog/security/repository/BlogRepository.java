package com.homework.blog.security.repository;

import com.homework.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findAllByUserId(Long userId);
}
