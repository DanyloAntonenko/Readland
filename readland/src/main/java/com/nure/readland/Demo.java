package com.nure.readland;

import com.nure.readland.dao.HibernateUtils;
import com.nure.readland.model.Book;
import com.nure.readland.model.Role;
import com.nure.readland.model.User;
import com.nure.readland.service.UserService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

//TODO запустить мэйн этого класса для заполнения БД
public class Demo {
    public static void main(String[] args) {
        UserService userService = new UserService();
        List<User> users = userService.findAll();
        users.forEach(user -> {
            System.out.print(user.getId());
            System.out.print(" ");
            System.out.print(user.getLogin());
            System.out.print(" ");
            System.out.print(user.getPassword());
            System.out.print(" ");
            System.out.print(user.getName());
            System.out.print(" ");
            System.out.print(user.getSurname());
            System.out.print(" ");
            System.out.print(user.getRole().getId());
            System.out.print(" ");
            System.out.print(user.getRole().getName());
            System.out.print(" | ");
            user.getChosen().forEach(chosen -> System.out.print(chosen.getId()));
            System.out.println();
        });
        /*Session session = HibernateUtils.getSessionFactory().openSession();

        Role role1 = new Role();
        role1.setName("admin");
        Role role2 = new Role();
        role2.setName("user");
        Role role3 = new Role();
        role3.setName("lib");

        try{
            session.beginTransaction();
            session.save(role1);
            session.save(role2);
            session.save(role3);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }

        Query adminQuery = session.createQuery("from Role where name like 'admin'");
        Query userQuery = session.createQuery("from Role where name like 'user'");
        Query libQuery = session.createQuery("from Role where name like 'lib'");

        Role admin = ((List<Role>)adminQuery.list()).get(0);
        Role user = ((List<Role>)userQuery.list()).get(0);
        Role lib = ((List<Role>)libQuery.list()).get(0);

        User adminUser = new User();
        User userUser = new User();
        User libUser = new User();

        adminUser.setLogin("adminLogin");
        adminUser.setPassword("adminPass");
        adminUser.setName("adminName");
        adminUser.setSurname("adminSurname");
        adminUser.setRole(admin);

        userUser.setLogin("userLogin");
        userUser.setPassword("userPass");
        userUser.setName("userName");
        userUser.setSurname("userSurname");
        userUser.setRole(user);

        libUser.setLogin("libLogin");
        libUser.setPassword("libPass");
        libUser.setName("libName");
        libUser.setSurname("libSurname");
        libUser.setRole(lib);

        try{
            session.beginTransaction();
            session.save(adminUser);
            session.save(userUser);
            session.save(libUser);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }*/
    }
}
