package me.hmine.gestionconference;

import me.hmine.gestionconference.entities.Conference;
import me.hmine.gestionconference.repositories.ConferenceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionConferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionConferenceApplication.class, args);
    }

    //@Bean
    CommandLineRunner start(ConferenceRepository confRepo){
        return args -> {
            Stream.of("c1","c2","c3","c4").forEach(name->{
                Conference conf = new Conference();
                conf.setTitre(name);
                conf.setDate(new Date());
                conf.setDescription("this is conference : "+name);
                conf.setHeureDeb(new Date());
                conf.setHeureFin(new Date());
                confRepo.save(conf);
            });
        };
    }
}
