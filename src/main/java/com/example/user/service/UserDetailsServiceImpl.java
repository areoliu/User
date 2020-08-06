package com.example.user.service;

import com.example.user.entity.Role;
import com.example.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userService.selectUser(username);
        if (user == null || user.getId() < 1) {
            throw new UsernameNotFoundException("Username not found: "
                    + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), true, true, true, true,
                getGrantedAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(
            User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            authorities
                    .add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }
}
