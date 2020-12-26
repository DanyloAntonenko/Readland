package com.nure.readland.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nure.readland.controller.Constant;
import com.nure.readland.model.Book;
import com.nure.readland.model.Review;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.API_REVIEW)
public class ReviewController {
	@GetMapping("{id}")
	protected Review getReview(@PathVariable String id){
		// TODO: 26.12.2020 need dao
		return null;
	}

	@GetMapping("book/{id}")
	protected Iterable getReviewOfBook(@PathVariable String id){
		// TODO: 26.12.2020 need dao
		return null;
	}

	@GetMapping("user/{id}")
	protected Iterable getReviewOfUser(@PathVariable String id){
		// TODO: 26.12.2020 need dao
		return null;
	}

	@PutMapping()
	protected void createReview(@RequestBody String body) throws JsonProcessingException {
		Review result =
				new ObjectMapper().readValue(body, Review.class);
		// TODO: 26.12.2020 need dao
		return;
	}

	@PatchMapping("{id}")
	protected Review changeReview(@PathVariable String id, @RequestBody String body) throws JsonProcessingException {
		Review result =
				new ObjectMapper().readValue(body, Review.class);
		// TODO: 26.12.2020 need dao
		return null;

	}

	@DeleteMapping("{id}")
	protected void deleteReview(@PathVariable String id){
		// TODO: 26.12.2020 need dao
		return;
	}
}
