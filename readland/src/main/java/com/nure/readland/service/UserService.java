package com.nure.readland.service;

import com.nure.readland.dao.HibernateUserDao;
import com.nure.readland.dao.interfaces.UserDao;
import com.nure.readland.model.Role;
import com.nure.readland.model.User;

import java.util.List;

public class UserService implements UserDao {
    private HibernateUserDao userDao = new HibernateUserDao();

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


    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    private void checkId(Long id){
        if(id == null){
            throw new NullPointerException("id was null");
        }
        if(id < 1){
            throw new IllegalStateException("id was less than 1");
        }
    }

    private void checkLogin(String login){
        if(login == null){
            throw new NullPointerException("login was null");
        }
        if(login.trim().length() == 0){
            throw new IllegalStateException("login is empty");
        }
    }

    private void checkPassword(String password){
        if(password == null){
            throw new NullPointerException("password was null");
        }
        if(password.trim().length() == 0){
            throw new IllegalStateException("password was empty");
        }
    }

    private void checkName(String name){
        if(name == null){
            throw new NullPointerException("name was null");
        }
        if(name.trim().length() == 0){
            throw new IllegalStateException("name was empty");
        }
    }

    private void checkSurnname(String surname){
        if(surname== null){
            throw new NullPointerException("name was null");
        }
        if(surname.trim().length() == 0){
            throw new IllegalStateException("name was empty");
        }
    }

    private void checkRole(Role role){

    }


}
