package com.api.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.payloads.ApiResponse;
import com.api.blog.payloads.UserDto;
import com.api.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	// POST create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto created_user = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(created_user, HttpStatus.CREATED);
	}
	
	
	// PUT update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
	UserDto updated_user = 	this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updated_user);
	}
	
	
	// DELETE delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){
		this.userService.deleteUser(id);
		
		return new ResponseEntity(new ApiResponse("User deleted successfully", true),HttpStatus.OK );
	}
	
	
	
	// GET get user
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
		
		return ResponseEntity.ok(this.userService.getuserById(userId));
	}
	
	
}
