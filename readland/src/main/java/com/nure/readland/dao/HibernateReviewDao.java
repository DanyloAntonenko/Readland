package com.nure.readland.dao;

import com.nure.readland.dao.interfaces.ReviewDao;
import com.nure.readland.model.Book;
import com.nure.readland.model.Review;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateReviewDao extends HibernateUtils implements ReviewDao {
	@Override
	public List<Review> getAll() {
		try (Session session = getSessionFactory().openSession()) {
			return session.createQuery("from Review").list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Review> getReviewsForBook(Book book) {
		try (Session session = getSessionFactory().openSession()) {
			Query query = session.createQuery("from Review where book_id = :book_id");
			query.setParameter("book_id", book.getId());
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Review create(Review review) {
		try (Session session = getSessionFactory().openSession()) {
			return (Review) session.save(review);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Review review) {
		try (Session session = getSessionFactory().openSession()) {
			session.delete(review);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Review getById(Long id) {
		try (Session session = getSessionFactory().openSession()) {
			Query query = session.createQuery("from Review where id = :id");
			query.setParameter("id", id);
			List<Review> res = query.list();
			if (res.size() > 0) {
				return res.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
