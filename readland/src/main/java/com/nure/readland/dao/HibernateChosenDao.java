package com.nure.readland.dao;

import com.nure.readland.dao.interfaces.ChosenDao;
import com.nure.readland.model.Chosen;
import com.nure.readland.model.User;

import java.util.List;

public class HibernateChosenDao extends HibernateUtils implements ChosenDao {
    @Override
    public List<Chosen> getChosenForUser(User user) {
        return null;
    }

    @Override
    public Chosen add(Chosen chosen) {
        return null;
    }

    @Override
    public void delete(Chosen chosen) {

    }
}
