package com.nure.readland.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nure.readland.controller.Constant;
import com.nure.readland.model.Review;
import com.nure.readland.service.BookService;
import com.nure.readland.service.ReviewService;
import com.nure.readland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(Constant.API_REVIEW)
public class ReviewController {

	@Autowired
	ReviewService rs;

	@Autowired
	BookService bs;

	@Autowired
	UserService us;

	@GetMapping("{id}")
	protected Review getReview(@PathVariable String id) {
		return rs.getById(Long.valueOf(id));
	}

	@GetMapping("/book/{id}")
	protected Iterable getReviewOfBook(@PathVariable String id) {
		return rs.getReviewsForBook(bs.getById(Long.valueOf(id)));
	}

	/**
	 * creates Review from json object.
	 * @param body IMPORTANT pass bookId as book and authorId as user
	 * @throws JsonProcessingException
	 */
	@PutMapping()
	protected Review createReview(@RequestBody String body) throws JsonProcessingException {
		HashMap result = new ObjectMapper().readValue(body, HashMap.class);
		Review review = new Review();
		review.setBook(bs.getById((Long) result.get("book")));
		review.setComment((String) result.get("comment"));
		review.setMark((Integer) result.get("mark"));
		return rs.create(review);
	}

	@DeleteMapping("{id}")
	protected void deleteReview(@PathVariable String id) {
		rs.delete(rs.getById(Long.valueOf(id)));
	}
}
