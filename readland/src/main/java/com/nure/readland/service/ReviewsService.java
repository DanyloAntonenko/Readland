package com.nure.readland.service;

import com.nure.readland.dao.HibernateReviewDao;
import com.nure.readland.dao.interfaces.ReviewDao;
import com.nure.readland.model.Book;
import com.nure.readland.model.Review;
import com.nure.readland.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService implements ReviewDao {
	HibernateReviewDao reviewDao = new HibernateReviewDao();

	@Override
	public List<Review> getAll() {
		return reviewDao.getAll();
	}

	@Override
	public List<Review> getReviewsForBook(Book book) {
		BookService.checkNotNull(book);
		return reviewDao.getReviewsForBook(book);
	}

	@Override
	public Review create(Review review) {
		checkIfNotNull(review);
		checkComment(review.getComment());
		checkMark(review.getMark());
		checkBook(review.getBook());
		checkUser(review.getUser());
		return reviewDao.create(review);
	}

	@Override
	public void delete(Review review) {
		checkIfNotNull(review);
		reviewDao.delete(review);
	}

	public static void checkIfNotNull(Review review) {
		if (review == null)
			throw new NullPointerException("book was null");
	}

	public static void checkMark(Integer review) {
		if (review == null)
			throw new NullPointerException("mark was null");
		if (review < 1 || review > 10)
			throw new IllegalStateException("mark was not valid");

	}

	public static void checkComment(String comment) {
		if (comment != null) {
			if (comment.trim().length() > 5000)
				throw new IllegalStateException("comment is too long");
			if (comment.contains("卐") || comment.contains("卍"))
				throw new IllegalStateException("comment contains swastika");
		}
		// TODO: 27.12.2020 чекнуть на наличие слов

	}

	public static void checkBook(Book book){
		if(book==null)
			throw new NullPointerException("book was null");
	}

	public static void checkUser(User user){
		if (user == null)
			throw new NullPointerException("user was null");
	}

}
