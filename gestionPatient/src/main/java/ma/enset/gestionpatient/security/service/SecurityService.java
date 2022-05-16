package ma.enset.gestionpatient.security.service;

import ma.enset.gestionpatient.security.entities.AppRole;
import ma.enset.gestionpatient.security.entities.AppUser;

public interface SecurityService  {
    AppUser saveNewUser(String username, String password , String rePassword);
    AppRole saveNewRole(String rolename, String description );
    void addRoleToUser(String username,String rolename);
    void deleteRoleFromUser(String username,String rolename);
    AppUser loadUserByUserName(String username);
}
