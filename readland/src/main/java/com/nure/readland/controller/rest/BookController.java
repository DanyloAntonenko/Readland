package com.nure.readland.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nure.readland.controller.Constant;
import com.nure.readland.model.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.API_BOOK)
public class BookController {

	@GetMapping()
	protected Iterable<Book> getAllBooks() {
		return null;
	}

	@GetMapping("search/")
	protected Iterable searchBooks(@RequestParam("op") String param,
								   @RequestParam("q") String query) {
		if (param.equals("tag")) {
			// TODO: 26.12.2020 search by tag
		} else if (param.equals("author")) {
			// TODO: 26.12.2020 search by author
		} else if (param.equals("genre")) {
			// TODO: 26.12.2020 search by genre
		}
		return null;
	}

	@GetMapping("{id}")
	protected Book getBook(@PathVariable("id") String id) {
		// TODO: 26.12.2020 nujno bolshe dao
		return null;
	}


	@PatchMapping("{id}")
	protected Book changeBook(@PathVariable("id") String id, @RequestBody String body)
			throws JsonProcessingException {
		Book result =
				new ObjectMapper().readValue(body, Book.class);
		// TODO: 26.12.2020 more dao
		return null;
	}

	@DeleteMapping("{id}")
	protected void deleteBook(@PathVariable String id) {
		// TODO: 26.12.2020 add book
		return;
	}

	@PutMapping()
	protected void addBook(@RequestBody String body) throws JsonProcessingException {
		Book result =
				new ObjectMapper().readValue(body, Book.class);
		// TODO: 26.12.2020 need dao

	}

	@PostMapping("/{book_id}/add")
	protected void addBookToCollection(@PathVariable String book_id) {
		// TODO: 26.12.2020 need dao
		return;
	}

	@PostMapping("/{book_id}/remove")
	protected void removeBookFromCollection(@PathVariable String book_id) {
		// TODO: 26.12.2020 need dao
		return;
	}



}
