package com.api.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotNull(message = "Please enter a valid salary")
	private String name;
	
	@Email(message = "Please enter a valid salary")
	private String email;
	
	@NotNull(message = "Please enter a valid salary")
	private String about;
	
	@NotNull(message = "Please enter a valid salary")
	private String password;
	
	
	

}
