package me.hmine.gestionconference.repositories;

import me.hmine.gestionconference.entities.Conference;
import me.hmine.gestionconference.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
}
