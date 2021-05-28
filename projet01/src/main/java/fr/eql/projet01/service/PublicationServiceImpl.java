package fr.eql.projet01.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.eql.projet01.dao.PublicationRepository;
import fr.eql.projet01.entity.Publication;

@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {

    PublicationRepository productRepository;
  

    @Autowired
    public void setProductRepository(PublicationRepository productRepository) {
        this.productRepository = productRepository;
    }

 
    @Override
    public Publication saveOrUpdate(Publication productDto) {
        if (productDto == null) throw new IllegalArgumentException("ProductDto is not valid");
        Publication savedObject = productRepository.save(productDto);
        return savedObject;
    }

    @Override
    public List<Publication> getAll() {
        Iterable<Publication> iterable = productRepository.findAll();
        List<Publication> productList = new ArrayList<>();
        iterable.iterator().forEachRemaining(productList::add);

        // convert list of product to list of dto
        return productList;
    }

    
    public Publication findById(Long id) {
        if (id == 0) throw new IllegalArgumentException("Id should not be null");
        Publication product = productRepository.findById(id).get();
        return product;
    }

    @Override
    public void deleteById(Long id) {
        if (id == 0) throw new IllegalArgumentException("Id should not be null");
        productRepository.deleteById(id); 
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
		// TODO Auto-generated method stub
		return productRepository.findByTitreIgnoreCaseContains( mc,  pageable);
	}

//	@Override
//	public List<Publication> findBySupportDetailsName(String name) {
//		// TODO Auto-generated method stub
//		return productRepository.findBySupportDetailsName( name);
//	}
}
