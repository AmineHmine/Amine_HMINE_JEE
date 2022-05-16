package ma.amine.jpa_app.repositories;

import ma.amine.jpa_app.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
}
