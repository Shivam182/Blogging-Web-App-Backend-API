package com.api.blog.services;

import java.util.List;

import com.api.blog.entities.Post;
import com.api.blog.payloads.PostDto;
import com.api.blog.payloads.PostResponse;

public interface PostService {
	// create
	
	PostDto createPost(PostDto postDto, Integer userId,Integer CategoryId);
	
	// update
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	// delete
	
	void deletePost(Integer postId);
	
	// get all post
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
	
	// get post by Id
	
	PostDto getPostById(Integer postId);
	
	// get all post by category 
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get all posts by user
	
	List<PostDto> getPostByUser(Integer userId);
	
	// get posts by search
	
	List<PostDto> searchPosts(String keyword);
}
