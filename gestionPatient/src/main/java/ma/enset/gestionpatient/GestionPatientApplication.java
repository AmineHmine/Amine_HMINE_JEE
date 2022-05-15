package ma.enset.gestionpatient;

import ma.enset.gestionpatient.entities.Patient;
import ma.enset.gestionpatient.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GestionPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionPatientApplication.class, args);
    }

    @Bean
    CommandLineRunner cmd(PatientRepository patRepo){
        return args -> {
            patRepo.save(new Patient(null,"amine",new Date(),true,20));
            patRepo.save(new Patient(null,"nadia",new Date(),false,50));
            patRepo.save(new Patient(null,"amal",new Date(),false,30));
            patRepo.save(new Patient(null,"fati",new Date(),true,40));
        };
    }
}
