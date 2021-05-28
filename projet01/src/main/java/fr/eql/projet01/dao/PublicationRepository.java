package fr.eql.projet01.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Utilisateur;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

	List<Publication> findByUtilisateur(Utilisateur utilisateur);
}
