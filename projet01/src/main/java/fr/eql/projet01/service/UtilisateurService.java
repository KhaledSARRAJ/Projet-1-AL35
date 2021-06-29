package fr.eql.projet01.service;

import fr.eql.projet01.entity.Utilisateur;
import fr.eql.projet01.exception.ResourceNotFoundException;

public interface UtilisateurService {

	Utilisateur rechercheUtiParId(long id);
	Utilisateur sauvegardeUtilisatuer(Utilisateur uti);
	Utilisateur rechercherUtilisateurParProfil(String profil);
	Utilisateur findInfoUtilisateur(long id);
	Utilisateur findOne(Long id) throws ResourceNotFoundException;
}
