package fr.eql.projet01.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eql.projet01.dao.UtilisateurRepository;
import fr.eql.projet01.entity.Utilisateur;


@Service 
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired 
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public Utilisateur rechercheUtiParId(long id) {
		return utilisateurRepository.findById(id).orElse(null);
	}

	@Override
	public Utilisateur sauvegardeUtilisatuer(Utilisateur uti) {
		Utilisateur src = new Utilisateur();
		
		src = utilisateurRepository.findById(uti.getId()).orElse(null);
		src.setNom(uti.getNom());
		src.setPrenom(uti.getPrenom());
		src.setSexe(uti.getSexe());
		src.setMail(uti.getMail());
		src.setTelephone(uti.getTelephone());
		src.setRue(uti.getRue());
		src.setVille(uti.getVille());
		src.setComplement(uti.getComplement());
		src.setProfile(uti.getProfile());
		src.setPasseWord(uti.getPasseWord());

		return utilisateurRepository.save(src);
	}

}
