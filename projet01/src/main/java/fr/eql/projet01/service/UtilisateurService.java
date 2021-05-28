package fr.eql.projet01.service;

import fr.eql.projet01.entity.Utilisateur;

public interface UtilisateurService {

	Utilisateur rechercheUtiParId(long id);
	Utilisateur sauvegardeUtilisatuer(Utilisateur uti);
	Utilisateur rechercherUtilisateurParProfil(String profil);
	Utilisateur findInfoUtilisateur(long id);
}
