package com.nure.readland.security;

import com.nure.readland.dao.HibernateUserDao;
import com.nure.readland.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loading user");
        User user = new HibernateUserDao().findByLogin(s);
        return new MyUserPrincipal(user);
    }


}
