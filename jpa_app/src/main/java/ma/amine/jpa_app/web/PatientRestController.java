package ma.amine.jpa_app.web;

import ma.amine.jpa_app.entities.Patient;
import ma.amine.jpa_app.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {
    @Autowired
    private PatientRepository pRepo;

    @GetMapping("/patients")
    public List<Patient> pList(){
        return pRepo.findAll();
    }

}
