package com.Instagram.Instagram.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Instagram.Instagram.Entity.Post;
import com.Instagram.Instagram.Service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/{userId}")
	public Post createPost(@RequestParam("description") String description,
			@RequestParam("image") MultipartFile imageFile, @PathVariable Long userId) throws IOException {
		return postService.createPost(description, imageFile, userId);
	}

	@GetMapping
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}

	@GetMapping("/user/{userId}")
	public List<Post> getPostsByUserId(@PathVariable Long userId) {
		return postService.getPostsByUserId(userId);
	}

	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable Long postId, @RequestParam("description") String description,
			@RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
		return postService.updatePost(postId, description, imageFile);
	}

	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
	}
}