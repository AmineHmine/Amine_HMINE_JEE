package ma.enset.gestionpatient.repositories;

import ma.enset.gestionpatient.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long>{
    Page<Patient> findByNomContains(String keyword,Pageable pageable);
}
