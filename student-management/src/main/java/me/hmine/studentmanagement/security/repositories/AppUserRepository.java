package me.hmine.studentmanagement.security.repositories;


import me.hmine.studentmanagement.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
