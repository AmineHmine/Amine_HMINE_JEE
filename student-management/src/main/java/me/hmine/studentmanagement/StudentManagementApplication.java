package me.hmine.studentmanagement;

import me.hmine.studentmanagement.entities.Sexe;
import me.hmine.studentmanagement.entities.Student;
import me.hmine.studentmanagement.repositories.StudentRepository;
import me.hmine.studentmanagement.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            studentRepository.save(new Student(null,"hmine","amine","aminehmine1@gmail.com",new Date(),Sexe.MASCULIN,true));
            studentRepository.save(new Student(null,"janah","yasser","janah@gmail.com",new Date(),Sexe.MASCULIN,true));
            studentRepository.save(new Student(null,"alaa","naoufal","alaa@gmail.com",new Date(),Sexe.MASCULIN,true));
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("Prof1","1234","1234");
            securityService.saveNewUser("Stud1","1234","1234");
            securityService.saveNewUser("stud2","1234","1234");

            securityService.saveNewRole("ADMIN","the admin user has more access than the normal user");
            securityService.saveNewRole("USER","the normal user has less access than the admin user");

            securityService.addRoleToUser("Prof1","ADMIN");
            securityService.addRoleToUser("Prof1","USER");

            securityService.addRoleToUser("Stud1","USER");

            securityService.addRoleToUser("Stud2","USER");
        };
    }

}
