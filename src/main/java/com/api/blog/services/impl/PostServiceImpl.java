package com.api.blog.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.blog.entities.Category;
import com.api.blog.entities.Post;
import com.api.blog.entities.User;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.payloads.PostDto;
import com.api.blog.repositories.CategoryRepo;
import com.api.blog.repositories.PostRepo;
import com.api.blog.repositories.UserRepo;
import com.api.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId) );
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		
		post.setAddedDate(new Date());
		
		post.setUser(user);
		
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	
	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
