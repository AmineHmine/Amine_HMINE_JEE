package me.hmine.studentmanagement.security.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

import me.hmine.studentmanagement.security.entities.AppRole;
import me.hmine.studentmanagement.security.entities.AppUser;
import me.hmine.studentmanagement.security.repositories.AppRoleRepository;
import me.hmine.studentmanagement.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository userRepo;
    private AppRoleRepository roleRepo;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("password not match");
        String pw=passwordEncoder.encode(password);

        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(pw);
        appUser.setActive(true);
        return userRepo.save(appUser);
    }

    @Override
    public AppRole saveNewRole(String rolename, String description) {
        AppRole appRole = roleRepo.findByRoleName(rolename);
        if (appRole != null) throw new RuntimeException("role "+appRole+" allready exist");
        appRole = new AppRole();
        appRole.setRoleName(rolename);
        appRole.setDescription(description);
        AppRole savedaAppRole=roleRepo.save(appRole);
        return savedaAppRole;
    }

    @Override
    @Transactional
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser = userRepo.findByUsername(username);
        if(appUser == null) throw new RuntimeException("User not found");
        AppRole appRole = roleRepo.findByRoleName(rolename);
        if(appRole == null) throw new RuntimeException("Role not found");
            appUser.getAppRoles().add(appRole);
            System.out.println("role added successfully");
    }

    @Override
    public void deleteRoleFromUser(String username, String rolename) {
        AppUser appUser = userRepo.findByUsername(username);
        if(appUser == null) throw new RuntimeException("User not found");
        AppRole appRole = roleRepo.findByRoleName(rolename);
        if(appRole == null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
        System.out.println("role added successfully");
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return userRepo.findByUsername(username);
    }
}
