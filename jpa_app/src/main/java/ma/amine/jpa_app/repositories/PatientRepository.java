package ma.amine.jpa_app.repositories;

import ma.amine.jpa_app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
}
