package fr.eql.projet01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eql.projet01.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

}
