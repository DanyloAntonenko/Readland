package com.nure.readland.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nure.readland.controller.Constant;
import com.nure.readland.model.Book;
import com.nure.readland.service.BookService;
import com.nure.readland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(Constant.API_BOOK)
public class BookController {
	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@GetMapping()
	protected Iterable getAllBooks() {
		return bookService.getAll();
	}

	@GetMapping("/search")
	protected Iterable searchBooks(@RequestParam("op") String param,
								   @RequestParam("q") String query) {
		//todo 28.12.2020 добавить обработку исключений, которые выбрасывает сервис
		switch (param) {
			case "tag":
				return bookService.findByTag(query);
			case "name":
				var res = new ArrayList<Book>();
				res.add(bookService.getByName(query));
				return res;
			case "desc":
				return bookService.findByDescription(query);
		}
		return null;
	}

	@GetMapping("{id}")
	protected Book getBook(@PathVariable("id") String id) {
		var book = bookService.getById(Long.valueOf(id));
		bookService.addView(book);
		return book;
	}

	@PreAuthorize("hasAnyAuthority('admin', 'lib')")
	@PatchMapping("{id}")
	protected Book changeBook(@PathVariable("id") String id,
							  @RequestBody String body) throws JsonProcessingException {
		Book result = new ObjectMapper().readValue(body, Book.class);
		return bookService.update(result);
	}

	@PreAuthorize("hasAnyAuthority('admin', 'lib')")
	@DeleteMapping("{id}")
	protected void deleteBook(@PathVariable String id) {
		bookService.delete(bookService.getById(Long.valueOf(id)));
	}

	@PreAuthorize("hasAnyAuthority('admin', 'lib')")
	@PutMapping()
	protected Book addBook(@RequestBody String body) throws JsonProcessingException {
		Book result = new ObjectMapper().readValue(body, Book.class);
		return bookService.create(result);
	}


	@PostMapping("/{book_id}/add")
	protected void addBookToCollection(@PathVariable String book_id) {
		userService.addBook(UserService.getCurrentUser(), bookService.getById(Long.valueOf(book_id)));
	}

	@PostMapping("/{book_id}/remove")
	protected void removeBookFromCollection(@PathVariable String book_id) {
		userService.removeBook(UserService.getCurrentUser(), bookService.getById(Long.valueOf(book_id)));
	}

}
