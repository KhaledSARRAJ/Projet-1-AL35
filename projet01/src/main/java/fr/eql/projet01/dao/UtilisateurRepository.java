package fr.eql.projet01.dao;

import java.util.List;
import java.util.Set;

import fr.eql.projet01.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.eql.projet01.entity.Abonnement;
import fr.eql.projet01.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	public Utilisateur findByProfile(String profil) ;
	public List<Utilisateur> findByNom(String mc);
}
