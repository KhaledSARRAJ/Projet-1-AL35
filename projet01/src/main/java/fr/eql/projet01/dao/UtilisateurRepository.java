package fr.eql.projet01.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eql.projet01.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	public Utilisateur findByProfil(String profil);
	public List<Utilisateur> findByNom(String mc);
}
