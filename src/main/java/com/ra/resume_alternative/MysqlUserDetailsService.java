package com.ra.resume_alternative;

import java.util.Optional;

import com.ra.resume_alternative.domain.User;
import com.ra.resume_alternative.repositories.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MysqlUserDetailsService implements UserDetailsService {

    @Autowired
    Users users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.findByName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found : " + username));
        return user.map(MysqlUserDetails::new).get();

    }

}
