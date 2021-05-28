package fr.eql.projet01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eql.projet01.dao.UtilisateurRepository;
import fr.eql.projet01.entity.Utilisateur;


@Service 
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public Utilisateur rechercherUtilisateurParProfil(String profil) {
		return utilisateurRepository.findByProfil(profil);
	}
		@Override
	public Utilisateur findInfoUtilisateur(long id) {
		return utilisateurRepository.findById(id).orElse(null);

	}
}
