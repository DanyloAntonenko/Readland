package com.nure.readland.service;

import com.nure.readland.dao.HibernateBookDao;
import com.nure.readland.dao.interfaces.BookDao;
import com.nure.readland.model.Book;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookDao {
	private HibernateBookDao bookDao = new HibernateBookDao();

	public static void checkNotNull(Book book) {
		if (book == null) {
			throw new NullPointerException("book was null");
		}
	}

	public static void checkId(Long id) {
		if (id == null) {
			throw new NullPointerException("id was null");
		}
		if (id < 1) {
			throw new IllegalStateException("id was less than 1");
		}
	}

	public static void checkName(String name) {
		if (name == null) {
			throw new NullPointerException("name was null");
		}
		if (name.trim().length() == 0) {
			throw new IllegalStateException("name was empty");
		}
	}

	public static void checkPathToFile(String path) {
		if (path == null) {
			throw new NullPointerException("path to file was null");
		}
		if (path.trim().length() == 0) {
			throw new IllegalStateException("path to file was empty");
		}
		//TODO add checking file existence
	}

	public static void checkViews(Long views) {
		if (views == null) {
			throw new NullPointerException("views was null");
		}
		if (views < 0) {
			throw new IllegalStateException("views was less than zero");
		}
	}

	public static void checkTags(String tags) {
		if (tags == null) {
			throw new NullPointerException("tags was null");
		}
	}

	public static void checkDescription(String description) {
		if (description == null) {
			throw new NullPointerException("description was null");
		}
		if (description.trim().length() == 0) {
			throw new IllegalStateException("description was empty");
		}
	}

	public static void checkPathToPic(String pathToPic) {
		if (pathToPic == null) {
			throw new NullPointerException("path to pic was null");
		}
		if (pathToPic.trim().length() == 0) {
			throw new IllegalStateException("path to pic was empty");
		}
		//TODO check file existence
	}

	private void checkNameExists(String name) {
		if (bookDao.getByName(name) != null) {
			throw new SecurityException("book with this name already exists");
		}
	}

	private void chekIfViewsWereModified(Book initial, Book modifiable) {
		if (initial.getViews().equals(modifiable.getViews()))
			throw new IllegalStateException("views were modified");
	}

	@Override
	public List<Book> getAll() {
		return bookDao.getAll();
	}

	@Override
	public Book getById(Long id) {
		checkId(id);
		return bookDao.getById(id);
	}

	@Override
	public Book getByName(String name) {
		checkName(name);
		return bookDao.getByName(name);
	}

	@Override
	public Book create(Book book) {
		checkNotNull(book);
		checkId(book.getId());
		checkName(book.getName());
		checkNameExists(book.getName());
		checkDescription(book.getDescription());
		checkViews(book.getViews());
		checkTags(book.getTags());
		checkPathToFile(book.getPathToFile());
		checkPathToPic(book.getPathToPic());
		return bookDao.create(book);
	}

	@Override
	public Book update(Book book) {
		checkNotNull(book);
		checkId(book.getId());
		checkName(book.getName());
		checkDescription(book.getDescription());
		checkViews(book.getViews());
		checkTags(book.getTags());
		checkPathToFile(book.getPathToFile());
		checkPathToPic(book.getPathToPic());
		var initBook = bookDao.getById(book.getId());
		chekIfViewsWereModified(initBook, book);
		return bookDao.update(book);
	}

	@Override
	public void delete(Book book) {
		checkNotNull(book);
		checkId(book.getId());
		bookDao.delete(book);
	}

	public void addView(@NotNull Book book) {
		checkNotNull(book);
		checkViews(book.getViews());
		book.setViews(book.getViews() + 1);
		bookDao.update(book);
	}

	public List<Book> findByTag(String tag){
		checkTags(tag);
		return bookDao.findByTag(tag);
	}

}
