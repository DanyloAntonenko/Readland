package com.nure.readland.dao;

import com.nure.readland.dao.interfaces.BookDao;
import com.nure.readland.model.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateBookDao extends HibernateUtils implements BookDao {
    @Override
    public List<Book> getAll() {
        try (Session session = getSessionFactory().openSession()){
            return session.createQuery("from Book").list();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getById(Long id) {
        try(Session session = getSessionFactory().openSession()){
            Query query = session.createQuery("from Book where id = :id");
            query.setParameter("id", id);
            List<Book> res = query.list();
            if(res.size() > 0){
                return res.get(0);
            }
            return null;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Book getByName(String name){
        try(Session session = getSessionFactory().openSession()){
            Query query = session.createQuery("from Book where name like :name");
            query.setParameter("name", name);
            List<Book> res = query.list();
            if(res.size() > 0){
                return res.get(0);
            }
            return null;
        }
    }

    @Override
    public Book create(Book book) {
        try(Session session = getSessionFactory().openSession()){
            book = (Book)session.save(book);
            return book;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book update(Book book) {
        try(Session session = getSessionFactory().openSession()){
            session.update(book);
            return book;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Book book) {
        try(Session session = getSessionFactory().openSession()){
            session.delete(book);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
