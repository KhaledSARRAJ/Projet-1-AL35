package fr.eql.projet01.service;



import java.util.ArrayList;
import java.util.Set;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.eql.projet01.dao.PublicationRepository;
import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Utilisateur;
import fr.eql.projet01.exception.AecServiceException;
import fr.eql.projet01.exception.NotValidObjectException;
import fr.eql.projet01.exception.ResourceNotFoundException;



@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private Validator validator;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public List<Publication> findPublicationByUser(Utilisateur u) {
		return publicationRepository.findByUtilisateur(u);
	}



	@Override
	public Publication saveOrUpdate(Publication productDto) {
		if (productDto == null) throw new IllegalArgumentException("ProductDto is not valid");
		Publication savedObject = publicationRepository.save(productDto);
		return savedObject;
	}

	@Override
	public List<Publication> getAll() {
		Iterable<Publication> iterable = publicationRepository.findAll();
		List<Publication> productList = new ArrayList<>();
		iterable.iterator().forEachRemaining(productList::add);

		// convert list of product to list of dto
		return productList;
	}


	public Publication findById(Long id) {
		if (id == 0) throw new IllegalArgumentException("Id should not be null");
		Publication product = publicationRepository.findById(id).get();
		return product;
	}

	@Override
	public void deleteById(Long id) {
		if (id == 0) throw new IllegalArgumentException("Id should not be null");
		publicationRepository.deleteById(id); 
	}
	//
	//    @Override
	//    public List<Publication> findBySupportTypeSupport(String name) {
	//        if (name == null) throw new IllegalArgumentException("name should not be null");
	//        List<Publication> productList = productRepository.findByTitreIgnoreCase(name);
	//        return productList;
	//    }

	@Override
	public Page<Publication> findByTitreIgnoreCaseContains(String mc, Pageable pageable) {
		return publicationRepository.findByTitreIgnoreCaseContains( mc,  pageable);
	}



	public Page<Publication> findByUtilisateurAndTitreIgnoreCaseContains(Utilisateur utilisateur, String titre, Pageable pageable) {
		return publicationRepository.findByUtilisateurAndTitreIgnoreCaseContains(utilisateur, titre,pageable);
	}

	//	@Override
	//	public List<Publication> findBySupportDetailsName(String name) {
	//		return productRepository.findBySupportDetailsName( name);
	//	}

	@Override 
	public List<Publication> search(Long userId) throws ResourceNotFoundException {
		if(!publicationRepository.existsById(userId))
			throw new ResourceNotFoundException("Il n'y a pas de publications pour cet utilisateur : id : " + userId);
		return publicationRepository.findByUtilisateur(null);	

	}


	@Override
	public Publication findOne(Long id) throws ResourceNotFoundException {
		if(!publicationRepository.existsById(id))
			throw new ResourceNotFoundException("Annonce introuvable avec cet id : " + id);
		return publicationRepository.findById(id).get();	
	}
	

	public Publication save (Publication publication) throws NotValidObjectException, ResourceNotFoundException {
		Assert.notNull(publication, "Ne peut pas être null");
		if(publication.getId() != null)
			this.findOne(publication.getId());
		Set<ConstraintViolation<Publication>> errors = validator.validate(publication);
		if(errors.size() > 0) {
			List<String> l = new ArrayList<String>();
			for(ConstraintViolation<Publication> cv : errors)
				l.add(cv.getMessage());
			throw new NotValidObjectException(l);
		}
		return publicationRepository.save(publication);
	}
	
	@Override 
	public void delete(Long id) throws AecServiceException {
		if(!publicationRepository.existsById(id))
			throw new ResourceNotFoundException("Annonce inexistante (pas supprimable) pour l'id : " +id);
		publicationRepository.findById(id).get().detachWithTheme();
		publicationRepository.deleteById(id);
	}
}
