package com.nure.readland.dao;

import com.nure.readland.dao.interfaces.ChosenDao;
import com.nure.readland.model.Chosen;
import com.nure.readland.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateChosenDao extends HibernateUtils implements ChosenDao {
	@Override
	public List<Chosen> getChosenForUser(User user) {
		try (Session session = getSessionFactory().openSession()) {
			Query query = session.createQuery("from Chosen where user_id = :id");
			query.setParameter("id", user.getId());
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Chosen create(Chosen chosen) {
		try (Session session = getSessionFactory().openSession()) {
			chosen = (Chosen) session.save(chosen);
			return chosen;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void delete(Chosen chosen) {
		try (Session session = getSessionFactory().openSession()) {
			session.delete(chosen);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
