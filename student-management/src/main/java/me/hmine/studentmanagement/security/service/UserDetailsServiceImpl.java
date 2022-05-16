package me.hmine.studentmanagement.security.service;


import me.hmine.studentmanagement.security.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=securityService.loadUserByUserName(username);

//
//        Collection<GrantedAuthority> rolesColl1 = new ArrayList<>();
//        appUser.getAppRoles().forEach(appRole -> {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appRole.getRoleName());
//            rolesColl1.add(authority);
//        });

        Collection<GrantedAuthority> rolesColl =
                appUser.getAppRoles().stream()
                        .map(appRole -> new SimpleGrantedAuthority(appRole.getRoleName())).collect(Collectors.toList());


        User user= new User(appUser.getUsername(), appUser.getPassword(),rolesColl);
        return user;
    }
}
