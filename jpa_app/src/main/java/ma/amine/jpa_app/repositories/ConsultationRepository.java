package ma.amine.jpa_app.repositories;

import ma.amine.jpa_app.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
