package fr.eql.projet01.service;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {

	Utilisateur rechercheUtiParId(long id);
	Utilisateur sauvegardeUtilisatuer(Utilisateur uti);
	Utilisateur rechercherUtilisateurParProfil(String profil);
	Utilisateur findInfoUtilisateur(long id);
	   List<Utilisateur> rechercherUtilisateur();

}
