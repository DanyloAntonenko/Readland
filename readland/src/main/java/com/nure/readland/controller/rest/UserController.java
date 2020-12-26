package com.nure.readland.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nure.readland.controller.Constant;
import com.nure.readland.model.Book;
import com.nure.readland.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.API_USER)
public class UserController {
	@GetMapping()
	protected Iterable<User> getAllUsers(){
		// TODO: 26.12.2020 need dao
		return null;
	}

	@GetMapping("{id}")
	protected User getUserById(@PathVariable String id){
		// TODO: 26.12.2020 need dao
		return null;
	}

	@PutMapping()
	protected User addUser(@RequestBody String body){
		// TODO: 26.12.2020 need dao
		return null;
	}

	@PatchMapping("{id}")
	protected User updateUser(@PathVariable String id, @RequestBody String body) throws JsonProcessingException {
		User result =
				new ObjectMapper().readValue(body, User.class);
		// TODO: 26.12.2020 need dao
		return null;
	}

	@DeleteMapping("{id}")
	protected void deleteUser(@PathVariable String id){

	}
}
