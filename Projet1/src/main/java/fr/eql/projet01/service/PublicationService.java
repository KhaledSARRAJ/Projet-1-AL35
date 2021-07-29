package fr.eql.projet01.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Utilisateur;
import fr.eql.projet01.exception.AecServiceException;
import fr.eql.projet01.exception.NotValidObjectException;
import fr.eql.projet01.exception.ResourceNotFoundException;

public interface PublicationService {

    Publication saveOrUpdate(Publication productDto);

    List<Publication> getAll();

    Publication findById(Long id);

    void deleteById(Long id);

   // List<Publication>findBySupportTypeSupport(String name);

	public Page<Publication> findByTitreIgnoreCaseContains(String mc, Pageable pageable);
	
	List<Publication> findPublicationByUser(Utilisateur u);

//	List<Publication> findBySupport  DetailsList(String name);
	public Page<Publication> findByUtilisateurAndTitreIgnoreCaseContains(Utilisateur utilisateur, String titre, Pageable pageable);
	
	List<Publication> search(Long userId) throws ResourceNotFoundException;
	
	Publication findOne(Long id) throws ResourceNotFoundException;
	
	Publication save (Publication publication) throws NotValidObjectException, ResourceNotFoundException;
	
	void delete (Long id) throws AecServiceException;

}
