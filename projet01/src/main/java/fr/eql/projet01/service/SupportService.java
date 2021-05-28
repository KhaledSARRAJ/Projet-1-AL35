package fr.eql.projet01.service;

import java.util.List;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Support;

public interface SupportService {
	List<Support> findByPublicationSupport(Publication publication);

}
