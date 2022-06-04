package me.hmine.gestionconference.repositories;

import me.hmine.gestionconference.entities.Commentaire;
import me.hmine.gestionconference.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference,Long> {
}
