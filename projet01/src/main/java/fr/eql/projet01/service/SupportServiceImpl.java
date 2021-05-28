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
	SupportRepository supportRepository;

	@Override
	public List<Support> findByPublicationSupport(Publication publication) {
		return supportRepository.findByPublicationSupport(publication);
	}

}
