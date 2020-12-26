package com.nure.readland.service;

import com.nure.readland.dao.HibernateBookDao;
import com.nure.readland.dao.interfaces.BookDao;
import com.nure.readland.model.Book;

import java.util.List;

public class BookService implements BookDao {
    private HibernateBookDao hibernateBookDao = new HibernateBookDao();

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book create(Book book) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }
}
