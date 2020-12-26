package com.nure.readland.dao.interfaces;

import com.nure.readland.model.Chosen;
import com.nure.readland.model.User;

import java.util.List;

public interface ChosenDao {
    List<Chosen> getChosenForUser(User user);
    Chosen create(Chosen chosen);
    void delete(Chosen chosen);
}
