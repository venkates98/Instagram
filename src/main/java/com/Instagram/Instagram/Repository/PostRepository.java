package com.Instagram.Instagram.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Instagram.Instagram.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUserId(Long userId);
}