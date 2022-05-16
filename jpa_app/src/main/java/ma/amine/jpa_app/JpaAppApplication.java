package ma.amine.jpa_app;

import ma.amine.jpa_app.entities.*;
import ma.amine.jpa_app.repositories.ConsultationRepository;
import ma.amine.jpa_app.repositories.MedecinRepository;
import ma.amine.jpa_app.repositories.PatientRepository;
import ma.amine.jpa_app.repositories.RdvRepository;
import ma.amine.jpa_app.service.Hospital;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class    JpaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaAppApplication.class, args);
    }
    @Bean
    CommandLineRunner start(Hospital hospital,
           PatientRepository pRepo,
            MedecinRepository mRepo,
            RdvRepository rRepo,
            ConsultationRepository cRepo
    ){
        return args -> {
            //inserer des enregistrements dans la table patient
            Stream.of("manal","amine","ihssan").forEach(name->{
                Patient p1=new Patient();
                p1.setName(name);
                p1.setDateNaiss(new Date());
                p1.setMalade(false);
                hospital.savePatient(p1);
            });

            Stream.of("anis","aymane","sonia").forEach(name->{
                Medecin m1=new Medecin();
                m1.setName(name);
                m1.setSpecialite(Math.random()>0.5?"cardio":"dentiste");
                m1.setEmail(name+"@gmail.com");
                hospital.saveMedecin(m1);
            });

            List<Patient> p2=pRepo.findAll();
            p2.forEach(p->{
                System.out.println(p.getName());
            });

            Patient p3=pRepo.findById(1L).orElse(null);
            Patient p4=pRepo.findById(2L).orElse(null);
            Medecin med=mRepo.findById(3L).orElse(null);

            RendezVous rdv=new RendezVous();
            rdv.setDate(new Date());
            rdv.setMedecin(med);
            rdv.setStatus(RdvS.pending);
            rdv.setPat(p3);
            RendezVous savedRDV = hospital.saveRDV(rdv);

            System.out.println(savedRDV.getId() );

            RendezVous rdv1=rRepo.findById(1L).orElse(null);
            Consultation con=new Consultation();
            con.setDateConsult(new Date());
            con.setRapport("le rapport de la consultation ...");
            con.setRendezVous(rdv1);
            hospital.saveCon(con);
        };
    }
}
