package com.Instagram.Instagram.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Instagram.Instagram.Entity.Post;
import com.Instagram.Instagram.Entity.User;
import com.Instagram.Instagram.Repository.PostRepository;
import com.Instagram.Instagram.Repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	public Post createPost(String description, MultipartFile imageFile, Long userId) throws IOException {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Post post = new Post();
		post.setDescription(description);
		post.setImageData(imageFile.getBytes());
		post.setUser(user);
		return postRepository.save(post);
	}

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	public List<Post> getPostsByUserId(Long userId) {
		return postRepository.findByUserId(userId);
	}

	public Post updatePost(Long postId, String description, MultipartFile imageFile) throws IOException {
		Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
		post.setDescription(description);
		if (imageFile != null && !imageFile.isEmpty()) {
			post.setImageData(imageFile.getBytes());
		}
		return postRepository.save(post);
	}

	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}
}
