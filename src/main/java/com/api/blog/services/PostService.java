package com.api.blog.services;

import java.util.List;

import com.api.blog.entities.Post;
import com.api.blog.payloads.PostDto;

public interface PostService {
	// create
	
	PostDto createPost(PostDto postDto, Integer userId,Integer CategoryId);
	
	// update
	
	Post updatePost(PostDto postDto, Integer postId);
	
	// delete
	
	void deletePost(Integer postId);
	
	// get all post
	
	List<Post> getAllPost();
	
	// get post by Id
	
	Post getPostById(Integer postId);
	
	// get all post by category 
	
	List<Post> getPostsByCategory(Integer categoryId);
	
	// get all posts by user
	
	List<Post> getPostByUser(Integer userId);
	
	// get posts by search
	
	List<Post> searchPosts(String keyword);
}
