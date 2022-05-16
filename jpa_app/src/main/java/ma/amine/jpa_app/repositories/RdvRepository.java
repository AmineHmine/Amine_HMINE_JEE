package ma.amine.jpa_app.repositories;

import ma.amine.jpa_app.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RdvRepository extends JpaRepository<RendezVous,Long> {
}
