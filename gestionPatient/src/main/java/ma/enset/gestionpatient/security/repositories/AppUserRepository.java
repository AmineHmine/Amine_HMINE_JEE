package ma.enset.gestionpatient.security.repositories;

import ma.enset.gestionpatient.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
