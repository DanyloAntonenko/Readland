package com.nure.readland.dao;

import com.nure.readland.model.Role;
import com.nure.readland.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public abstract class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            //TODO при добавлении новых сущностей добавлять их в конфигурацию
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.configure().buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
