package com.nure.readland.dao;

import com.nure.readland.dao.HibernateUtils;
import com.nure.readland.dao.interfaces.UserDao;
import com.nure.readland.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUserDao extends HibernateUtils implements UserDao {
    @Override
    public List<User> findAll() {
        try (Session session = getSessionFactory().openSession()){
            return session.createQuery("from User").list();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = getSessionFactory().openSession()){
            Query query = session.createQuery("from User where id = :id");
            query.setParameter("id", id);
            List<User> users = query.list();
            if(users.size() > 0){
                return users.get(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        if(login == null || login.isEmpty()){
            throw new RuntimeException("login was null or empty");
        }
        try (Session session = getSessionFactory().openSession()){
            Query query = session.createQuery("from User where login like :login");
            query.setParameter("login", login);
            List<User> users = query.list();
            if(users.size() > 0){
                return users.get(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
