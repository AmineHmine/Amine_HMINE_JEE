package me.hmine.studentmanagement;

import me.hmine.studentmanagement.entities.Sexe;
import me.hmine.studentmanagement.entities.Student;
import me.hmine.studentmanagement.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            studentRepository.save(new Student(null,"hmine","amine","aminehmine1@gmail.com",new Date(),Sexe.MASCULIN,true));
            studentRepository.save(new Student(null,"janah","yasser","janah@gmail.com",new Date(),Sexe.MASCULIN,true));
            studentRepository.save(new Student(null,"alaa","naoufal","alaa@gmail.com",new Date(),Sexe.MASCULIN,true));
        };
    }
}
