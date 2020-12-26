package com.nure.readland.dao.interfaces;

import com.nure.readland.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getById(Long id);
    Book create(Book book);
    Book update(Book book);
    void delete(Book book);
}
