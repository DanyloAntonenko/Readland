package com.nure.readland.dao.interfaces;

import com.nure.readland.model.Book;
import com.nure.readland.model.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getAll();
    List<Review> getReviewsForBook(Book book);
    Review create(Review review);
    void delete(Review review);
}
