package fr.eql.projet01.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.projet01.dao.SupportRepository;
import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Support;



@Service
@Transactional
public class SupportServiceImpl implements SupportService {
	

	@Autowired
	private SupportRepository supportRepository;

	@Override
	public List<Support> findByPublicationSupport(Publication publication) {
		return supportRepository.findByPublicationSupport(publication);

	}
	
	@Override
	public List<Support> findSupportByPublication(Publication publicationSupport) {
		return supportRepository.findByPublicationSupport(publicationSupport);

	}

	@Override
	public Support enregisterSupport(Support support) {
		// TODO Auto-generated method stub
		return supportRepository.save(support);
	}
}
