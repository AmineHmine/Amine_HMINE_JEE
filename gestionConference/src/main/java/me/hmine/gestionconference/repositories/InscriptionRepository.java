package me.hmine.gestionconference.repositories;

import me.hmine.gestionconference.entities.Commentaire;
import me.hmine.gestionconference.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
}
