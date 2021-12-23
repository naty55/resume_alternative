package com.ra.resume_alternative.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MysqlUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found : " + username));
        return user.map(MysqlUserDetails::new).get();

    }
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = users.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found : " + email));
        return user.map(MysqlUserDetails::new).get();

    }

}
