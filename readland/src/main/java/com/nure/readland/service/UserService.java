package com.nure.readland.service;

import com.nure.readland.dao.HibernateUserDao;
import com.nure.readland.dao.interfaces.UserDao;
import com.nure.readland.model.Book;
import com.nure.readland.model.Role;
import com.nure.readland.model.User;
import com.nure.readland.security.MyUserPrincipal;
import com.sun.istack.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDao {
	private final HibernateUserDao userDao = new HibernateUserDao();

	public static User getCurrentUser() {
		// TODO: 27.12.2020 накиньте фильтры на запросики в конфигурации и ничего не будет
		return ((MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
	}

	public static ArrayList<String> getCurrentUserAuthorities() {
		return (ArrayList<String>) ((ArrayList) ((MyUserPrincipal) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal())
				.getAuthorities())
				.stream()
				.map(o -> o.toString())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public static void checkNotNull(User user) {
		if (user == null) {
			throw new NullPointerException("user was null");
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

	public static void checkLogin(String login) {
		if (login == null) {
			throw new NullPointerException("login was null");
		}
		if (login.trim().length() == 0) {
			throw new IllegalStateException("login is empty");
		}
	}

	public static void checkPassword(String password) {
		if (password == null) {
			throw new NullPointerException("password was null");
		}
		if (password.trim().length() == 0) {
			throw new IllegalStateException("password was empty");
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

	public static void checkSurname(String surname) {
		if (surname == null) {
			throw new NullPointerException("name was null");
		}
		if (surname.trim().length() == 0) {
			throw new IllegalStateException("name was empty");
		}
	}

	public static void checkRole(Role role) {
		if (role == null) {
			throw new NullPointerException("role was null");
		}
		if (role.getId() == null) {
			throw new NullPointerException("role id was null");
		}
		if (role.getId() < 1) {
			throw new IllegalStateException("role id was less than 0");
		}
	}

	public static void checkPrivilege(Long id) {
		boolean isIdEquals = UserService.getCurrentUser().getId().equals(id);
		boolean hasAdminAuthority = UserService.getCurrentUserAuthorities().contains("admin");
		if (!isIdEquals || !hasAdminAuthority)
			throw new IllegalStateException("id was wrong or insufficient privileges");
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	//TODO на вызове проверять на Runtime, IllegalState, NullPointer
	@Override
	public User findById(Long id) throws RuntimeException {
		checkId(id);
		return userDao.findById(id);
	}

	//TODO на вызове проверять на Runtime, IllegalState, NullPointer
	@Override
	public User findByLogin(String login) throws RuntimeException {
		checkLogin(login);
		return userDao.findByLogin(login);
	}

	//TODO на вызове проверять на Runtime, IllegalState, NullPointer
	@Override
	public User create(User user) throws RuntimeException {
		checkNotNull(user);
		checkLogin(user.getLogin());
		checkLoginExists(user.getLogin());
		checkPassword(user.getPassword());
		checkName(user.getName());
		checkSurname(user.getSurname());
		checkRole(user.getRole());
		return userDao.create(user);
	}

	//TODO на вызове проверять на Runtime, IllegalState, NullPointer
	@Override
	public User update(User user) throws RuntimeException {
		checkPrivilege(user.getId());
		checkNotNull(user);
		checkLogin(user.getLogin());
		checkPassword(user.getPassword());
		checkName(user.getName());
		checkSurname(user.getSurname());
		checkRole(user.getRole());
		return userDao.update(user);
	}

	//TODO на вызове проверять на Runtime, IllegalState, NullPointer
	@Override
	public void delete(User user) throws RuntimeException {
		checkNotNull(user);
		checkId(user.getId());
		userDao.delete(user);
	}

	public void addBook(@NotNull User user, @NotNull Book book) {
		checkNotNull(user);
		BookService.checkNotNull(book);
		user.getChosen().add(book);
		userDao.update(user);
	}

	// TODO: 27.12.2020 check if exists. import from BookService?
	public void removeBook(@NotNull User user, @NotNull Book book) {
		checkNotNull(user);
		BookService.checkNotNull(book);
		user.getChosen().remove(book);
		userDao.update(user);
	}

	private void checkLoginExists(String login) {
		if (userDao.findByLogin(login) != null) {
			throw new SecurityException("login already exists");
		}
	}
}
