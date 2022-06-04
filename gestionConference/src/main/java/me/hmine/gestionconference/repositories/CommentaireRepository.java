package me.hmine.gestionconference.repositories;

import me.hmine.gestionconference.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
}
