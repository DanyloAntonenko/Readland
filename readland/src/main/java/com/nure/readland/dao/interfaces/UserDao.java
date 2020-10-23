package com.nure.readland.dao.interfaces;

import com.nure.readland.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Long id);

}
