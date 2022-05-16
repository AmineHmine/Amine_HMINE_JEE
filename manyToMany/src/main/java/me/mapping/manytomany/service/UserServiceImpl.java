package me.mapping.manytomany.service;

import lombok.AllArgsConstructor;
import me.mapping.manytomany.entities.Role;
import me.mapping.manytomany.entities.User;
import me.mapping.manytomany.repositories.RoleRepository;
import me.mapping.manytomany.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;
    private RoleRepository roleRepo;

//    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo) {
//        this.userRepo = userRepo;
//        this.roleRepo = roleRepo;
//    } this or use @AllArgsConstructor

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        //UUID generer une clé unique

        return userRepo.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(rolename);

        user.getRoles().add(role);
        role.getUsers().add(user);
        //userRepo.save(user); // because we have @Transactinal we dont need to save
    }

    @Override
    public User login(String username, String password) {
        User user=userRepo.findByUsername(username);
        if (user == null) throw new RuntimeException("");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("bad credentials");
    }
}
