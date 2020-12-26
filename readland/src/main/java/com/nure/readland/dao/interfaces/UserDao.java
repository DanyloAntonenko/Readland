package com.nure.readland.dao.interfaces;

import com.nure.readland.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    User findByLogin(String login);
    User create(User user);
    User update(User user);
    User delete(User user);
}
