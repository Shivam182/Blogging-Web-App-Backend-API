package com.api.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.blog.exceptions.*;
import com.api.blog.config.AppConstants;
import com.api.blog.entities.Role;
import com.api.blog.entities.User;
import com.api.blog.payloads.UserDto;
import com.api.blog.repositories.RoleRepo;
import com.api.blog.repositories.UserRepo;
import com.api.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
		User saved_user = this.userRepo.save(user);
	
		return this.userToDto(saved_user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id ",userId));
		
		
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updated_user = this.userRepo.save(user);
		
		return this.userToDto(updated_user);
	}

	@Override
	public UserDto getuserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		this.userRepo.delete(user);
		
	}
	
	
	public User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
//		User user = new User();
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		
		
		return user;
		
	}
	
	
	public UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		User user =  this.modelMapper.map(userDto, User.class);
		
		//encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		
		
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
