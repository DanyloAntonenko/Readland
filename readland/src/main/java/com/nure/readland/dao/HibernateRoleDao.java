package com.nure.readland.dao;

import com.nure.readland.dao.interfaces.RoleDao;
import com.nure.readland.model.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class HibernateRoleDao extends HibernateUtils implements RoleDao {
    @Override
    public Role getById(Long id) {
        try(Session session = getSessionFactory().openSession()){
            Query query = session.createQuery("from Role where id = :id");
            query.setParameter("id", id);
            List res = query.list();
            return res.size() > 0 ? (Role)res.get(0) : null;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
