package me.hmine.gestionconference.repositories;

import me.hmine.gestionconference.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Commentaire,Long> {
}
