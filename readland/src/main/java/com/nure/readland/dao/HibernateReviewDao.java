package com.nure.readland.dao;

import com.nure.readland.dao.interfaces.ReviewDao;
import com.nure.readland.model.Book;
import com.nure.readland.model.Review;

import java.util.List;

public class HibernateReviewDao extends HibernateUtils implements ReviewDao {
    @Override
    public List<Review> getAll() {
        return null;
    }

    @Override
    public List<Review> getReviewsForBook(Book book) {
        return null;
    }

    @Override
    public Review create(Review review) {
        return null;
    }

    @Override
    public Review delete(Review review) {
        return null;
    }
}
