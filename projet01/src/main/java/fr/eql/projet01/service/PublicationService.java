package fr.eql.projet01.service;

import java.util.List;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Utilisateur;

public interface PublicationService {

	List<Publication> findPublicationByUser(Utilisateur u);
}
