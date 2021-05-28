package fr.eql.projet01.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.projet01.dao.PublicationRepository;
import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Utilisateur;

@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {
	
	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public List<Publication> findPublicationByUser(Utilisateur u) {
		// TODO Auto-generated method stub
		return publicationRepository.findByUtilisateur(u);
	}

}
