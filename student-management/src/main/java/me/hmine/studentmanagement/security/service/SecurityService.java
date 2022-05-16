package me.hmine.studentmanagement.security.service;


import me.hmine.studentmanagement.security.entities.AppRole;
import me.hmine.studentmanagement.security.entities.AppUser;

public interface SecurityService  {
    AppUser saveNewUser(String username, String password , String rePassword);
    AppRole saveNewRole(String rolename, String description );
    void addRoleToUser(String username,String rolename);
    void deleteRoleFromUser(String username,String rolename);
    AppUser loadUserByUserName(String username);
}
