package com.nure.readland.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nure.readland.controller.Constant;
import com.nure.readland.model.Book;
import com.nure.readland.model.User;
import com.nure.readland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.API_USER)
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping()
	protected Iterable<User> getAllUsers(){
		return userService.findAll();
	}

	@GetMapping("{id}")
	protected User getUserById(@PathVariable String id){
		return userService.findById(Long.parseLong(id));
	}

	@PutMapping()
	protected User addUser(@RequestBody String body) throws JsonProcessingException{
		User parsed = new ObjectMapper().readValue(body, User.class);
		return userService.create(parsed);
	}

	@PatchMapping("{id}")
	protected User updateUser(@PathVariable String id, @RequestBody String body) throws JsonProcessingException {
		User result = new ObjectMapper().readValue(body, User.class);
		return userService.update(result);
	}

	@DeleteMapping("{id}")
	protected void deleteUser(@PathVariable String id){
		userService.delete(userService.findById(Long.valueOf(id)));
	}
}
