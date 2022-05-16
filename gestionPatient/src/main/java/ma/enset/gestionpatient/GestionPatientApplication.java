package ma.enset.gestionpatient;

import ma.enset.gestionpatient.entities.Patient;
import ma.enset.gestionpatient.repositories.PatientRepository;
import ma.enset.gestionpatient.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GestionPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionPatientApplication.class, args);
    }

    //@Bean
    CommandLineRunner cmd(PatientRepository patRepo){
        return args -> {
            patRepo.save(new Patient(null,"amine",new Date(),true,120));
            patRepo.save(new Patient(null,"nadia",new Date(),false,150));
            patRepo.save(new Patient(null,"amal",new Date(),false,630));
            patRepo.save(new Patient(null,"fati",new Date(),true,440));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohammed","1234","1234");
            securityService.saveNewUser("sami","1234","1234");
            securityService.saveNewUser("ali","1234","1234");

            securityService.saveNewRole("ADMIN","the admin user has more access than the normal user");
            securityService.saveNewRole("USER","the normal user has less access than the admin user");

            securityService.addRoleToUser("ali","ADMIN");
            securityService.addRoleToUser("ali","USER");

            securityService.addRoleToUser("sami","USER");

            securityService.addRoleToUser("mohammed","USER");
        };
    }
}
