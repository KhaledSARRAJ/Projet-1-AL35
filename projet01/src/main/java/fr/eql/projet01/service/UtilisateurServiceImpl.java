package fr.eql.projet01.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.projet01.dao.UtilisateurRepository;
import fr.eql.projet01.entity.Utilisateur;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public Utilisateur findInfoUtilisateur(long id) {
		return utilisateurRepository.findById(id).orElse(null);
	}
}
